package GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AccuracyPanel extends DashBoardComponent{
	
	public AccuracyPanel(String filename) {
		this.fileName = filename;
		thresholdValueExcellent = 95;
		thresholdValueModerate = 85;
		title = "Accuracy\n\n";
		//setBorder(new CustomBorder(new Color(59,119,252)));
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
