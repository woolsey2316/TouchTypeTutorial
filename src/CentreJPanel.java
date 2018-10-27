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
	Model performanceMetrics;
	
	public CentreJPanel(Model performanceMetrics_) {
		setLayout(new BoxLayout(this, 1));
		setBackground(Color.white);
		setBorder(null);
		
		typingPanel = new TypingPanel(performanceMetrics_);
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
}