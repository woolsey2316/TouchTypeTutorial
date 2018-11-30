import java.awt.Color;

import javax.swing.BorderFactory;

public class AccuracyPanel extends DashBoardComponent{

	public AccuracyPanel() {
		thresholdValueExcellent = 95;
		thresholdValueModerate = 85;
		title = "\n    Accuracy";
		setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(59,119,252)));
		loadDocStyle();
	}

}
