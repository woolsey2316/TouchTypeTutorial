package GUI.VisualElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;


public class Profile extends JPanel {

	public Profile() {
		setLayout(new GridBagLayout());
		setMaximumSize(new Dimension(200, 220));
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1; c.weighty=1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 0;

		
		ProfilePicture profilePic = new ProfilePicture();
		add(profilePic, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0;
		c.anchor = GridBagConstraints.NORTH;

		JTextPane name = new JTextPane();
		name.setEditable(false);
		name.setMaximumSize(new Dimension(60,40));
		name.setText("Craig Anon");
		name.setBackground(new Color(0, 0, 0, 0));
		name.setForeground(new Color(255,255,255));
		
		Font font = new Font("segoe", Font.PLAIN, 18);
		Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
		attributes.put(TextAttribute.TRACKING, 0.05);
		Font font2 = font.deriveFont(attributes);
		name.setFont(font2);
		
		add(name, c);
		
		setBackground(new Color(0, 0, 0, 0));
		setVisible(true);
		
	}




}