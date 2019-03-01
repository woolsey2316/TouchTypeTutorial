import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.InputMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class TypingPanel extends JPanel implements KeyListener {
	LinkedList<JTextField> typingField = new LinkedList<JTextField>();
	JTextField currentField;
	LinkedList<JLabel> textToType = new LinkedList<JLabel>();
	int linenumber = 0;
	String result;
	User user;
	Statistics performanceMetrics;
	WordGenerator wordGenerator;
	GridBagConstraints gc;
	boolean focusGained;

	public TypingPanel(User user_) {
		user = user_;
		performanceMetrics = user.getPerformanceMetrics();
		wordGenerator = performanceMetrics.getWordGenerator();
		
		setLayout(new GridBagLayout());
		setBackground(new Color(232,232,232));
		gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.gridx = 0;
		
		for (int i = 0; i < wordGenerator.getNumberOfLines(); ++i) {
			gc.gridy = i;
			addTextField();
		}
		typingField.get(0).setEditable(true);
		typingField.get(0).requestFocusInWindow();

		focusGained = true;
		
	}
	
	public void addTextField() {
		JTextField textField = new JTextField();
		textField.setFont(new Font("Courier New", Font.BOLD, 24));
		textField.setOpaque(false);
		textField.setCaretColor(new Color(61,239,16,50));
		textField.addKeyListener(this);
		textField.setMinimumSize(new Dimension(200, 30));
		textField.setBorder(null);
		textField.setForeground(new Color(232,232,232));
		textField.setCaret(new ThickCaret());
		textField.setEditable(false);
		
		typingField.add(textField);
		
		JLabel texttoType = new JLabel(htmlForm(performanceMetrics.getWordList()[typingField.size()-1]));
		texttoType.setFont(new Font("Courier New", Font.BOLD, 24));
		texttoType.setForeground(Color.GRAY);
		
		textToType.add(texttoType);
		
		disableKeys(textField.getInputMap());
		
		add(textField, gc);
		add(texttoType, gc);
	}
	
  public void focusGained(FocusEvent e) {
		focusGained = true;
	}
	
	public void focusLost(FocusEvent e) {
		focusGained = false;
	}
	
	public boolean isfocusGained() {
		return focusGained;
	}
		
	String processInput(char input) {
		
		result = performanceMetrics.validateInput(input);
		
		if (result == "CORRECT") {
			typingField.get(linenumber).setEditable(true);
			typingField.get(linenumber).setCaretColor(new Color(61, 239, 16, 50));
		  
		} else if (result == "NEWLINE") {
			typingField.get(linenumber).setEditable(false);
			if (linenumber == wordGenerator.getNumberOfLines()-1) {
				linenumber = 0;
				int i = 0;
				for (JLabel elem : textToType) {
					elem.setText(htmlForm(performanceMetrics.getWordList()[i]));
					i++;
				}
				for (JTextField elem : typingField) {
					elem.setText("");
					i++;
				}
			} else {
				linenumber++;
			}
			typingField.get(linenumber).requestFocusInWindow();
			typingField.get(linenumber).setEditable(true);
		} else if (result == "SPACE") {
			typingField.get(linenumber).setEditable(true);
			typingField.get(linenumber).setCaretColor(new Color(61, 239, 16, 50));
			
		}
			else if (result == "INCORRECT") {
			typingField.get(linenumber).setEditable(false);
			typingField.get(linenumber).setCaretColor(new Color(239, 16, 16, 50));
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
		for (int i = 0; i < wordGenerator.getNumberOfLines(); ++i) {
			htmlstr += str[i] + "</br>";
		}
		htmlstr += "</html>";
		return htmlstr;
	}
	
	public void updateText() {
		performanceMetrics.generateWords(0);
		for (JTextField elem : typingField) {
			remove(elem);
		}
		for (JLabel elem : textToType) {
			elem.setText("");
		}
		typingField.clear();
		textToType.clear();
		
		for (int i = 0; i < wordGenerator.getNumberOfLines(); ++i) {
			gc.gridy = i;
			addTextField();
		}
		typingField.get(0).setEditable(true);
		typingField.get(0).requestFocusInWindow();
		typingField.get(0).setCaretPosition(0);
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
	
	Statistics getPerformanceMetrics() {
		return performanceMetrics;
	}
}
