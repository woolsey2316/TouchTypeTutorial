import java.awt.Color;

public class WordsPerMinutePanel extends DashBoardComponent{

	public WordsPerMinutePanel() {
		thresholdValueExcellent = 35;
		thresholdValueModerate = 20;
		title = "\n    WPM";
		setBorder(new CustomBorder(new Color(31,180,251)));
		loadDocStyle();
	}
}
