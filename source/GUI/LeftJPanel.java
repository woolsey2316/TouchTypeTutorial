package GUI;
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

import Model.User;
import Model.UserProfile;
import Model.WordGenerator;

public class LeftJPanel extends JPanel {
	User user;
	SettingsComponent settingsComponent;
	CustomTextWindow customTextWindow;
	WordGenerator wordGenerator;
	
	public LeftJPanel(User user_) {
		user = user_;
		wordGenerator = user.getPerformanceMetrics().getWordGenerator();
		settingsComponent = new SettingsComponent(user);
		customTextWindow = new CustomTextWindow(wordGenerator);
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Color colour = new Color(20, 25, 28);
		Color grey = new Color(230,230,230);
		
		final JButton jButton2 = new JButton(" Settings");
		jButton2.setFont(new Font("Palatino", Font.PLAIN, 18));
		jButton2.setForeground(grey);
		jButton2.setBackground(colour);
		jButton2.setFocusable(false);
		
		jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	jButton2.setBackground(new Color(43, 47, 50));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	jButton2.setBackground(new Color(20, 25, 28));
		    }
		});
		
	  try {
	    Image img = ImageIO.read(getClass().getResource("../cog.png"));
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
		    	      	settingsComponent.closeFrame();
		    	      }
		    	  });
		      	add(settingsComponent);
		      	settingsComponent.createFrame();
		      }
		  });
		
		final JButton jButton3= new JButton("  Load Custom Text");
		jButton3.setFont(new Font("Palatino", Font.PLAIN, 18));
		jButton3.setForeground(grey);
		jButton3.setBackground(colour);
		jButton3.setFocusable(false);
		
		jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	jButton3.setBackground(new Color(43, 47, 50));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	jButton3.setBackground(new Color(20, 25, 28));
		    }
		});
		
	  try {
	    Image img = ImageIO.read(getClass().getResource("../diskette.png"));
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
	    	  customTextWindow.createFrame();
	      }
	  });
		final JButton jButton4= new JButton(" User Profile");
		jButton4.setFont(new Font("Palatino", Font.PLAIN, 18));
		jButton4.setForeground(grey);
		jButton4.setBackground(colour);
		jButton4.setFocusable(false);
		
		jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	jButton4.setBackground(new Color(43, 47, 50));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	jButton4.setBackground(new Color(20, 25, 28));
		    }
		});
		
	  try {
	    Image img = ImageIO.read(getClass().getResource("../user.png"));
	    jButton4.setIcon(new ImageIcon(img));
	  } catch (Exception ex) {
	    System.out.println(ex);
	  }
		JPanel panel4 = new JPanel(new BorderLayout());
		panel4.setMaximumSize(new Dimension(300, 40));
		panel4.add(jButton4);
		jButton4.setHorizontalAlignment(SwingConstants.LEFT);
		add(panel4);
		jButton4.addActionListener( new ActionListener()
	  {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  new UserProfile(user);
	      }
	  });
		
		setBackground(new Color(20, 25, 28));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
	public SettingsComponent getSettingsComponent() {
		return settingsComponent;
	}
	
	public CustomTextWindow getCustomTextWindow() {
		return customTextWindow;
	}

}
