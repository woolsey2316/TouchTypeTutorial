package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

import Model.User;

public class RightJPanel extends JPanel{
	XYChart accuracyChart;
	XYChart wpmChart;
	JPanel accuracyChartPanel;
	JPanel wpmChartPanel;
	
	public RightJPanel(User user_) {
		setLayout(new GridLayout(2,1));
		
		wpmChart = new XYChartBuilder().width(300).height(300).title("Words per minute").xAxisTitle("Time").yAxisTitle("Y").build();
		wpmChart.addSeries("wordsPerMinute", new double[]{0.0}, new double[]{20});
		wpmChart.addSeries("goal", new double[]{0.0}, new double[]{user_.getGoalTypingSpeed()});
		wpmChart.getStyler().setChartTitleFont(new Font("Palatino", 14, 14));
		wpmChart.getStyler().setAxisTitleFont(new Font("Palatino", 14, 14));
		wpmChart.getStyler().setAxisTickLabelsFont(new Font("Palatino", 14, 14));
		wpmChart.getStyler().setLegendPosition(LegendPosition.InsideSW);
		wpmChart.getStyler().setLegendVisible(false);
		wpmChart.getStyler().setChartBackgroundColor(new Color(247,240,240));
		wpmChart.getStyler().setYAxisTitleVisible(false);
		wpmChart.getStyler().setXAxisTicksVisible(false);
		wpmChart.getStyler().setPlotGridVerticalLinesVisible(false);
		wpmChart.getStyler().setPlotGridHorizontalLinesVisible(false);
		wpmChart.getStyler().setMarkerSize(2);
		wpmChartPanel = new XChartPanel<XYChart>(wpmChart);
	  
		add(wpmChartPanel);
		
		accuracyChart = new XYChartBuilder().width(300).height(300).title("Accuracy").xAxisTitle("Time").yAxisTitle("Y").build();
		accuracyChart.addSeries("score",  new double[]{0.0},  new double[]{100.0});
		accuracyChart.getStyler().setChartTitleFont(new Font("Palatino", 14, 14));
		accuracyChart.getStyler().setAxisTitleFont(new Font("Palatino", 14, 14));
		accuracyChart.getStyler().setAxisTickLabelsFont(new Font("Palatino", 14, 14));
		accuracyChart.getStyler().setLegendPosition(LegendPosition.InsideSW);
		accuracyChart.getStyler().setChartBackgroundColor(new Color(247,240,240));
		accuracyChart.getStyler().setLegendVisible(false);
		accuracyChart.getStyler().setXAxisTicksVisible(false);
		accuracyChart.getStyler().setYAxisTitleVisible(false);
		accuracyChart.getStyler().setPlotGridVerticalLinesVisible(false);
		accuracyChart.getStyler().setPlotGridHorizontalLinesVisible(false);
		accuracyChart.getStyler().setMarkerSize(2);
		accuracyChartPanel = new XChartPanel<XYChart>(accuracyChart);
	  
		add(accuracyChartPanel);
		setBorder(null);
		setBackground(new Color(247,240,240));
	}
	
	void refreshChartDisplay(double[][] accuracyData, double[][] WordsPMData, User user_) {
		double[] goal = new double[WordsPMData[1].length];
		java.util.Arrays.fill(goal, (double) user_.getGoalTypingSpeed());
		
		accuracyChart.updateXYSeries("score", accuracyData[0], accuracyData[1], null);
		accuracyChartPanel.repaint();
	  
		wpmChart.updateXYSeries("wordsPerMinute", WordsPMData[0], WordsPMData[1], null);
		wpmChart.updateXYSeries("goal", WordsPMData[0], goal, null);
		wpmChartPanel.repaint();
	}

}
