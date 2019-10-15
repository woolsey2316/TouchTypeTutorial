package GUI;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class DashBoard extends JPanel {
	AccuracyPanel accuracyPanel = new AccuracyPanel("dash1.png");
	WordsPerMinutePanel wordsPerMinutePanel= new WordsPerMinutePanel("dash2.png");
	ScorePanel scorePanel = new ScorePanel("dash3.png");
	GoalPanel goalPanel = new GoalPanel("dash4.png"); 
  
  public DashBoard() {

  	add(wordsPerMinutePanel);
  	add(Box.createRigidArea(new Dimension(10,0)));
  	add(accuracyPanel);
  	add(Box.createRigidArea(new Dimension(10,0)));
  	add(scorePanel);
  	add(Box.createRigidArea(new Dimension(10,0)));
  	add(goalPanel);
  	setBackground(new Color(247,240,240));
	setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 10));
  	setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
  }
	
	public AccuracyPanel getAccuracyPanel() {
		return accuracyPanel;
	}
	
	public WordsPerMinutePanel getWordsPerMinutePanel() {
		return wordsPerMinutePanel;
	}
	
	public ScorePanel getScorePanel() {
		return scorePanel;
	}
	
	public GoalPanel getGoalPanel() {
		return goalPanel;
	}

}
