package GUI.VisualElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class ProfilePicture extends JPanel {
	
	ProfilePicture() {
		setPreferredSize(new Dimension(200, 200));
		setMaximumSize(new Dimension(200, 290));
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		int xpos = 0, ypos = 50, xlength =  130, ylength = 130;
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fillOval(xpos, ypos, xlength, ylength);
		
		 try {
	          BufferedImage image = ImageIO.read(getClass()
	        		  .getResourceAsStream("/profile_default.png"));
	          g2.drawImage(image, xpos+5, ypos+5, xlength-10, ylength-10, this);
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
		 
		 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
	}

}
