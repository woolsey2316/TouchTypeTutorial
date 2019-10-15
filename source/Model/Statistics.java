package Model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Statistics {
	private double typingAccuracy;
	private int letterIndex;
	private double wordsPerMinute;
	private double totalTime;
	private int letterLevel;
	private int numberofLines;
	private static LinkedList<Double> wordsPerMinuteData = new LinkedList<Double>();
	private static LinkedList<Integer> scoreData = new LinkedList<Integer>();
	private static LinkedList<Double> accuracyData = new LinkedList<Double>();
	
	private Map<Character, LinkedList<Integer>> characterTimeData  = new HashMap<Character, LinkedList<Integer>>();
	private Map<Character, Integer> characterErrorData  = new HashMap<Character, Integer>();
	
	private int numErrors;
	private int totalnumErrors;
	private int totalnumSuccessful;
	private double accuracy;
	private TimeWatch letterTime = TimeWatch.start();
	private TimeWatch wordTime = TimeWatch.start();
	private TimeWatch lineTime = TimeWatch.start();
	private int score;
	private int averageWordSize = 6;
	private int endOfWord = 0;
	private double goalWPM;
	int lineNumber;
	
	WordGenerator wordGenerator = new WordGenerator();
	String[] paragraphOfWords = wordGenerator.generateWords(letterLevel);
	
	public Statistics() {
		letterLevel = 0;
		totalnumErrors = 0;
		totalnumSuccessful = 0;
		typingAccuracy = 100.0;
		totalTime = 0.0;
		wordsPerMinute = 35;
		score = 0;
		numberofLines = 0;
		goalWPM = 70.0; 
		accuracyData.add(typingAccuracy);
		wordsPerMinuteData.add(wordsPerMinute);
		scoreData.add(score);

	}
	
	public Statistics (JSONObject username) {
		try {
				letterLevel = (int) username.get("letterLevel");
				totalnumErrors = (int) username.get("Number of Errors");
				totalnumSuccessful = (int) username.get("Number of Correct Letters");
				if (totalnumSuccessful == 0 && totalnumErrors == 0) {
					typingAccuracy = 100.0;
				} else {
					typingAccuracy = totalnumSuccessful / (totalnumSuccessful + totalnumErrors);
				}
				totalTime = (double) username.get("Total Time");
				wordsPerMinute = (double) username.get("wordsPerMinute");
				numberofLines = (int) username.get("numberofLines");
				score = (int) username.get("score");
				goalWPM = (double) username.getDouble("goalWPM");
		
				Double[] accurData = new Gson().fromJson((String) username.get("accuracyData"), Double[].class);
				Integer[] scoData = new Gson().fromJson((String) username.get("scoreData"), Integer[].class);
				Double[] wordsPerMData = new Gson().fromJson((String) username.get("wordsPerMinuteData"), Double[].class);
				
				accuracyData.addAll(new ArrayList(Arrays.asList(accurData)));
				scoreData.addAll(new ArrayList(Arrays.asList(scoData)));
				wordsPerMinuteData.addAll(new ArrayList(Arrays.asList(wordsPerMData)));
		} catch (NumberFormatException | JSONException e) {
				new Statistics();
				e.printStackTrace();
				System.out.println("default user is launched");
				wordGenerator.loadTextFromFile();
		} finally {
				paragraphOfWords = wordGenerator.generateWords(letterLevel);
		}

	}

public void toJsonFile () {
	JsonObjectBuilder UserStats = Json.createObjectBuilder()
	     .add("Number of Errors", this.totalnumErrors)
	     .add("Number of Correct Letters", this.totalnumSuccessful)
	     .add("Typing Accuracy", this.typingAccuracy)
	     .add("Total Time", this.totalTime)
	     .add("letterLevel", this.letterLevel)
	     .add("wordsPerMinute", this.wordsPerMinute)
	     .add("numberofLines", this.numberofLines)
		 .add("score", this.score)
		 .add("goalWPM", this.goalWPM);
	     
	JsonObjectBuilder letterSpeed = Json.createObjectBuilder();
	GsonBuilder gsonBuilder_ = new GsonBuilder();
	Gson gson_ = gsonBuilder_.create();
	UserStats.add("wordsPerMinuteData", gson_.toJson(wordsPerMinuteData));
	UserStats.add("scoreData", gson_.toJson(scoreData));
	UserStats.add("accuracyData", gson_.toJson(accuracyData));
	
	for (Map.Entry<Character, LinkedList<Integer>> entry : characterTimeData.entrySet()) {
	    Character key = entry.getKey();
	    LinkedList<Integer> list = entry.getValue();
	    GsonBuilder gsonBuilder = new GsonBuilder();
	    Gson gson = gsonBuilder.create();
	    letterSpeed.add(String.valueOf(key), gson.toJson(list));
	}
	
	UserStats.add("letterSpeed", letterSpeed);
	JsonObjectBuilder letterErrors = Json.createObjectBuilder();
	
	for (Entry<Character, Integer> entry : characterErrorData.entrySet()) {
	    char key = entry.getKey();
	    Integer list = entry.getValue();
	    GsonBuilder gsonBuilder = new GsonBuilder();
	    Gson gson = gsonBuilder.create();
	    letterErrors.add(String.valueOf(key), gson.toJson(list));
	}
	
	UserStats.add("letterErrors", letterErrors);

	try {
		JsonWriter writer = Json.createWriter(new FileWriter("UserData.json"));
		JsonObject debug = UserStats.build();
		writer.writeObject(debug);
		writer.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}

public void recordData() {
	wordsPerMinute = Math.round((double) (letterIndex-endOfWord)*60000/
			(averageWordSize*(wordTime.time(TimeUnit.MILLISECONDS))));
	wordsPerMinuteData.add(wordsPerMinute);
	wordTime.reset();
	accuracy = (int) 100.0*(letterIndex - endOfWord) / (letterIndex - endOfWord + numErrors);
	accuracyData.add(accuracy);
	score = (int) ((int) wordsPerMinute*accuracy*accuracy/100);
	scoreData.add(score);
	totalTime += lineTime.time(TimeUnit.MILLISECONDS);
	lineTime.reset();

	numberofLines++;
	endOfWord = letterIndex;
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
		if (wordsPerMinuteData.descendingIterator().hasNext()) {
			sum += scoreData.descendingIterator().next();
		} else {
			return 400;
		}
	}
	return sum/5;

}

public Double getRecentWordsperMinute() {
	double sum = 0;
	for (int i = 0; i < 5; ++i) {
		if (wordsPerMinuteData.descendingIterator().hasNext()) {
			sum += wordsPerMinuteData.descendingIterator().next();
		} else {
			return 40.0;
		}
	}
	return sum/5;

}

public Double getRecentAccuracy() {
	double sum = 0;
	for (int i = 0; i < 5; ++i) {
		if (wordsPerMinuteData.descendingIterator().hasNext()) {
			sum += accuracyData.descendingIterator().next();
		} else {
			return 100.0;
		}
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
		sum += it.next();
	}
	return sum/N;
}

public double averageAccuracy() {
	Iterator<Double> it = accuracyData.iterator();
	double sum = 0.0;
	int N = accuracyData.size();
	while (it.hasNext()) {
		sum += it.next();
	}
	return sum/N;
}

public double averageScore() {
	Iterator<Integer> it = scoreData.iterator();
	int sum = 0;
	int N = scoreData.size();
	while (it.hasNext()) {
		sum += it.next();
	}
	return 1.0*sum/N;
}

public double[][] getScoreDataArray(boolean recent) {
	 int N = scoreData.size();
	 double[] time = new double[N];
	 double[] score = new double[N];
	 if (recent) {
		 for (int i = 0; i < N; i++) {
			 if (i == 50) {
				 break;
			 }
			 time[i] = 49 - i;
			 score[i] = scoreData.get(N -1 -i);
		 }
	 } else {
		 for (int i = 0; i < N; i++) {
			 time[i] = i;
			 score[i] = scoreData.get(i);
		 } 
	 }
  return new double[][]{time, score};
}
	 
public double[][] getAccuracyDataArray(boolean recent) {
	 int N = accuracyData.size();
	 double[] time = new double[N];
	 double[] acc = new double[N];
	 if (recent) {
		 for (int i = 0; i < N; i++) {
			 if (i == 50) {
				 break;
			 }
			 time[i] = 49 - i;
			 acc[i] = accuracyData.get(N -1 -i);
		 }
	 } else {
		 for (int i = 0; i < N; i++) {
			 time[i] = i;
			 acc[i] = accuracyData.get(i);
		 }
	 }
	 return new double[][]{time, acc};
}

public double[][] getWordsPerMinuteDataArray(boolean recent) {
	 int N = wordsPerMinuteData.size();
	 double[] time = new double[N];
	 double[] wpm = new double[N];
	 if (recent) {
		 for (int i = 0; i < N; i++) { 
			 if (i == 50) {
				 break;
			 }
			 time[i] = 49 - i;
			 wpm[i] = wordsPerMinuteData.get(N -1 -i);
		 }
	 } else {
		 for (int i = 0; i < N; i++) { 
			 time[i] = i;
			 wpm[i] = wordsPerMinuteData.get(i);
		 }	 
	 }
  return new double[][]{time, wpm};
}

public String validateInput(char input) {
	char correctletter = paragraphOfWords[lineNumber].charAt(letterIndex);
	if (input == correctletter) {	
		long time = letterTime.time(TimeUnit.MILLISECONDS);
		letterTime.reset();
		letterIndex++;

		if (input==' ') {
			recordData();
			return "SPACE";

		} else if (correctletter=='\n') {
			recordData();
			letterIndex = 0;
			endOfWord = 0;
			if (lineNumber == paragraphOfWords.length - 1) {
				lineNumber = 0;
				paragraphOfWords = wordGenerator.generateWords(letterLevel);
				
			} else {
				lineNumber++;
			}
			return "NEWLINE";
		}
		if (characterTimeData.containsKey(input)) {
			 characterTimeData.get(input).add((int)time); 
		 } else {
			 LinkedList<Integer> ll = new LinkedList<Integer>();
			 ll.add((int) time);
			 characterTimeData.put(input, ll);
		 }
			return "CORRECT";
	}
	else {
		totalnumErrors++;
		numErrors++;
		if (characterErrorData.containsKey(correctletter)) {
			 characterErrorData.put(correctletter,characterErrorData.get(correctletter) + 1);
		 } else {
			 if (correctletter >= 96 && correctletter <= 122) {
				 characterErrorData.put(correctletter, 1);
			 }
		 }
		return "INCORRECT";
	}
}

public char getCorrectLetter() {
	
	return paragraphOfWords[lineNumber].charAt(letterIndex);
}

public double getGoal() {
	
	return getRecentWordsperMinute()/goalWPM*100;
}

public int getIndex() {
	
	return letterIndex;
}

public String[] getWordList() {
	
	return paragraphOfWords;
}

public WordGenerator getWordGenerator() {
	
	return wordGenerator;
}

public Map<Character, Integer> getTimeforEachCharacter() {
	Map<Character, Integer> letterTime = new HashMap<Character, Integer>();
	for (Map.Entry<Character, LinkedList<Integer>> entry : characterTimeData.entrySet()) {
		int sum = 0;
		for (int i = 0; i < 5; ++i) {
			if (entry.getValue().descendingIterator().hasNext()) {
				sum += entry.getValue().descendingIterator().next();
			} else {
				letterTime.put(entry.getKey(), 0);
				break;
			}
		}
		letterTime.put(entry.getKey(), sum/5);
		
	}

	return letterTime;
}

public void generateWords(int level) {
	paragraphOfWords = wordGenerator.generateWords(letterLevel);
}

}
