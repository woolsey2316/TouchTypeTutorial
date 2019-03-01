
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

public class WordGenerator {
	private String[] dictionary;
	private int numberOfLines = 2;
	private int wordsPerLine = 3;
	static private Queue<String> customText = new LinkedList<>();
	static private int index;
	static private int numberofLinesCustomText;
	private static boolean usingCustomText;

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
	} else {
		while (customText.size() <  numberOfLines*wordsPerLine) {
			usingCustomText = false;
			customText.add("end");
		}
		for (int j = 0; j < numberOfLines; ++j) {
			StringBuilder sampleText = new StringBuilder();
			for (int i = 0; i < wordsPerLine; ++i) {
				if (i != wordsPerLine-1) {
					sampleText.append(customText.poll().toLowerCase()); 
					sampleText.append(' ');
				} else {
					sampleText.append(customText.poll().toLowerCase());
					sampleText.append('\n');
				}
			paragraphOfWords[j] = sampleText.toString();
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
	int previousWord = -1;
	int nextLine = (text.indexOf('\n') != -1) ? text.indexOf('\n') : text.length();
	int previousLine = -1;
	int nextWord = text.indexOf(' ');
	// a single word...
	if (nextWord == -1) {
		customText.add(text);
		return;
	}
	do {
		while (nextWord != -1) {
			customText.add(text.substring(previousWord+1,nextWord));
			previousWord = nextWord;
			nextWord = text.substring(previousLine+1, nextLine).indexOf(' ',previousWord+1);
			
		}
		// add final word in line
		customText.add(text.substring(previousWord+1,nextLine));
		previousLine = nextLine;
		previousWord = nextLine;
		nextLine = text.indexOf('\n',previousLine);
		
	} while (nextLine != -1);
	return;
}
    
public void setUsingCustomText(Boolean bool) {
	usingCustomText = bool;
}
	
}
