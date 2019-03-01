import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

public class EditableSetting extends JTextField {

	
	public EditableSetting(String value) {
		PlainDocument doc = (PlainDocument) getDocument();
		doc.setDocumentFilter(new IntegerFilter());
			  
		setForeground(new Color(7,100,143));
		setFont(new Font("Courier new", 1, 18));
		setText(value);
		setBackground(Color.white);
	  
	}

}
