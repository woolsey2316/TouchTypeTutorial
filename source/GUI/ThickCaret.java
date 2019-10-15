package GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

public class ThickCaret extends DefaultCaret {

	public ThickCaret() {
		setBlinkRate(500);
	}
  protected synchronized void damage(Rectangle rect) {
  	JTextComponent component = getComponent();
    if (rect == null)
      return;

    x = rect.x;
    y = rect.y;
    height = rect.height;
    if (width <= 0)
      width = component.getWidth();

    repaint();
  }

  public void paint(Graphics g) {
  	JTextComponent component = getComponent();
    if (component == null)
      return;

    int dot = getDot();
    Rectangle rect;
    char dotChar;
    try {
      rect = component.modelToView(dot);
      if (rect == null)
        return;
      dotChar = component.getText(dot, 1).charAt(0);
    } catch (BadLocationException e) {
      return;
    }

    if ((x != rect.x) || (y != rect.y)) {
    	// erase the previous location of caret
      repaint(); 
      x = rect.x;
      y = rect.y;
      height = rect.height;
    }
    float[] comp = component.getCaretColor().getRGBComponents(null);
    Color colour = new Color(comp[0],comp[1],comp[2],1f);
    g.setColor(colour);
    g.setXORMode(component.getBackground());

    if (dotChar == '\n') {
      int diam = rect.height;
      if (isVisible())
      	g.fillRect(rect.x, rect.y, width, rect.height);
      width = diam / 2 + 2;
      return;
    }

    if (dotChar == '\t')
      try {
        Rectangle nextr = component.modelToView(dot + 1);
        if ((rect.y == nextr.y) && (rect.x < nextr.x)) {
          width = nextr.x - rect.x;
          if (isVisible())
            g.fillRoundRect(rect.x, rect.y, width, rect.height, 12, 12);
          return;
        } else
          dotChar = ' ';
      } catch (BadLocationException e) {
        dotChar = ' ';
      }

    width = g.getFontMetrics().charWidth(dotChar);
    if (isVisible())
      g.fillRect(rect.x, rect.y, width, rect.height);
  }
}