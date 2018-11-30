import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.Timer;

public class GUI extends JFrame {
	CentreJPanel centrejPanel;
	RightJPanel rightjPanel;
	LeftJPanel leftjPanel;
	BottomPanel bottomjPanel;
	TopJPanel topJPanel;
	User user;
	String result;
	String lineOfWordsHTML;
	Timer timer;
	
	public GUI (User user_) {
	user = user_; 
	centrejPanel = new CentreJPanel();
	rightjPanel = new RightJPanel();
	leftjPanel = new LeftJPanel();
	bottomjPanel = new BottomPanel();
	topJPanel = new TopJPanel();
	
	setSize(1300, 700);
	
	add(bottomjPanel, BorderLayout.SOUTH);
	add(centrejPanel, BorderLayout.CENTER);
	add(rightjPanel, BorderLayout.EAST);
	add(leftjPanel, BorderLayout.WEST);
	add(topJPanel, BorderLayout.NORTH);
	
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
		      	centrejPanel.getTypingPanel().getPerformanceMetrics().toJsonFile(); 
		      	System.exit(0);
		      }
		  }
		};
		addWindowListener(exitListener); 
	
	final int INTERVAL = 4000;
	timer = new Timer(INTERVAL, new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		    @Override
		    public Void doInBackground() throws Exception {
					return null;

		    }

		    @Override
		    public void done() {
				    leftjPanel.refreshChartDisplay(centrejPanel.getTypingPanel().getPerformanceMetrics().getAccuracyDataArray(), 
				    		centrejPanel.getPerformanceMetrics().getWordsPerMinuteDataArray());
				    centrejPanel.refreshDisplay();
				    bottomjPanel.refreshDisplay(centrejPanel.getPerformanceMetrics().getTimeforEachCharacter());
		    	

		    }
			};
			worker.execute();
			
	    }
	 });
	timer.setRepeats(true);
	timer.start();

  }
}
