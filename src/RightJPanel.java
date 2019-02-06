import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class RightJPanel extends JPanel {
	User user;
	SettingsComponent settingsComponent;
	WordGenerator wordGenerator;
	
	public RightJPanel(User user_) {
		user = user_;
		wordGenerator = user.getPerformanceMetrics().getWordGenerator();
		settingsComponent = new SettingsComponent(user);
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Color colour = new Color(20, 25, 28);
		Border border = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.WHITE);
		Border border2 = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE);
		
		JButton jButton2 = new JButton("SETTINGS");
		jButton2.setFont(new Font("Palatino", Font.PLAIN, 18));
		jButton2.setForeground(new Color(255,255,255));
		jButton2.setBackground(colour);
		jButton2.setBorder(border);
	  try {
	    Image img = ImageIO.read(getClass().getResource("images/cog.png"));
	    jButton2.setIcon(new ImageIcon(img));
	  } catch (Exception ex) {
	    System.out.println(ex);
	  }
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.setMaximumSize(new Dimension(300, 40));
		panel2.add(jButton2);
		jButton2.setHorizontalAlignment(SwingConstants.LEFT);
		add(panel2);
	  jButton2.addActionListener(new ActionListener()
	  {
	      public void actionPerformed(ActionEvent e)
	      {
	      	settingsComponent.saveButton.addActionListener( new ActionListener()
	    	  {
	    	      public void actionPerformed(ActionEvent e)
	    	      {
	    	      	remove(settingsComponent);    	      	
	    	      }
	    	  });
	      	add(settingsComponent);
	      }
	  });
		
		JButton jButton3= new JButton("LOAD CUSTOM TEXT");
		jButton3.setFont(new Font("Palatino", Font.PLAIN, 18));
		jButton3.setForeground(new Color(255,255,255));
		jButton3.setBackground(colour);
		jButton3.setBorder(border2);
	  try {
	    Image img = ImageIO.read(getClass().getResource("images/diskette.png"));
	    jButton3.setIcon(new ImageIcon(img));
	  } catch (Exception ex) {
	    System.out.println(ex);
	  }
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.setMaximumSize(new Dimension(300, 40));
		panel3.add(jButton3);
		jButton3.setHorizontalAlignment(SwingConstants.LEFT);
		add(panel3);
	  jButton3.addActionListener( new ActionListener()
	  {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  CustomTextWindow customTextWindow = new CustomTextWindow(wordGenerator);
	    	  customTextWindow.createFrame();
	      }
	  });
		
		setBackground(colour);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
	public SettingsComponent getSettingsComponent() {
		return settingsComponent;
	}

}
