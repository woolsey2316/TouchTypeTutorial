import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.PlainDocument;

public class EditableSetting extends JPanel {
	final int FIELD_WIDTH = 3;
	JTextField field = new JTextField(FIELD_WIDTH);
	JTextPane title = new JTextPane();
	User user;
	
	public EditableSetting(User user_, String titleName, String value) {
		user = user_;
		Color colour = new Color(20, 25, 28);
		PlainDocument doc = (PlainDocument) field.getDocument();
	  doc.setDocumentFilter(new IntegerFilter());
	  title.setText(titleName);
	  title.setEditable(false);
	  title.setFont(new Font("Palatino", Font.PLAIN, 18));
	  title.setForeground(new Color(255,255,255));
	  title.setBackground(colour);
	  
	  field.setForeground(new Color(7,100,143));
	  field.setFont(new Font("Courier new", 1, 18));
	  field.setText(value);
	  add(title);
	  add(field);
		setBackground(colour);
	  
	}
	
	public String getText() {
		return field.getText();
	}
	
	public void setText(String value) {
		  field.setText(value);
	}

}
