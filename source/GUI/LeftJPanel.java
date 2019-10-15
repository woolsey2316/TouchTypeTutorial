package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import GUI.VisualElements.Profile;
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
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Color colour = new Color(57, 55, 64);
		Color grey = new Color(230,230,230);
		
		Profile profilePanel= new Profile();
		add(profilePanel);
		
		JPanel padding = new JPanel(new BorderLayout());
		padding.setMaximumSize(new Dimension(300, 40));
		padding.setBackground(new Color(0,0,0,0));
		add(padding);
		
		final JButton settingsButton = new JButton("Settings");
		
		Font font = new Font("segoe", Font.PLAIN, 18);
		Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
		attributes.put(TextAttribute.TRACKING, 0.05);
		Font font2 = font.deriveFont(attributes);
		
		settingsButton.setFont(font2);		
		settingsButton.setForeground(grey);
		settingsButton.setBackground(colour);
		settingsButton.setBorder(null);
		settingsButton.setFocusable(false);
		
		settingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	settingsButton.setBackground(new Color(43, 47, 50));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	settingsButton.setBackground(colour);
		    }
		});
		
		JPanel settingsPanel = new JPanel(new BorderLayout());
		settingsPanel.setMaximumSize(new Dimension(300, 40));
		settingsPanel.add(settingsButton);
		settingsButton.setHorizontalAlignment(SwingConstants.LEFT);
		add(settingsPanel);
		  settingsButton.addActionListener(new ActionListener()
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
		
		final JButton customTextButton= new JButton("Load Custom Text");
		customTextButton.setFont(font2);
		customTextButton.setForeground(grey);
		customTextButton.setBackground(colour);
		customTextButton.setBorder(null);
		customTextButton.setFocusable(false);
		
		customTextButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	customTextButton.setBackground(new Color(43, 47, 50));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	customTextButton.setBackground(colour);
		    }
		});
		
		JPanel customTextPanel = new JPanel(new BorderLayout());
		customTextPanel.setMaximumSize(new Dimension(300, 40));
		customTextPanel.add(customTextButton);
		customTextButton.setHorizontalAlignment(SwingConstants.LEFT);
		add(customTextPanel);
	  customTextButton.addActionListener( new ActionListener()
	  {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  customTextWindow.createFrame();
	      }
	  });
		final JButton userProfileButton= new JButton("User Profile");
		userProfileButton.setFont(font2);
		userProfileButton.setForeground(grey);
		userProfileButton.setBackground(colour);
		userProfileButton.setBorder(null);
		userProfileButton.setFocusable(false);
		
		userProfileButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	userProfileButton.setBackground(new Color(43, 47, 50));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	userProfileButton.setBackground(colour);
		    }
		});
		
		JPanel userProfilePanel = new JPanel(new BorderLayout());
		userProfilePanel.setMaximumSize(new Dimension(300, 40));
		userProfilePanel.add(userProfileButton);
		userProfileButton.setHorizontalAlignment(SwingConstants.LEFT);
		add(userProfilePanel);
		userProfileButton.addActionListener( new ActionListener()
	  {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  new UserProfile(user);
	      }
	  });
		
		setBackground(colour);
		setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10));
	}
	
	public SettingsComponent getSettingsComponent() {
		return settingsComponent;
	}
	
	public CustomTextWindow getCustomTextWindow() {
		return customTextWindow;
	}

}
