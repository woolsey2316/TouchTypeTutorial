import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

public class TypingPanel extends JPanel implements KeyListener {
	JTextField[] textField = new JTextField[3];
	JLayeredPane[] layeredPane = new JLayeredPane[3];
	JLabel[] textToType = new JLabel[3];
	String[] paragraphOfWords;
	int linenumber = 0;
	String result;
	Model performanceMetrics;

	public TypingPanel(Model performanceMetrics_) {
		performanceMetrics = performanceMetrics_;
		textField = new JTextField[performanceMetrics.getWordGenerator().getNumberOfLines()];
		layeredPane = new JLayeredPane[performanceMetrics.getWordGenerator().getNumberOfLines()];
		textToType = new JLabel[performanceMetrics.getWordGenerator().getNumberOfLines()];
		
		setLayout(new GridBagLayout());
		setBackground(Color.white);
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.gridx = 0;
		
		for (int i = 0; i < textField.length; ++i) {
			gc.gridy = i;
			textField[i] = new JTextField();
			textField[i].setFont(new Font("Palatino", Font.BOLD, 18));
			textField[i].setOpaque(false);
			textField[i].setCaretColor(Color.GREEN);
			textField[i].addKeyListener(this);
			textField[i].setMinimumSize(new Dimension(200, 30));
			textField[i].setBorder(null);
			textField[i].setForeground(Color.WHITE);
			
			textToType[i] = new JLabel(htmlForm(performanceMetrics.getWordList()[i]));
			textToType[i].setFont(new Font("Palatino", Font.BOLD, 18));
			textToType[i].setForeground(Color.GRAY);
			
			disableKeys(textField[i].getInputMap());
			
			add(textField[i], gc);
			add(textToType[i], gc);
			setBackground(new Color(240,240,240));
		}
		textField[0].requestFocusInWindow();	
	}
	
	String processInput(char input) {
		
		result = performanceMetrics.validateInput(input);
		
		if (result == "CORRECT") {
			textField[linenumber].setEditable(true);
			textField[linenumber].setCaretColor(Color.GREEN);
		  
		} else if (result == "NEWLINE") {
			if (linenumber == performanceMetrics.getWordGenerator().getNumberOfLines()-1) {
				linenumber = 0;
				for (int i = 0; i < textField.length; ++i) {
					textField[i].setText("");
					textToType[i].setText(htmlForm(performanceMetrics.getWordList()[i]));
				}
				textField[linenumber].setCaretPosition(0);
			} else {
			linenumber++;
			}
			textField[linenumber].grabFocus();
			textField[linenumber].requestFocus();
		} else if (result == "SPACE") {
			textField[linenumber].setEditable(true);
			textField[linenumber].setCaretColor(Color.GREEN);
			
		}
			else if (result == "INCORRECT") {
			textField[linenumber].setEditable(false);
			textField[linenumber].setCaretColor(Color.RED);
		}
		
		return result;
	}
	
  private void disableKeys(InputMap inputMap) {
    String[] keys = {"UP", "DOWN", "LEFT", "RIGHT", "BACK_SPACE"};
    for (String key : keys) {
        inputMap.put(KeyStroke.getKeyStroke(key), "none");
    }
  }
  
	String htmlForm(String[] str) {
		String htmlstr = "<html>";
		for (int i = 0; i < performanceMetrics.getWordGenerator().getNumberOfLines(); ++i) {
			htmlstr += str[i] + "</br>";
		}
		htmlstr += "</html>";
		return htmlstr;
	}
	
	String htmlForm(String str) {
		return "<html>" + str + "</br>" + "</html>";
	}

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {

		
	}

	public void keyTyped (KeyEvent e) {
		processInput(e.getKeyChar());
			
	}
	
	String getResult() {
		return result;
	}
	
	Model getPerformanceMetrics() {
		return performanceMetrics;
	}
}
