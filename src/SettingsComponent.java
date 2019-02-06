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

public class SettingsComponent extends JPanel {
	User user;
	EditableSetting goalTypingSpeedSetting;
	EditableSetting numberOfLinesSetting;
	EditableSetting wordsPerLineSetting;
	JButton saveButton;
	
	public SettingsComponent(User user_) {
		user = user_;
		String goalSpeed = Integer.toString(user.getGoalTypingSpeed());
		String numberOfLines = Integer.toString(user.getNumberOfLines());
		String wordsPerLine = Integer.toString(user.getWordsPerLine());
		
		goalTypingSpeedSetting = new EditableSetting(user, "Goal Typing Speed", goalSpeed);
		numberOfLinesSetting = new EditableSetting(user, "Number of Lines", numberOfLines);
		wordsPerLineSetting = new EditableSetting(user, "Words per Line", wordsPerLine);
		saveButton = new JButton("Save");
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Color colour = new Color(20, 25, 28);
		setBackground(colour);

		saveButton.setFont(new Font("Palatino", Font.PLAIN, 12));
		saveButton.setForeground(new Color(255,255,255));
		saveButton.setBackground(colour);
	  try {
	    Image img = ImageIO.read(getClass().getResource("images/diskette.png"));
	    saveButton.setIcon(new ImageIcon(img));
	  } catch (Exception ex) {
	    System.out.println(ex);
	  }
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setMaximumSize( new Dimension(100, 40));
		buttonPanel.add(saveButton);
		saveButton.setHorizontalAlignment(SwingConstants.LEFT);
		
		setBackground(colour);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		add(goalTypingSpeedSetting);
		add(numberOfLinesSetting);
		add(wordsPerLineSetting);
		add(buttonPanel);
		
	}
	
	public void updatePreferences() {
		user.setNumberOfLines(numberOfLinesSetting.getText());
		user.setWordsPerLines(wordsPerLineSetting.getText());
		user.setGoalwpm(goalTypingSpeedSetting.getText());
		
		
	}
	
}
