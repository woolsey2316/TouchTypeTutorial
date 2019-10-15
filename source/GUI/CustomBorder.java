package GUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Rectangle2D;

import javax.swing.border.AbstractBorder;

class CustomBorder extends AbstractBorder
{
    private Color borderColour;
    private static final int THICKNESS = 1;

    public CustomBorder(Color colour)
    {
        borderColour = colour;
        
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y
                                                   , int width
                                                   , int height)
    {
        super.paintBorder(c, g, x, y, width, height);
        Graphics2D g2d = null;
        if (g instanceof Graphics2D)
        {
            g2d = (Graphics2D) g;
            g2d.setColor(new Color(180,180,180));
            /* Top Border
            g2d.fill(new Rectangle2D.Double(
                (double)x
              , (double)y
              , width, THICKNESS));
            // Right Border
            g2d.fill(new Rectangle2D.Double(
                (double)width - THICKNESS 
              , (double)y
              , THICKNESS, height));
            
            // Bottom Border
            g2d.fill(new Rectangle2D.Double(
                (double)x
              , (double)height-THICKNESS
              , width, THICKNESS));
              */
            g2d.setColor(borderColour);
            // Blue, Left Border
            g2d.fill(new Rectangle2D.Double(
                (double)x
              , (double)y
              , 3, height));
        }
    }
    
    @Override
    public Insets getBorderInsets(Component c) 
    {
        return new Insets(THICKNESS, THICKNESS, THICKNESS, THICKNESS);
    }
}