import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;


public class CustomTextWindow {
	static WordGenerator wordGenerator;
	Boolean fileDropped = false;
	JButton loadButton;
	JFrame frame;
	
	public CustomTextWindow(WordGenerator wordGenerator_) {
		wordGenerator = wordGenerator_;
		loadButton = new JButton("load");
	}
	
	public void createFrame()
	  {
	      EventQueue.invokeLater(new Runnable()
	      {
	          public void run()
	          {
		      frame = new JFrame("load Custom .txt file");
		      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		      try 
		      {
		         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		      JPanel panel = new JPanel();
		      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		      panel.setOpaque(true);
		      final JTextArea textArea = new JTextArea(15, 50);
		      textArea.setWrapStyleWord(true);
		      textArea.setEditable(true);
		      textArea.setFont(new Font("Courier New", Font.BOLD, 24));
		      textArea.setDropTarget(new DropTarget() {
		        public synchronized void drop(DropTargetDropEvent evt) {
		          try {
		        	  fileDropped = true;
		              evt.acceptDrop(DnDConstants.ACTION_COPY);
		              @SuppressWarnings("unchecked")
		              List<File> droppedFiles = (List<File>)
		              evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
	            	  textArea.setText(wordGenerator.loadCustomText(droppedFiles));
		              
		          } catch (Exception ex) {
		              ex.printStackTrace();
		          }
		      }
		      });
		      
		      JScrollPane scroller = new JScrollPane(textArea);
		      scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		      scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		      JPanel inputpanel = new JPanel();
		      inputpanel.setLayout(new FlowLayout());
		      loadButton.addActionListener( new ActionListener()
	          	  {
	          	      public void actionPerformed(ActionEvent e)
	          	      {
	          	      	wordGenerator.setUsingCustomText(true);
	          	      	if (!fileDropped) {
	          	      		wordGenerator.loadCustomText(textArea.getText());
	          	      		frame.dispose();
	          	      	}
	          	      }
	          	  });
	              DefaultCaret caret = (DefaultCaret) textArea.getCaret();
	              caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	              panel.add(scroller);
	              inputpanel.add(loadButton);
	              panel.add(inputpanel);
	              frame.getContentPane().add(BorderLayout.CENTER, panel);
	              frame.pack();
	              frame.setLocationByPlatform(true);
	              frame.setVisible(true);
	              frame.setResizable(false);
	              textArea.requestFocus();
	          }
	      });
	  }
	
	public void closeFrame() {
		frame.dispose();
	}

}
