import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.PlainDocument;

public class WordsPerLineSetting extends JPanel {
	final int FIELD_WIDTH = 3;
	JTextField wordsperlineField = new JTextField(FIELD_WIDTH);
	JTextPane wordsperlineFieldTitle = new JTextPane();
	User user;
	
	public WordsPerLineSetting(User user) {
		Color colour = new Color(20, 25, 28);
		PlainDocument doc = (PlainDocument) wordsperlineField.getDocument();
	  doc.setDocumentFilter(new IntegerFilter());
	  wordsperlineFieldTitle.setText("Words per Line : ");
	  wordsperlineFieldTitle.setEditable(false);
	  wordsperlineFieldTitle.setFont(new Font("Palatino", Font.PLAIN, 18));
	  wordsperlineFieldTitle.setForeground(new Color(255,255,255));
	  wordsperlineFieldTitle.setBackground(colour);

	  wordsperlineField.setText(Integer.toString(user.getWordsPerLine()));
	  wordsperlineField.setForeground(Color.GREEN);
	  add(wordsperlineFieldTitle);
	  add(wordsperlineField);
		setBackground(colour);
	}
	
	public String getText() {
		return wordsperlineField.getText();
	}

}
