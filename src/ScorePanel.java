import java.awt.Color;

import javax.swing.BorderFactory;

public class ScorePanel extends DashBoardComponent{

	public ScorePanel() {
		thresholdValueExcellent = 350;
		thresholdValueModerate = 200;
		title = "\n    Score";
		setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(73,125,230)));
		loadDocStyle();
	}
}
