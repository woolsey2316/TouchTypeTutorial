import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

public class LeftJPanel extends JPanel{
	XYChart accuracyChart;
	XYChart wpmChart;
	JPanel accuracyChartPanel;
	JPanel wpmChartPanel;
	
	public LeftJPanel() {
		setLayout(new GridLayout(2,1));
		
	  wpmChart = new XYChartBuilder().width(300).height(300).title("Words per minute").xAxisTitle("Time").yAxisTitle("Y").build();
	  wpmChart.addSeries("wordsPerMinute", new double[]{0.0}, new double[]{20.0});
	  wpmChart.getStyler().setLegendPosition(LegendPosition.InsideSW);
	  wpmChart.getStyler().setLegendVisible(false);
	  wpmChart.getStyler().setChartBackgroundColor(Color.WHITE);
	  wpmChart.getStyler().setYAxisTitleVisible(false);
	  wpmChart.getStyler().setXAxisTicksVisible(false);
	  wpmChart.getStyler().setPlotGridVerticalLinesVisible(false);
	  wpmChartPanel = new XChartPanel<XYChart>(wpmChart);
	  
		add(wpmChartPanel);
		
	  accuracyChart = new XYChartBuilder().width(300).height(300).title("Accuracy").xAxisTitle("Time").yAxisTitle("Y").build();
	  accuracyChart.addSeries("score",  new double[]{0.0},  new double[]{100.0});
	  accuracyChart.getStyler().setLegendPosition(LegendPosition.InsideSW);
	  accuracyChart.getStyler().setChartBackgroundColor(Color.WHITE);
	  accuracyChart.getStyler().setLegendVisible(false);
	  accuracyChart.getStyler().setXAxisTicksVisible(false);
	  accuracyChart.getStyler().setYAxisTitleVisible(false);
	  accuracyChart.getStyler().setPlotGridVerticalLinesVisible(false);
	  accuracyChartPanel = new XChartPanel<XYChart>(accuracyChart);
	  
		add(accuracyChartPanel);
		setBorder(null);
		setBackground(Color.WHITE);
	}
	
	void refreshChartDisplay(double[][] accuracyData, double[][] WordsPMData) {
		
		accuracyChart.updateXYSeries("score", accuracyData[0], accuracyData[1], null);
	  accuracyChartPanel.repaint();
	  
		wpmChart.updateXYSeries("wordsPerMinute", WordsPMData[0], WordsPMData[1], null);
	  wpmChartPanel.repaint();
	}

}
