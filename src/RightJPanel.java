import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class RightJPanel extends JPanel {
	public RightJPanel() {
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JLabel jLabel = new JLabel("Touch Type Tutorial");
		jLabel.setFont(new Font("Palatino", Font.PLAIN, 30));
		jLabel.setForeground(Color.WHITE);
		add(jLabel,BorderLayout.CENTER);
		

	  
		JButton jButton1= new JButton("	PRACTICE");
		jButton1.setFont(new Font("Palatino", Font.PLAIN, 18));
		jButton1.setForeground(new Color(134, 138, 148));
		jButton1.setBackground(new Color(42, 48, 62));
	  try {
	    Image img = ImageIO.read(getClass().getResource("images/keyboard.png"));
	    jButton1.setIcon(new ImageIcon(img));
	  } catch (Exception ex) {
	    System.out.println(ex);
	  }
	  
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(jButton1);
		panel1.setMaximumSize( new Dimension(Integer.MAX_VALUE,40));
		jButton1.setAlignmentX(Component.LEFT_ALIGNMENT);
		jButton1.setHorizontalAlignment(SwingConstants.LEFT);
		add(panel1,BorderLayout.CENTER);
		
		JButton jButton2= new JButton("	PROFILE");
		jButton2.setFont(new Font("Palatino", Font.PLAIN, 18));
		jButton2.setForeground(new Color(134, 138, 148));
		jButton2.setBackground(new Color(42, 48, 62));
	  try {
	    Image img = ImageIO.read(getClass().getResource("images/user.png"));
	    jButton2.setIcon(new ImageIcon(img));
	  } catch (Exception ex) {
	    System.out.println(ex);
	  }
	  
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(jButton2);
		panel2.setMaximumSize( new Dimension(Integer.MAX_VALUE, 40));
		jButton2.setAlignmentX(Component.LEFT_ALIGNMENT);
		jButton2.setHorizontalAlignment(SwingConstants.LEFT);
		add(panel2);
		
		JButton jButton3= new JButton("	LOAD CUSTOM TEXT");
		jButton3.setFont(new Font("Palatino", Font.PLAIN, 18));
		jButton3.setForeground(new Color(134, 138, 148));
		jButton3.setBackground(new Color(42, 48, 62));
	  try {
	    Image img = ImageIO.read(getClass().getResource("images/diskette.png"));
	    jButton3.setIcon(new ImageIcon(img));
	  } catch (Exception ex) {
	    System.out.println(ex);
	  }
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.setMaximumSize( new Dimension(Integer.MAX_VALUE, 40));
		panel3.add(jButton3);
		jButton3.setAlignmentX(Component.LEFT_ALIGNMENT);
		jButton3.setHorizontalAlignment(SwingConstants.LEFT);
		add(panel3);
		
		setBackground(new Color(42, 48, 62));
	}

}
