import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TopJPanel extends JPanel {
	public TopJPanel() {
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	  
		JLabel jLabel = new JLabel("Touch Type Practice");
		jLabel.setFont(new Font("Palatino", Font.PLAIN, 25));
		jLabel.setForeground(new Color(240,240,240));
		jLabel.setMaximumSize(new Dimension(300,40));
		add(jLabel);
		
		setBackground(new Color(7,100,143));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

}
