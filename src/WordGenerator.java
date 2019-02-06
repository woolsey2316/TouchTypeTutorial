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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

public class WordGenerator {
	private String[] dictionary;
	private int numberOfLines = 2;
	private int wordsPerLine = 3;
	static private Queue<String> customText;
	static private int index;
	static private int numberofLinesCustomText;
	private static boolean usingCustomText;
	private char[] characters = {'e','t','a','o','i','n','s',
			'r','d','l','c','u','m','w','f','g','y','p','b','v','k','j','x','q','z'};

	/**
	 * Loads the entire dictionary from a file*/
public String[] generateWords (int Level) {
	String[] paragraphOfWords = new String[numberOfLines];
	if (!usingCustomText ) {
		Random rand = new Random();
		for (int j = 0; j < numberOfLines; ++j) {
			StringBuilder sampleText = new StringBuilder();
			for (int i = 0; i < wordsPerLine; ++i) {
					sampleText.append(dictionary[rand.nextInt(50000)].toLowerCase());
					sampleText.append(' ');
			}
			sampleText.setLength(sampleText.length() - 1);
			sampleText.append('\n');
				
			paragraphOfWords[j] = sampleText.toString();
		}
	}
	else {
		for (int j = 0; j < numberOfLines; ++j) {
			if (index != numberofLinesCustomText) {
				paragraphOfWords[j] = customText.poll().toLowerCase();
			}
		}
		
	}
	return paragraphOfWords;
}
	
public String generateParagraph (int level) {
		Random rand = new Random();
			StringBuilder sampleText = new StringBuilder();
			sampleText.append(dictionary[rand.nextInt(50000+level)].toLowerCase());

			for (int i = 0; i < 21; ++i) {
					sampleText.append(' ');
					sampleText.append(dictionary[rand.nextInt(500+level)].toLowerCase());

			}
			sampleText.append('\n');
		return sampleText.toString();
	}
	
public String generateLineOfWords (int level) {
	Random rand = new Random();
		StringBuilder sampleText = new StringBuilder();
		sampleText.append(dictionary[rand.nextInt(500+level)].toLowerCase());

		for (int i = 0; i < 21; ++i) {
				sampleText.append(' ');
				sampleText.append(dictionary[rand.nextInt(500+level)].toLowerCase());

		}
		sampleText.append('\n');
	return sampleText.toString();
}
	
public void loadTextFromFile () {
	dictionary = new String[466460];
	
	FileReader fr;
	try {
		fr = new FileReader("DataPreparation/words.txt");
		Scanner in = new Scanner(fr);
		int ind = 0;
		while (in.hasNextLine()) {
			dictionary[ind] = in.nextLine();
			ind++;
		}
		in.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} finally { }
		
}
	
public void setNumberOfLines(int num) {
	numberOfLines = num;
}

public void setWordsPerLine(int num) {
	wordsPerLine = num;
}

public WordGenerator() {
	this.loadTextFromFile();
}

public int getWordsPerLine() {
	return wordsPerLine;
}

public int getNumberOfLines() {
	return numberOfLines;
}

public String loadCustomText(List<File> droppedFiles) throws IOException {
  String text = "";
  String line = "";
    for (File file : droppedFiles) { 	  
    	BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 

    	  String st; 
    	  while ((st = br.readLine()) != null) { 
    	  	line = "";
    	  	line += st;                    	  	
    	  	line += '\n';
    	  	customText.add(line);
    	  	text +=line;
    	  }
    	  numberofLinesCustomText = index;
    	  index = 0;
    	  usingCustomText = true;
    	  br.close();
    	    
    }
    return text;
}

public void loadCustomText(String text) {
	int previousIndex = 0;
	int index = 0;
	index = text.indexOf('\n', previousIndex);
	while (index != -1) { 
		while (previousIndex != -1)
			previousIndex = text.substring(previousIndex+1, index-1).indexOf(' ', index);
		  	customText.add(text.substring(previousIndex+1, index-1));
		  	previousIndex = index;
		  	index = text.indexOf('\n', previousIndex);
	  }
}
    
public void setUsingCustomText(Boolean bool) {
	usingCustomText = bool;
}
	
}
