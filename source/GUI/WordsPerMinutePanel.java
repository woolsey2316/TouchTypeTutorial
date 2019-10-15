package GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WordsPerMinutePanel extends DashBoardComponent{
	String fileName;
	
	public WordsPerMinutePanel(String filename) {
		this.fileName = filename;
		thresholdValueExcellent = 35;
		thresholdValueModerate = 20;
		title = "WPM\n\n";
		//setBorder(new CustomBorder(new Color(31,180,251)));
		loadDocStyle();
	}
	
	  @Override
	  protected void paintComponent(Graphics g) {
		  if (this.fileName == null) {return;}
	      try {
	          BufferedImage image = ImageIO.read(getClass()
	        		  .getResourceAsStream("/dash3.png"));
	          g.drawImage(image, 0, 0, (int) getSize().getWidth(),
	              (int) getSize().getHeight(), this);
	      } catch (IOException e) {
	          e.printStackTrace();
	      }

	      super.paintComponent(g);
	  }
}
