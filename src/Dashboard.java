import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class DashBoard extends JPanel {
	AccuracyPanel accuracyPanel = new AccuracyPanel();
	WordsPerMinutePanel wordsPerMinutePanel= new WordsPerMinutePanel();
	ScorePanel scorePanel = new ScorePanel();
	GoalPanel goalPanel = new GoalPanel(); 
  
  public DashBoard() {

  	add(wordsPerMinutePanel);
  	add(Box.createRigidArea(new Dimension(10,0)));
  	add(accuracyPanel);
  	add(Box.createRigidArea(new Dimension(10,0)));
  	add(scorePanel);
  	add(Box.createRigidArea(new Dimension(10,0)));
  	add(goalPanel);
  	setBackground(new Color(232,232,232));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
