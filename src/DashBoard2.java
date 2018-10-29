import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.text.DecimalFormat;

public class DashBoard2 extends JPanel{
	Color colour = new Color(240, 240, 240);
	String[][] text = new String[3][2];
	String[] title = {"\nAccuracy", "\nWords Per Minute", "\nScore"};
	StyleContext sc = new StyleContext();
	DefaultStyledDocument[] doc = new DefaultStyledDocument[3];
	JTextPane[] dashboard = new JTextPane[3];
	DecimalFormat df = new DecimalFormat("##.#");
  final Style titleStyle = sc.addStyle("Title", null);
  final Style excellentStyle = sc.addStyle("ExcellentValue", null);
  final Style moderateStyle = sc.addStyle("ModerateValue", null);
  final Style badStyle = sc.addStyle("BadValue", null);
    
	public DashBoard2() {
    	
      titleStyle.addAttribute(StyleConstants.Foreground, Color.black);
      titleStyle.addAttribute(StyleConstants.FontSize, new Integer(18));

      excellentStyle.addAttribute(StyleConstants.Foreground, Color.green);
      excellentStyle.addAttribute(StyleConstants.Bold, new Boolean(true));
      excellentStyle.addAttribute(StyleConstants.FontSize, new Integer(40));

      moderateStyle.addAttribute(StyleConstants.Foreground, Color.orange);
      moderateStyle.addAttribute(StyleConstants.Bold, new Boolean(true));
      moderateStyle.addAttribute(StyleConstants.FontSize, new Integer(40));  

      badStyle.addAttribute(StyleConstants.Foreground, Color.red);
      badStyle.addAttribute(StyleConstants.Bold, new Boolean(true));
      badStyle.addAttribute(StyleConstants.FontSize, new Integer(40));

      try {
          SwingUtilities.invokeAndWait(new Runnable() {
              public void run() {
                  try {
                  	for (int j = 0; j < 3; ++j) {
                  		text[j][0] = "0";
                    	text[j][1] = title[j];
                  			doc[j] = new DefaultStyledDocument(sc);
                        StyleConstants.setAlignment(excellentStyle, StyleConstants.ALIGN_CENTER);
                        doc[j].setParagraphAttributes(0, doc[j].getLength(), excellentStyle, false);
                    		dashboard[j] = new JTextPane(doc[j]);
                    		dashboard[j].setBackground(colour);
                    		dashboard[j].setFocusable(false);
                    		
                    		doc[j].insertString(0, text[j][0], excellentStyle);
                        int start = dashboard[j].getText().length();
                        doc[j].insertString(start, text[j][1], titleStyle);
                        add(dashboard[j]);
                  	}
                  } catch (BadLocationException e) {
                  }
              }
          });
      } catch (Exception e) {
          System.out.println("error when constructing document: " + e);
          System.exit(1);
      }
      setLayout(new GridLayout(1,3));
  		setBackground(colour);
}
    
    void setText(int index, String numericalIndicator) {
    	text[index][0] = numericalIndicator;
    	text[index][1] = title[index];	
    }
    
  	void setAccuracyValue(Double accuracy) {
      int index = 0;
  		dashboard[index].setText("");
  		setText(index, df.format(accuracy).toString() + "%");
  		
  		try {
	  		if (accuracy > 97) {
					doc[index].insertString(0, text[0][0], excellentStyle);
	  		} else if (accuracy > 80) {
	  			doc[index].insertString(0, text[0][0], moderateStyle);
	  		} else {
	  			doc[index].insertString(0, text[0][0], badStyle);
	  		}
	  		
	      int start = dashboard[0].getText().length();
	      doc[index].insertString(start, text[0][1], titleStyle);
			} catch (BadLocationException e) {
        System.out.println("error when constructing accuracy: " + e);
        System.exit(1);
			}
  	}
  	
  	void setWpmValue(Double wpm) {
      int index = 1;
  		dashboard[index].setText("");
  		setText(index, wpm.toString());
  		
  		try {
	  		if (wpm > 30) {
	  			doc[index].insertString(0, text[1][0], excellentStyle);
	  		} else if (wpm > 20) {
	  			doc[index].insertString(0, text[1][0], moderateStyle);
	  		} else {
	  			doc[index].insertString(0, text[1][0], badStyle);
	  		}
	  		int start = dashboard[1].getText().length();
	  		doc[index].insertString(start, text[1][1], titleStyle);
			} catch (BadLocationException e) {
        System.out.println("error when constructing wpm: " + e);
        System.exit(1);
			}
  	}
  	
  	void setScoreValue(Integer score) {
  		int index = 2;
  		dashboard[index].setText("");
      int start = 0;
  		setText(index, score.toString());
  		
  		try {
	  		if (score > 30) {
	  			doc[index].insertString(start, text[2][0], excellentStyle);
	  		} else if (score > 20) {
	  			doc[index].insertString(start, text[2][0], moderateStyle);
	  		} else {
	  			doc[index].insertString(start, text[2][0], badStyle);
	  		}
        start = dashboard[2].getText().length();
        doc[index].insertString(start, text[2][1] , titleStyle);
			} catch (BadLocationException e) {
        System.out.println("error when constructing document: " + e);
        System.exit(1);
			}		
  	}
}