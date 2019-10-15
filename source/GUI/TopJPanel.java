package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TopJPanel extends JPanel {
	public TopJPanel() {
		
		setLayout(new GridBagLayout());
		  
		JLabel jLabel = new JLabel("Rapid Touch-Typer");
		jLabel.setFont(new Font("Palatino", Font.BOLD, 25));
		jLabel.setForeground(new Color(240,240,240));
		jLabel.setMaximumSize(new Dimension(300,40));
		jLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		add(jLabel);
		
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource("../logo.png"));
			ImageIcon icon = new ImageIcon(img);
			JLabel logo = new JLabel(icon);
		    add(logo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setBackground(new Color(7,100,143));
		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
	}

}
