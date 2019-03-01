import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class User {
	Statistics performanceMetrics;
	String name;
	int wordsPerLine;
	int numberOfLines;
	int goalTypingSpeed;
	double wordsPerMinute;
	double score;
	double accuracy;
	
public User () {
	performanceMetrics = new Statistics();
	goalTypingSpeed = 70;
	numberOfLines = 3;
	wordsPerLine = 6;
	loadUserProfile();

}

public void loadUserProfile () {
	try {
		InputStream is = new FileInputStream("UserData.json");
		String jsonTxt = IOUtils.toString(is, "UTF-8");
		JSONObject json = new JSONObject(jsonTxt);
		performanceMetrics =  new Statistics(json);
		wordsPerMinute = performanceMetrics.averageWordsPerMinute();
		score = performanceMetrics.averageScore();
		accuracy = performanceMetrics.averageAccuracy();
		
		System.out.println("wpm = " + wordsPerMinute);
		System.out.println("Score = " + score);
		System.out.println("Accuracy = " + accuracy);
		
	} catch (FileNotFoundException e) {
		System.out.println("no user exists, loaded default user");
	} catch (IOException e1) {
		e1.printStackTrace();
	} catch (JSONException e) {
		e.printStackTrace();
	}       
        
  }

public Statistics getPerformanceMetrics() {
	return this.performanceMetrics;
}

public void setName(String nm) {
	this.name = nm;
}

public void setWPM(Statistics performanceMetrics) {
	wordsPerMinute = performanceMetrics.averageWordsPerMinute();
}

public void setScore(Statistics performanceMetrics) {
	score = performanceMetrics.averageScore();
}

public void setAccuracy(Statistics performanceMetrics) {
	accuracy = performanceMetrics.averageAccuracy();
}

public String getName() {
	return this.name;
}

public int getWordsPerLine() {
	return wordsPerLine;
}

public int getNumberOfLines() {
	return numberOfLines;
}

public int getGoalTypingSpeed() {
	return goalTypingSpeed;
}

public void setNumberOfLines(String text) {
	performanceMetrics.getWordGenerator().setNumberOfLines(Integer.parseInt(text));
}

public void setGoalwpm(String text) {
	goalTypingSpeed = Integer.parseInt(text);
}

public void setWordsPerLines(String text) {
	performanceMetrics.getWordGenerator().setWordsPerLine(Integer.parseInt(text));
}

}