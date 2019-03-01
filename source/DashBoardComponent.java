import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class DashBoardComponent extends JTextPane{
	Color colour = new Color(255,255,255);
	String numericalValue;
	protected int thresholdValueExcellent;
	protected int thresholdValueModerate;
	String title;
	StyleContext sc = new StyleContext();
	DefaultStyledDocument doc;
	DecimalFormat df = new DecimalFormat("##.#");
	final Style titleStyle = sc.addStyle("Title", null);
	final Style excellentStyle = sc.addStyle("ExcellentValue", null);
	final Style moderateStyle = sc.addStyle("ModerateValue", null);
	final Style badStyle = sc.addStyle("BadValue", null);

  public DashBoardComponent() {
  	loadDocStyle();
  	setProperties();
	setMaximumSize(new Dimension(200, 75));
  	
  }
  
	public void loadDocStyle() {
  
		titleStyle.addAttribute(StyleConstants.Foreground, new Color(70,70,70));
		titleStyle.addAttribute(StyleConstants.FontSize, new Integer(16));
		titleStyle.addAttribute(StyleConstants.Bold, true);
		titleStyle.addAttribute(StyleConstants.FontFamily, "Segoe");
		  
		
		excellentStyle.addAttribute(StyleConstants.Foreground, new Color(14, 209, 69));
		excellentStyle.addAttribute(StyleConstants.FontSize, new Integer(25));
		
		moderateStyle.addAttribute(StyleConstants.Foreground, Color.orange);
		moderateStyle.addAttribute(StyleConstants.FontSize, new Integer(25));  
		
		badStyle.addAttribute(StyleConstants.Foreground, new Color(236, 28, 36));
		badStyle.addAttribute(StyleConstants.FontSize, new Integer(25));
	  
		try {
			doc = new DefaultStyledDocument(sc);
			StyleConstants.setAlignment(excellentStyle, StyleConstants.ALIGN_LEFT);
			doc.setParagraphAttributes(0, doc.getLength(), excellentStyle, false);        		
			doc.insertString(0, numericalValue, excellentStyle);
			int start = getText().length();
			doc.insertString(start, title, titleStyle);
			setDocument(doc);
			   
			
		} catch (Exception e) {
	      System.out.println("error when constructing document: " + e);
	      System.exit(1);
		}

	}
	
	public void setProperties() {
		setBackground(colour);
		setFocusable(false);
		setEditable(false);
	}
	
  public void setText(String numericalIndicator) {
  	numericalValue = numericalIndicator;
  }
  
	public void setNumericalValue(Double value, boolean percent) {
		try {
			doc.remove(0, doc.getLength());
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		String str;
		if (percent) {
			 str = "  " + df.format(value).toString() + "%";
		} else {
			str = "  " + df.format(value).toString();
		}

		
		try {
	  		if (Math.round(value) > thresholdValueExcellent) {
					doc.insertString(0, str, excellentStyle);
					doc.setParagraphAttributes(0, doc.getLength(), excellentStyle, false); 
	  		} else if (Math.round(value) > thresholdValueModerate) {
	  			doc.insertString(0, str, moderateStyle);
	  			doc.setParagraphAttributes(0, doc.getLength(), excellentStyle, false); 
	  		} else {
	  			doc.insertString(0, str, badStyle);
	  			doc.setParagraphAttributes(0, doc.getLength(), excellentStyle, false); 
	  		}
  			int start = getText().length();
			doc.insertString(start, title, titleStyle);
      
		} catch (BadLocationException e) {
			System.out.println("error when constructing accuracy: " + e);
			System.exit(1);
		}
	}
	
	public void setNumericalValue(int value) {
		try {
			doc.remove(0, doc.getLength());
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		String str = "  " + df.format(value).toString();
		
		try {
	  		if (value > thresholdValueExcellent) {
				doc.insertString(0, str, excellentStyle);
	  		} else if (value > thresholdValueModerate) {
	  			doc.insertString(0, str, moderateStyle);
	  		} else {
	  			doc.insertString(0, str, badStyle);
	  		}
	  		int start = getText().length();
	  		doc.insertString(start, title, titleStyle);
		} catch (BadLocationException e) {
			System.out.println("error when constructing accuracy: " + e);
			System.exit(1);
		}
	}
	
	
}
