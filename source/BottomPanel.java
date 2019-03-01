import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;

public class BottomPanel extends JPanel {
	Map<Character, JTextPane> alphabet = new HashMap<Character, JTextPane>();
	private char[] characters = {'e','t','a','o','i','n','s',
			'r','d','l','c','h','u','m','w','f','g','y','p','b','v','k','j','x','q','z'};
	
	public BottomPanel() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		for (int i = 0; i < characters.length; i++) {
			JTextPane jTextPane = new JTextPane();
			jTextPane.setText(String.valueOf(characters[i]));
			jTextPane.setFont(new Font("Courier New", Font.BOLD, 28));
			jTextPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jTextPane.setVisible(true);
			jTextPane.setForeground(Color.BLACK);
			jTextPane.setEditable(false);
			alphabet.put(characters[i], jTextPane);
			add(alphabet.get(characters[i]),gc);
			gc.gridx++;
		}
		
		setBackground(new Color(7,100,143));

	}
	
	public void refreshDisplay(Map<Character, Integer> timeDatabyLetter) {
		for (Map.Entry<Character, Integer> entry : timeDatabyLetter.entrySet()) {
			if ((entry.getValue() == null)) { 
				continue;
			}
			// 300 milliseconds is yellow-coloured performance
			int colourAdjust = (Math.round(entry.getValue()-300));
			if (colourAdjust > 255) { 
				alphabet.get(entry.getKey()).setBackground(new Color(255, 0, 0));
			} else if (colourAdjust < -255) { 
				alphabet.get(entry.getKey()).setBackground(new Color(0, 255, 0));
			} else if (entry.getValue() > 300) { 
				alphabet.get(entry.getKey()).setBackground(new Color(255, 255 - colourAdjust, 0));
			} else if (entry.getValue() > 0) {
				alphabet.get(entry.getKey()).setBackground(new Color(255 + colourAdjust, 255, 0));
			} 
		}
			
	}

}
