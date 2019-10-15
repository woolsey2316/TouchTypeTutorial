package GUI;
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
	protected String fileName;
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
  	setPreferredSize(new Dimension(175, 100));
	setMaximumSize(new Dimension(200, 105));
  	
  }
  
	public void loadDocStyle() {
  
		titleStyle.addAttribute(StyleConstants.Foreground, new Color(70,70,70));
		titleStyle.addAttribute(StyleConstants.FontSize, new Integer(16));
		titleStyle.addAttribute(StyleConstants.Bold, true);
		//titleStyle.addAttribute(StyleConstants.LineSpacing,1.0f);
		titleStyle.addAttribute(StyleConstants.FontFamily, "Segoe");
		  
		excellentStyle.addAttribute(StyleConstants.Foreground, new Color(14, 209, 69));
		excellentStyle.addAttribute(StyleConstants.FontSize, new Integer(25));
		
		moderateStyle.addAttribute(StyleConstants.Foreground, Color.orange);
		moderateStyle.addAttribute(StyleConstants.FontSize, new Integer(25));  
		
		badStyle.addAttribute(StyleConstants.Foreground, new Color(236, 28, 36));
		badStyle.addAttribute(StyleConstants.FontSize, new Integer(25));
	  
		try {
			doc = new DefaultStyledDocument(sc);
			StyleConstants.setAlignment(excellentStyle, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, doc.getLength(), excellentStyle, false);        		
			
			doc.insertString(0, title, titleStyle);
			int start = getText().length()-2;
			
			doc.insertString(start, numericalValue, excellentStyle);
			
			setDocument(doc);
			   
			
		} catch (Exception e) {
	      System.out.println("error when constructing document: " + e);
	      System.exit(1);
		}

	}
	
	public void setProperties() {
		setOpaque(false);
		//setBackground(colour);
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
			 str = df.format(value).toString() + "%";
		} else {
			str = df.format(value).toString();
		}


		try {
			
			doc.insertString(0, title, titleStyle);
			int start = getText().length()-2;
	  		if (Math.round(value) > thresholdValueExcellent) {
				doc.insertString(start, str, excellentStyle);
				doc.setParagraphAttributes(start, doc.getLength(), excellentStyle, false); 
	  		} else if (Math.round(value) > thresholdValueModerate) {
	  			doc.insertString(start, str, moderateStyle);
	  			doc.setParagraphAttributes(start, doc.getLength(), excellentStyle, false); 
	  		} else {
	  			doc.insertString(start, str, badStyle);
	  			doc.setParagraphAttributes(start, doc.getLength(), excellentStyle, false); 
	  		}

      
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
		String str = df.format(value).toString();
		
		try {
	  		doc.insertString(0, title, titleStyle);
	  		int start = getText().length()-2;
	  		if (value > thresholdValueExcellent) {
				doc.insertString(start, str, excellentStyle);
	  		} else if (value > thresholdValueModerate) {
	  			doc.insertString(start, str, moderateStyle);
	  		} else {
	  			doc.insertString(start, str, badStyle);
	  		}

		} catch (BadLocationException e) {
			System.out.println("error when constructing accuracy: " + e);
			System.exit(1);
		}
	}
	
	
}
