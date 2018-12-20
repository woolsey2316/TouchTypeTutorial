import java.awt.Color;

public class AccuracyPanel extends DashBoardComponent{

	public AccuracyPanel() {
		thresholdValueExcellent = 95;
		thresholdValueModerate = 85;
		title = "\n    Accuracy";
		setBorder(new CustomBorder(new Color(59,119,252)));
		loadDocStyle();
	}

}
