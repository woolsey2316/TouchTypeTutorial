import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class TopJPanel extends JPanel {
	DecimalFormat df = new DecimalFormat("##.#");
	JLabel panelWPMValue = new JLabel("___", JLabel.CENTER);
	JLabel panelAccuracyValue = new JLabel("___", JLabel.CENTER);
	JLabel panelScoreValue = new JLabel("_____", JLabel.CENTER);
	
	public TopJPanel() {
		Font typingFont = new Font("Palatino", Font.BOLD, 18);
		Font metricFont = new Font("Maven Pro", Font.PLAIN, 40);
		
		setLayout(new FlowLayout());
		setBackground(Color.white);
	
		//top Panel
		JLabel accuracyTitle = new JLabel("Accuracy", JLabel.CENTER);
		accuracyTitle.setFont(new Font("Palatino", Font.BOLD, 12));
	
		add(accuracyTitle);
		
		panelAccuracyValue.setForeground(Color.BLACK);
		panelAccuracyValue.setFont(metricFont);
		add(panelAccuracyValue);
	
		JLabel wordsPerMinuteTitle = new JLabel("Words Per Minute", JLabel.CENTER);
		wordsPerMinuteTitle.setFont(new Font("Palatino", Font.BOLD, 12));
		add(wordsPerMinuteTitle);
	
		panelWPMValue.setForeground(Color.BLACK);
		panelWPMValue.setFont(metricFont);
		add(panelWPMValue);
		
		JLabel scoreTitle = new JLabel("Score", JLabel.CENTER);
		scoreTitle.setFont(new Font("Palatino", Font.BOLD, 12));
		add(scoreTitle);
		
		panelScoreValue.setForeground(Color.BLACK);
		panelScoreValue.setFont(metricFont);
		add(panelScoreValue);
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
	}
	
	void setAccuracyValue(Double accuracy) {
		panelAccuracyValue.setText(df.format(accuracy) +"%");
		if (accuracy > 99) {
			panelAccuracyValue.setForeground(Color.GREEN);
		} else if (accuracy > 80) {
			panelAccuracyValue.setForeground(Color.ORANGE);
		} else {
			panelAccuracyValue.setForeground(Color.RED);
		}
		
	}
	
	void setWpmValue(Double wpm) {
		if (wpm > 30) {
			panelWPMValue.setForeground(Color.GREEN);
		} else if (wpm > 20) {
			panelWPMValue.setForeground(Color.ORANGE);
		} else {
			panelWPMValue.setForeground(Color.RED);
		}
		
		panelWPMValue.setText(df.format(wpm));
	}
		
}

