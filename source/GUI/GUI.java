package GUI;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import GUI.BottomPanel;
import GUI.CentreJPanel;
import GUI.LeftJPanel;
import GUI.RightJPanel;
import GUI.TopJPanel;
import Model.User;

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
	centrejPanel = new CentreJPanel(user_);
	rightjPanel = new RightJPanel(user_);
	leftjPanel = new LeftJPanel(user_);
	bottomjPanel = new BottomPanel();
	topJPanel = new TopJPanel();
	
	setSize(1300, 700);
	setLayout(new GridBagLayout());
	
	GridBagConstraints c = new GridBagConstraints();
	c.weightx = 0; c.weighty=1;
	c.fill = GridBagConstraints.VERTICAL;
	c.gridheight = 2;
	c.gridx = 0;
	c.gridy = 0;
	add(leftjPanel, c);
	
	c.fill = GridBagConstraints.BOTH;
	c.weighty=0; c.weightx = 1;
	c.gridheight = 1;
	c.gridwidth = 2;
	c.gridx = 1;
	c.gridy = 0;
	add(topJPanel, c);
	
	c.weighty=1; c.weightx = 1;
	c.gridheight = 1;
	c.gridwidth = 1;
	c.gridx = 1;
	c.gridy = 1;
	add(centrejPanel, c);
	
	c.weighty=1;  c.weightx = 0.9;
	c.gridheight = 2;
	c.gridwidth = 1;
	c.gridx = 2;
	c.gridy = 1;
	add(rightjPanel, c);
	
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
		
		leftjPanel.getSettingsComponent().saveButton.addActionListener( new ActionListener()
	  {
		  public void actionPerformed(ActionEvent e)
		  {
			  leftjPanel.getSettingsComponent().updatePreferences();
			  centrejPanel.getTypingPanel().updateText();
		  }
	  });
		
		leftjPanel.getCustomTextWindow().loadButton.addActionListener( new ActionListener()
		  {
			  public void actionPerformed(ActionEvent e)
			  {
			  	centrejPanel.getTypingPanel().updateText();
			  }
		  });
		
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
			    	if (!centrejPanel.getTypingPanel().isfocusGained()) {
			    		centrejPanel.getPerformanceMetrics().resetTime();
			    	}
			    	
			    	  	
				    rightjPanel.refreshChartDisplay(
				    		centrejPanel.getTypingPanel().getPerformanceMetrics().getAccuracyDataArray(true), 
				    		centrejPanel.getPerformanceMetrics().getWordsPerMinuteDataArray(true),
				    		user);
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
