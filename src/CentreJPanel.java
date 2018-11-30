import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CentreJPanel extends JPanel {
	TypingPanel typingPanel;
	DashBoard dashboard;
	String[] paragraphOfWords;
	int linenumber = 0;
	
	public CentreJPanel() {
		setLayout(new BoxLayout(this, 1));
		setBackground(new Color(232,232,232));

		typingPanel = new TypingPanel();
		dashboard = new DashBoard();
		refreshDisplay();
		add(dashboard);
		add(typingPanel);

	}
	
	Model getPerformanceMetrics() {
		return typingPanel.getPerformanceMetrics();
	}
	
	void setAccuracyValue(Double accuracy) {
		dashboard.getAccuracyPanel().setNumericalValue(accuracy, true);
	}
	
	void setWpmValue(Double wpm) {
		dashboard.getWordsPerMinutePanel().setNumericalValue(wpm, false);
	}
	
	void setScoreValue(int score) {
		dashboard.getScorePanel().setNumericalValue(score);
	}
	
	void setGoalValue(double goal) {
		dashboard.getGoalPanel().setNumericalValue(goal, true);
	}
	
	TypingPanel getTypingPanel() {
		return typingPanel;
	}
	
	DashBoard getDashBoard() {
		return dashboard;
	}
	
	void refreshDisplay() {
		setAccuracyValue(typingPanel.getPerformanceMetrics().getRecentAccuracy());
		setWpmValue(typingPanel.getPerformanceMetrics().getRecentWordsperMinute());
		setScoreValue(typingPanel.getPerformanceMetrics().getRecentScore());
		setGoalValue(typingPanel.getPerformanceMetrics().getGoal());
	}
}