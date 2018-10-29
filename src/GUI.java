import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

public class GUI extends JFrame {
	CentreJPanel centrejPanel;
	RightJPanel rightjPanel;
	LeftJPanel leftjPanel;
	AlphabetPanel bottomjPanel;
	User user;
	String result;
	Model performanceMetrics;
	String lineOfWordsHTML;
	Timer timer;

	private char[] characters = {'e','t','a','o','i','n','s',
			'r','d','l','c','u','m','w','f','g','y','p','b','v','k','j','x','q','z'};
	
	public GUI (User user_, Model performanceMetrics_) {
	user = user_; 
	performanceMetrics = performanceMetrics_;
	centrejPanel = new CentreJPanel(performanceMetrics_);
	rightjPanel = new RightJPanel();
	leftjPanel = new LeftJPanel();
	bottomjPanel = new AlphabetPanel();
	
	setSize(1200, 700);
	
	add(bottomjPanel, BorderLayout.SOUTH);
	add(centrejPanel, BorderLayout.CENTER);
	add(rightjPanel, BorderLayout.EAST);
	add(leftjPanel, BorderLayout.WEST);
	
	pack();
	setVisible(true);
	
	setLocationRelativeTo(null);
		
		WindowListener exitListener = new WindowAdapter() {
		  @Override
		  public void windowClosing(WindowEvent e) {
		      int confirm = JOptionPane.showOptionDialog(
		           null, "Are You Sure to Shut-Down the Application?", 
		           "Confirm Exit", JOptionPane.YES_NO_OPTION, 
		           JOptionPane.QUESTION_MESSAGE, null, null, null);
		      if (confirm == 0) {
		      	performanceMetrics.toJsonFile(); 
		      	System.exit(0);
		      }
		  }
		};
		addWindowListener(exitListener);
	
  SwingWorker worker = new SwingWorker<Void, Void>() {
    @Override
    public Void doInBackground() {
	    leftjPanel.refreshChartDisplay(centrejPanel.getPerformanceMetrics().getAccuracyDataArray(), 
	    		centrejPanel.getPerformanceMetrics().getWordsPerMinuteDataArray());
	    System.out.println("doing");
			return null;

    }

    @Override
    public void done() {
    	System.out.println("done");

    }
	};
	
	final int INTERVAL = 4000;
	timer = new Timer(INTERVAL, new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			worker.execute();
			System.out.println("hi");
			
	    }
	 });
	timer.setRepeats(true);
	timer.start();

  }
}
