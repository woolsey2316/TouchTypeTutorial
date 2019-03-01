import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class SettingsComponent extends JPanel {
	User user;
	JButton saveButton;
	JPanel buttonPanel;
	Color colour = new Color(20, 25, 28);
	JFrame frame;
	String goalSpeed;
	String numberOfLines;
	String wordsPerLine;
	EditableSetting goalField;
	EditableSetting numberOfLinesField;
	EditableSetting wordsPerLineField;
	
	public SettingsComponent(User user_) {
		user = user_;
		goalSpeed = Integer.toString(user.getGoalTypingSpeed());
		numberOfLines = Integer.toString(user.getNumberOfLines());
		wordsPerLine = Integer.toString(user.getWordsPerLine());
		
		saveButton = new JButton("Save");
		saveButton.setFont(new Font("Palatino", Font.PLAIN, 12));
		saveButton.setForeground(new Color(255,255,255));
		saveButton.setBackground(colour);
	  try {
	    Image img = ImageIO.read(getClass().getResource("images/diskette.png"));
	    saveButton.setIcon(new ImageIcon(img));
	  } catch (Exception ex) {
	    System.out.println(ex);
	  }
		buttonPanel = new JPanel();
		buttonPanel.setMaximumSize( new Dimension(100, 40));
		buttonPanel.add(saveButton);
		saveButton.setHorizontalAlignment(SwingConstants.RIGHT);
		
		setBackground(colour);
		
	}
	
	public void createFrame()
	  {
	      EventQueue.invokeLater(new Runnable()
	      {
	          public void run()
	          {
	              frame = new JFrame("Settings");
	              Container contentPane = frame.getContentPane();
	              JPanel settings = new JPanel(new SpringLayout());
	              
	              contentPane.setLayout(new BoxLayout(frame.getContentPane(), 1));
	              contentPane.setBackground(new Color(7,100,143));
	              JLabel typingSpeedl = new JLabel("Goal Typing Speed: ");
	              typingSpeedl.setForeground(Color.white);
	              typingSpeedl.setFont(new Font("arial",1,18));
	              goalField = new EditableSetting(goalSpeed);
	              JLabel numberOfLinesl = new JLabel("Number of Lines: ");
	              numberOfLinesl.setForeground(Color.white);
	              numberOfLinesl.setFont(new Font("arial",1,18));
	              numberOfLinesField = new EditableSetting(numberOfLines);
	              JLabel wordsPerLinel = new JLabel("Words per Line: ");
	              wordsPerLinel.setForeground(Color.white);
	              wordsPerLinel.setFont(new Font("arial",1,18));
	              wordsPerLineField = new EditableSetting(wordsPerLine);
	              
	              settings.add(typingSpeedl);
	              settings.add(goalField);
	              settings.add(numberOfLinesl);
	              settings.add(numberOfLinesField);
	              settings.add(wordsPerLinel);
	              settings.add(wordsPerLineField);
	              
	              SpringUtilities.makeCompactGrid(settings, 3, 2, 6, 6, 6, 6);
	              
	              settings.setBackground(colour); 
	              contentPane.add(settings);
	              contentPane.add(buttonPanel);
	              
	              frame.setSize(600, 250);
	              frame.setLocationByPlatform(true);
			      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			      try 
			      {
			         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			      } catch (Exception e) {
			         e.printStackTrace();
			      }
			      //frame.pack();
				  frame.setVisible(true);
	          }
	      });
	  }
	
	public void closeFrame() {
		frame.dispose();
		
	}
	
	public void updatePreferences() {
		user.setNumberOfLines(numberOfLinesField.getText());
		user.setWordsPerLines(wordsPerLineField.getText());
		user.setGoalwpm(goalField.getText());
		
		
	}
	
}
