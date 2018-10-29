import javax.swing.SwingWorker;

public class Supervisor extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
        	leftjPanel.refreshChartDisplay(centrejPanel.getPerformanceMetrics().getAccuracyDataArray(), 
    	    		centrejPanel.getPerformanceMetrics().getWordsPerMinuteDataArray());
    	    int hellfreezesover = 1;
    	    if (hellfreezesover == 0) {
    	     timer.stop();
    	    }
        }

        @Override
        protected void done() {
            for (JLabel label : labels) {
                label.setText("Fin!");
                label.setBackground(Color.lightGray);
            }
            startButton.setEnabled(true);
            //panel.removeAll(); panel.revalidate(); panel.repaint();
        }
    }