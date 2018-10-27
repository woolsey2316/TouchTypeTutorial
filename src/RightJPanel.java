import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class RightJPanel extends JPanel {
	public RightJPanel() {
		setLayout(new GridLayout(4,1));
		JLabel jLabel = new JLabel("Touch Type Tutorial");
		jLabel.setFont(new Font("Palatino", Font.PLAIN, 30));
		jLabel.setForeground(Color.WHITE);
		add(jLabel);
		JButton jButton1= new JButton("PRACTICE");
		jButton1.setFont(new Font("Palatino", Font.PLAIN, 18));
		jButton1.setForeground(new Color(134, 138, 148));
		jButton1.setBackground(new Color(42, 48, 62));
		add(jButton1);
		JButton jButton2= new JButton("PROFILE");
		jButton2.setFont(new Font("Palatino", Font.PLAIN, 18));
		jButton2.setForeground(new Color(134, 138, 148));
		jButton2.setBackground(new Color(42, 48, 62));
		add(jButton2);
		JButton jButton3= new JButton("LOAD CUSTOM TEXT");
		jButton3.setFont(new Font("Palatino", Font.PLAIN, 18));
		jButton3.setForeground(new Color(134, 138, 148));
		jButton3.setBackground(new Color(42, 48, 62));
		add(jButton3);
		
		setBackground(new Color(42, 48, 62));
	}

}
