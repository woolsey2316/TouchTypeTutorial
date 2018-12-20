import java.awt.Color;

public class GoalPanel extends DashBoardComponent{

	public GoalPanel() {
		thresholdValueExcellent = 70;
		thresholdValueModerate = 50;
		title = "\n    Goal Typing Speed";
		setBorder(new CustomBorder(new Color(30,97,178)));
		loadDocStyle();
	}

}