import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class CentreJPanel extends JPanel {
	private TypingPanel typingPanel;
	private DashBoard dashboard;
	
	public CentreJPanel(User user) {
		setLayout(new BoxLayout(this, 1));
		setBackground(new Color(232,232,232));

		typingPanel = new TypingPanel(user);
		dashboard = new DashBoard();
		refreshDisplay();
		add(dashboard);
		add(typingPanel);

	}
	
	Statistics getPerformanceMetrics() {
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