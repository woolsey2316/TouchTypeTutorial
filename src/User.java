import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class User {
	Model performanceMetrics;
	String name;
	double wordsPerMinute;
	double score;
	double accuracy;
	
public User () {
	performanceMetrics = new Model();

}

public User (JSONObject username) {
	performanceMetrics =  new Model(username);
	wordsPerMinute = performanceMetrics.averageWordsPerMinute();
	score = performanceMetrics.averageAccuracy();
	accuracy = performanceMetrics.averageScore();

}

public void loadUserProfile () {
			try {
				InputStream is = new FileInputStream("userData.json");
				String jsonTxt = IOUtils.toString(is, "UTF-8");
				JSONObject json = new JSONObject(jsonTxt);
				new User(json);
			} catch (FileNotFoundException e) {
				System.out.println("no user exists, loaded default user");
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}       
        
  }

public Model getPerformanceMetrics() {
	return this.performanceMetrics;

}

public void setName(String nm) {
	this.name = nm;
	
}

public void setWPM(Model performanceMetrics) {
	wordsPerMinute = performanceMetrics.averageWordsPerMinute();
}

public void setScore(Model performanceMetrics) {
	score = performanceMetrics.averageScore();
}

public void setAccuracy(Model performanceMetrics) {
	accuracy = performanceMetrics.averageAccuracy();
}

public String getName() {
	return this.name;
	
}

}