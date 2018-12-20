import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.PlainDocument;

public class NumberOfLinesSetting extends JPanel {
	final int FIELD_WIDTH = 3;
	JTextField numberoflinesField = new JTextField(FIELD_WIDTH);
	JTextPane numberoflinesFieldTitle = new JTextPane();
	User user;
	
	public NumberOfLinesSetting(User user) {
		Color colour = new Color(20, 25, 28);
		PlainDocument doc = (PlainDocument) numberoflinesField.getDocument();
	  doc.setDocumentFilter(new IntegerFilter());
	  numberoflinesFieldTitle.setText("Number of Lines : ");
	  numberoflinesFieldTitle.setEditable(false);
	  numberoflinesFieldTitle.setFont(new Font("Palatino", Font.PLAIN, 18));
	  numberoflinesFieldTitle.setForeground(new Color(255,255,255));
	  numberoflinesFieldTitle.setBackground(colour);
	  
	  numberoflinesField.setText(Integer.toString(user.getNumberOfLines()));
	  numberoflinesField.setForeground(Color.GREEN);
	  add(numberoflinesFieldTitle);
	  add(numberoflinesField);
		setBackground(colour);
	}
	
	public String getText() {
		return numberoflinesField.getText();
	}

}
