import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PerformanceMetrics {
	private double typingAccuracy;
	private int letterIndex;
	private double wordsPerMinute;
	private double totalTime;
	private int letterLevel;
	private int numberofLines;
	private LinkedList<Double> wordsPerMinuteData = new LinkedList<Double>();
	private LinkedList<Integer> scoreData = new LinkedList<Integer>();
	private LinkedList<Double> accuracyData = new LinkedList<Double>();
	
	private Map<Character, LinkedList<Double>> characterTimeData  = new HashMap<Character, LinkedList<Double>>();
	private Map<Character, LinkedList<Integer>> characterErrorData  = new HashMap<Character, LinkedList<Integer>>();
	
	private int numErrors;
	private int totalnumErrors;
	private int totalnumSuccessful;
	private double accuracy;
	private TimeWatch letterTime = TimeWatch.start();
	private TimeWatch wordTime = TimeWatch.start();
	private TimeWatch lineTime = TimeWatch.start();
	private char[] characters = {'e','t','a','o','i','n','s','r','d','l','c','u','m','w','f','g','y','p','b','v','k','j','x','q','z'};
	private int score;
	private int averageWordSize = 6;

	
	public PerformanceMetrics() {
		letterLevel = 0;
		totalnumErrors = 0;
		totalnumSuccessful = 0;
		typingAccuracy = 100.0;
		totalTime = 0.0;
		wordsPerMinute = 0;
		score = 0;
		numberofLines = 0;
	}
	
	public PerformanceMetrics (JSONObject username) {
		try {
			letterLevel = (int) username.get("letterLevel");
			totalnumErrors = (int) username.get("numberOfErrors");
			totalnumSuccessful = (int) username.get("numberOfCorrectLetters");
			typingAccuracy = (double) username.get("Typing Accuracy");
			totalTime = (double) username.get("Total Time");
			wordsPerMinute = (double) username.get("wordsPerMinute");
			numberofLines = (int) username.get("numberofLines");
			score = (int) username.get("score");
		} catch (NumberFormatException | JSONException e) {
			new PerformanceMetrics();
			e.printStackTrace();
			System.out.println("default user is launched");
		}

	}
	
public String validateInput(char input, char correctletter) {
		
	if (input == correctletter) {	
		long time = letterTime.time(TimeUnit.MILLISECONDS);
		letterTime.reset();
		letterIndex++;

		if (input==' ') {
			recordData();
			return "SPACE";
		} else if (input=='\n') {
			recordData();
			return "NEWLINE";
		}
		if (characterTimeData.containsKey(input)) {
			 characterTimeData.get(input).add((double) time); 
		 } else {
			 LinkedList<Double> ll = new LinkedList<Double>();
			 ll.add((double) time);
			 characterTimeData.put(input, ll);
		 }
			return "CORRECT";
	}
	else {
		numErrors++;
		if (characterErrorData.containsKey(correctletter)) {
			 characterErrorData.get(correctletter).add(0);
		 } else {
			 LinkedList<Integer> ll = new LinkedList<Integer>();
			 ll.add(0);
			 characterErrorData.put(correctletter, ll);
		 }
		return "INCORRECT";
	}
}

public void toJsonFile () {
	JsonObjectBuilder UserStats = Json.createObjectBuilder()
	     .add("numberOfErrors", this.numErrors)
	     .add("Typing Accuracy", this.typingAccuracy)
	     .add("Total Time", this.totalTime)
	     .add("letterLevel", this.letterLevel)
	     .add("wordsPerMinute", this.wordsPerMinute)
	     .add("numberofLines", this.numberofLines)
			 .add("score", this.score)
			 .add("numberOfCorrectLetters", this.totalnumSuccessful);
	     
	JsonObjectBuilder letterSpeed = Json.createObjectBuilder();
	
	for (Map.Entry<Character, LinkedList<Double>> entry : characterTimeData.entrySet()) {
    Character key = entry.getKey();
    LinkedList<Double> list = entry.getValue();
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();
    letterSpeed.add(String.valueOf(key), gson.toJson(list));
	}
	
	UserStats.add("letterSpeed", letterSpeed);
	JsonObjectBuilder letterErrors = Json.createObjectBuilder();
	
	for (Entry<Character, LinkedList<Integer>> entry : characterErrorData.entrySet()) {
    char key = entry.getKey();
    LinkedList<Integer> list = entry.getValue();
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();
    letterErrors.add(String.valueOf(key), gson.toJson(list));
	}
	
	UserStats.add("letterErros", letterErrors);
	
	JsonWriter writer;
	try {
		writer = Json.createWriter(new FileWriter(new File("UserData.json")));
		writer.writeObject(UserStats.build());
		writer.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}

public void recordData() {
	wordsPerMinute = Math.round((double) letterIndex*60000/
			(averageWordSize*(wordTime.time(TimeUnit.MILLISECONDS))));
	wordsPerMinuteData.add(wordsPerMinute);
	wordTime.reset();
	accuracy = (int) 100.0*letterIndex / (letterIndex + numErrors);
	accuracyData.add(accuracy);
	score = (int) ((int) wordsPerMinute*accuracy*accuracy/1000);
	scoreData.add(score);
	lineTime.reset();

	numberofLines++;

	letterIndex = 0;
	totalnumSuccessful += letterIndex;
	totalnumErrors += numErrors;
	numErrors = 0;
}

public void resetTime() {
	letterTime.reset();
	lineTime.reset(); 
	
}

int getLetterLevel () {
	return letterLevel;
}

public int getScore() {
	return this.score;

}

public Double getWordsperMinute() {
	return this.wordsPerMinute;

}

public Double getAccuracy() {
	return this.accuracy;

}

public int getRecentScore() {
	int sum = 0;
	for (int i = 0; i < 5; ++i) {
		sum += scoreData.descendingIterator().next();
	}
	return sum/5;

}

public Double getRecentWordsperMinute() {
	double sum = 0;
	for (int i = 0; i < 5; ++i) {
		sum += wordsPerMinuteData.descendingIterator().next();
	}
	return sum/5;

}

public Double getRecentAccuracy() {
	double sum = 0;
	for (int i = 0; i < 5; ++i) {
		sum += accuracyData.descendingIterator().next();
	}
	return sum/5;
	
}

public LinkedList<Integer> getScoreData() {
	return scoreData;
}

public LinkedList<Double> getAccuracyData() {
	return accuracyData;
}

public LinkedList<Double> getWordsPerMinuteData() {
	return wordsPerMinuteData;
}

public double averageWordsPerMinute() {
	Iterator<Double> it = wordsPerMinuteData.iterator();
	double sum = 0.0;
	int N = wordsPerMinuteData.size();
	while (it.hasNext()) {
		sum = it.next();
	}
	return sum/N;
}

public double averageAccuracy() {
	Iterator<Double> it = accuracyData.iterator();
	double sum = 0.0;
	int N = accuracyData.size();
	while (it.hasNext()) {
		sum = it.next();
	}
	return sum/N;
}

public double averageScore() {
	Iterator<Integer> it = scoreData.iterator();
	int sum = 0;
	int N = scoreData.size();
	while (it.hasNext()) {
		sum = it.next();
	}
	return 1.0*sum/N;
}
	
}
