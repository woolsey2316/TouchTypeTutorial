import java.awt.Color;

import javax.swing.BorderFactory;

public class GoalPanel extends DashBoardComponent{

	public GoalPanel() {
		thresholdValueExcellent = 70;
		thresholdValueModerate = 50;
		title = "\n    Goal Typing Speed";
		setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(30,97,178)));
		loadDocStyle();
	}

}