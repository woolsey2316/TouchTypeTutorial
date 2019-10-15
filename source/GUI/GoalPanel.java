package GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GoalPanel extends DashBoardComponent{
	
	public GoalPanel(String filename) {
		this.fileName = filename;
		thresholdValueExcellent = 70;
		thresholdValueModerate = 50;
		title = "Goal Typing Speed\n\n";
		//setBorder(new CustomBorder(new Color(30,97,178)));
		loadDocStyle();
	}
	
	  @Override
	  protected void paintComponent(Graphics g) {
		  if (fileName == null) {
			  return;}
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