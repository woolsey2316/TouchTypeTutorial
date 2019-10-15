package Model;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class WordSelector {
	private String[] fileContents;
	private int numberOfLines = 4;
	private int wordsPerLine = 6;

	/**
	 * Loads the words the user tests their typing skills on from a file*/
	public String[] generateWords (int Level) {
		String[] paragraphOfWords = new String[numberOfLines];
		
		Random rand = new Random();
		for (int j = 0; j < numberOfLines; ++j) {
			StringBuilder sampleText = new StringBuilder();
			for (int i = 0; i < wordsPerLine; ++i) {
					sampleText.append(fileContents[rand.nextInt(50000)].toLowerCase());
					sampleText.append(' ');
			}
			sampleText.setLength(sampleText.length() - 1);
			sampleText.append('\n');
				
			paragraphOfWords[j] = sampleText.toString();
		}
		return paragraphOfWords;
	}
	
	public String generateParagraph (int level) {
		Random rand = new Random();
			StringBuilder sampleText = new StringBuilder();
			sampleText.append(fileContents[rand.nextInt(50000+level)].toLowerCase());

			for (int i = 0; i < 21; ++i) {
					sampleText.append(' ');
					sampleText.append(fileContents[rand.nextInt(500+level)].toLowerCase());

			}
			sampleText.append('\n');
		return sampleText.toString();
	}
	
	public String generateLineOfWords (int level) {
		Random rand = new Random();
			StringBuilder sampleText = new StringBuilder();
			sampleText.append(fileContents[rand.nextInt(500+level)].toLowerCase());

			for (int i = 0; i < 21; ++i) {
					sampleText.append(' ');
					sampleText.append(fileContents[rand.nextInt(500+level)].toLowerCase());

			}
			sampleText.append('\n');
		return sampleText.toString();
	}
	
	public void loadTextFromFile () {
		fileContents = new String[466460];
		
		FileReader fr;
		try {
			fr = new FileReader("data preparation/words.txt");
			Scanner in = new Scanner(fr);
			int ind = 0;
			while (in.hasNextLine()) {
				fileContents[ind] = in.nextLine();
				ind++;
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally { }
			
	}
	
	public int getWordsPerLine() {
		return wordsPerLine;
	}
	
	public int getNumberOfLines() {
		return numberOfLines;
	}
	
}
