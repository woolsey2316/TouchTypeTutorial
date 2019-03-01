import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

public class UserProfile {
	JFrame frame = new JFrame("User Profile");
	static User user;
	AccuracyPanel accuracyPanel = new AccuracyPanel();
	WordsPerMinutePanel wordsPerMinutePanel= new WordsPerMinutePanel();
	ScorePanel scorePanel = new ScorePanel();
	GoalPanel goalPanel = new GoalPanel(); 
	XChartPanel<XYChart> wpmChartPanel;
	XChartPanel<XYChart> scoreChartPanel;
	XChartPanel<XYChart> accuracyChartbyKeyPanel;
	XChartPanel<XYChart> wpmChartbyKeyPanel;
	XChartPanel<XYChart> scoreChartbyKeyPanel;
  
  public UserProfile(User user_) {
	  user = user_;
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
        	  createFrame();
          }
      });
  }
	public void createFrame() {
		frame.setSize(1300, 700);
		frame.setBackground(new Color(232,232,232));
		JPanel panel = new JPanel();
		addComponentsToPane(panel);
		JScrollPane scrollPane = new JScrollPane(panel);
		
		frame.add(scrollPane);	
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane,1));
        
        JPanel dashboard = new JPanel();
        dashboard.setLayout(new BoxLayout(dashboard, BoxLayout.LINE_AXIS));
        dashboard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
         
        wordsPerMinutePanel.setNumericalValue(user.getPerformanceMetrics().averageWordsPerMinute(), false);
        accuracyPanel.setNumericalValue(user.getPerformanceMetrics().averageAccuracy(), true);
        scorePanel.setNumericalValue(user.getPerformanceMetrics().averageScore(), false);
        goalPanel.setNumericalValue((double) user.getGoalTypingSpeed(), true);
        
        dashboard.add(Box.createRigidArea(new Dimension(10,0)));
        dashboard.add(wordsPerMinutePanel);
        dashboard.add(Box.createRigidArea(new Dimension(10,0)));
        dashboard.add(accuracyPanel);
        dashboard.add(Box.createRigidArea(new Dimension(10,0)));
        dashboard.add(scorePanel);
        dashboard.add(Box.createRigidArea(new Dimension(10,0)));
        dashboard.add(goalPanel);
        
        pane.add(dashboard);
        
        JPanel charts = new JPanel();
        charts.setLayout(new BoxLayout(charts, BoxLayout.Y_AXIS));
        
        Statistics stats = user.getPerformanceMetrics();
	  	
        XYChart wpmChart = new XYChartBuilder().width(300).height(300).title("Words per minute").xAxisTitle("").yAxisTitle("Y").build();
		double[][] data = stats.getWordsPerMinuteDataArray(false);
	
		wpmChart.addSeries("wordsPerMinute", data[0], data[1]);
		wpmChart.getStyler().setLegendPosition(LegendPosition.InsideSW);
		wpmChart.getStyler().setLegendVisible(false);
		wpmChart.getStyler().setChartBackgroundColor(new Color(255,255,255));
		wpmChart.getStyler().setYAxisTitleVisible(false);
		wpmChart.getStyler().setXAxisTicksVisible(false);
		wpmChart.getStyler().setPlotGridVerticalLinesVisible(false);
		XChartPanel<XYChart> wpmChartPanel = new XChartPanel<XYChart>(wpmChart);
	  
		charts.add(wpmChartPanel);
		charts.add(Box.createRigidArea(new Dimension(10,0)));
	  
		XYChart accuracyChart = new XYChartBuilder().width(300).height(300).title("Accuracy").xAxisTitle("").yAxisTitle("Y").build();
		double[][] accData = stats.getAccuracyDataArray(false);
	  	accuracyChart.addSeries("wordsPerMinute", accData[0], accData[1]);
	  	accuracyChart.getStyler().setLegendPosition(LegendPosition.InsideSW);
	  	accuracyChart.getStyler().setLegendVisible(false);
	  	accuracyChart.getStyler().setChartBackgroundColor(new Color(255,255,255));
	  	accuracyChart.getStyler().setYAxisTitleVisible(false);
	  	accuracyChart.getStyler().setXAxisTicksVisible(false);
	  	accuracyChart.getStyler().setPlotGridVerticalLinesVisible(false);
	  	XChartPanel<XYChart> accuracyChartPanel = new XChartPanel<XYChart>(accuracyChart);
	  
	  	charts.add(accuracyChartPanel);
	  	charts.add(Box.createRigidArea(new Dimension(10,0)));
	  
	  	XYChart scoreChart = new XYChartBuilder().width(300).height(300).title("Score").xAxisTitle("").yAxisTitle("Y").build();
	  	double[][] scoreData = stats.getScoreDataArray(false);
	  	scoreChart.addSeries("wordsPerMinute", accData[0], scoreData[1]);
	  	scoreChart.getStyler().setLegendPosition(LegendPosition.InsideSW);
	  	scoreChart.getStyler().setLegendVisible(false);
	  	scoreChart.getStyler().setChartBackgroundColor(new Color(255,255,255));
	  	scoreChart.getStyler().setYAxisTitleVisible(false);
	  	scoreChart.getStyler().setXAxisTicksVisible(false);
	  	scoreChart.getStyler().setPlotGridVerticalLinesVisible(false);
	  	XChartPanel<XYChart> scoreChartPanel = new XChartPanel<XYChart>(scoreChart);
	  
	  	charts.add(scoreChartPanel);
	  	charts.add(Box.createRigidArea(new Dimension(10,0)));
	  	
	  	pane.add(charts);
 
    }
	
	public DashBoardComponent getAccuracyPanel() {
		return accuracyPanel;
	}
	
	public DashBoardComponent getWordsPerMinutePanel() {
		return wordsPerMinutePanel;
	}
	
	public DashBoardComponent getScorePanel() {
		return scorePanel;
	}
	
	public DashBoardComponent getGoalPanel() {
		return goalPanel;
	}

}
