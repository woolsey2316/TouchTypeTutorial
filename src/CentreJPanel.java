import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CentreJPanel extends JPanel {
	TypingPanel typingPanel;
	DashBoard2 dashboard;
	JTextField[] textField = new JTextField[3];
	JLayeredPane[] layeredPane = new JLayeredPane[3];
	JLabel[] textToType = new JLabel[3];
	String[] paragraphOfWords;
	int linenumber = 0;
	String result;
	
	public CentreJPanel() {
		setLayout(new BoxLayout(this, 1));
		setBackground(Color.white);
		setBorder(null);
		
		typingPanel = new TypingPanel();
		dashboard = new DashBoard2();
		add(dashboard);
		add(typingPanel);

	}
	
	Model getPerformanceMetrics() {
		return typingPanel.getPerformanceMetrics();
	}
	
	void setAccuracyValue(Double accuracy) {
		dashboard.setAccuracyValue(accuracy);
	}
	
	void setWpmValue(Double wpm) {
		dashboard.setWpmValue(wpm);
	}
	
	void setScoreValue(int score) {
		dashboard.setScoreValue(score);
	}
	
	TypingPanel getTypingPanel() {
		return typingPanel;
	}
	
	DashBoard2 getDashBoard() {
		return dashboard;
	}
	
	void refreshDisplay() {
		dashboard.setAccuracyValue(typingPanel.getPerformanceMetrics().getRecentAccuracy());
		dashboard.setWpmValue(typingPanel.getPerformanceMetrics().getRecentWordsperMinute());
		dashboard.setScoreValue(typingPanel.getPerformanceMetrics().getRecentScore());
	}
}