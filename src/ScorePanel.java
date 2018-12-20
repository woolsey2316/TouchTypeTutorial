import java.awt.Color;

public class ScorePanel extends DashBoardComponent{

	public ScorePanel() {
		thresholdValueExcellent = 3500;
		thresholdValueModerate = 2000;
		title = "\n    Score";
		setBorder(new CustomBorder(new Color(73,125,230)));
		loadDocStyle();
	}
}
