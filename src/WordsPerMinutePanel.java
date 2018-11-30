import java.awt.Color;

import javax.swing.BorderFactory;

public class WordsPerMinutePanel extends DashBoardComponent{

	public WordsPerMinutePanel() {
		thresholdValueExcellent = 35;
		thresholdValueModerate = 20;
		title = "\n    WPM";
		setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(31,180,251)));
		loadDocStyle();
	}
}
