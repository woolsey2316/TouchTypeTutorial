import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;



public class AlphabetPanel extends JPanel {
	private char[] characters = {'e','t','a','o','i','n','s',
			'r','d','l','c','u','m','w','f','g','y','p','b','v','k','j','x','q','z'};
	
	public AlphabetPanel() {
		
		for (int i = 0; i < characters.length; i++) {
			JLabel jlabel = new JLabel(String.valueOf(characters[i]),JLabel.CENTER);
			add(jlabel);
			jlabel.setPreferredSize(new Dimension(50,50));
			jlabel.setFont(new Font("Palatino", Font.BOLD, 18));
			jlabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		}
		
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		setLayout(new GridLayout(0,26));
		setBackground(Color.white);
	
	}
}
