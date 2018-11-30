
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class BottomPanel extends JPanel {
	Map<Character, JLabel> alphabet = new HashMap<Character, JLabel>();
	private char[] characters = {'e','t','a','o','i','n','s',
			'r','d','l','c','u','m','w','f','g','y','p','b','v','k','j','x','q','z'};
	
	public BottomPanel() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		for (int i = 0; i < characters.length; i++) {
			JLabel jlabel = new JLabel(String.valueOf(characters[i]),JLabel.CENTER);
			jlabel.setFont(new Font("Courier New", Font.BOLD, 24));
			jlabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jlabel.setVisible(true);
			alphabet.put(characters[i], jlabel);
			add(alphabet.get(characters[i]),gc);
			gc.gridx++;
		}
		
		setBackground(new Color(255,255,255));

	}
	
	public void refreshDisplay(Map<Character, Integer> timeDatabyLetter) {
		for (Map.Entry<Character, Integer> entry : timeDatabyLetter.entrySet()) {
			System.out.println(Math.round(entry.getValue()/1000));
			if (entry.getValue() > 300) { 
				alphabet.get(entry.getKey()).setBackground(new Color(183 - entry.getValue()/100, 183, 0));
			} else {
				alphabet.get(entry.getKey()).setBackground(new Color(183, 183 - entry.getValue()/100, 0));
			}
		}
			
	}

}
