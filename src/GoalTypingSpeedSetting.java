import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.PlainDocument;

public class GoalTypingSpeedSetting extends JPanel {
	final int FIELD_WIDTH = 3;
	JTextField goalField = new JTextField(FIELD_WIDTH);
	JTextPane goalFieldTitle = new JTextPane();
	User user;
	
	public GoalTypingSpeedSetting(User user) {
		Color colour = new Color(20, 25, 28);
		PlainDocument doc = (PlainDocument) goalField.getDocument();
	  doc.setDocumentFilter(new IntegerFilter());
	  goalFieldTitle.setText("Goal Typing Speed : ");
	  goalFieldTitle.setEditable(false);
	  goalFieldTitle.setFont(new Font("Palatino", Font.PLAIN, 18));
	  goalFieldTitle.setForeground(new Color(255,255,255));
	  goalFieldTitle.setBackground(colour);
	  
	  goalField.setText(Integer.toString(user.getGoalTypingSpeed()));
	  goalField.setForeground(Color.GREEN);
	  add(goalFieldTitle);
	  add(goalField);
		setBackground(colour);
	  
	}
	
	public String getText() {
		return goalField.getText();
	}

}
