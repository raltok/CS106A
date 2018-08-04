import java.io.*;
import acm.program.*;

public class WordCount extends ConsoleProgram {
	
	public void run() {
		countLines();	
	}
	
	private BufferedReader openFileReader () {
		BufferedReader rd = null;
		while(rd == null) {
			try {
				String name = readLine("File: ");
				rd = new BufferedReader (new FileReader(name));
			} catch (IOException ex) {
				println("Can't open that file.");
			}
		}
		return rd;
	}
	
	private void countLines() {
		BufferedReader rd = openFileReader();
		int lines = 0;
		int chars = 0;
		int words = 0;
		try {
			while(true) {
				String line = rd.readLine();
				if(line == null)	break;
				lines++;
				chars += line.length();
				words += countWords(line);
			}
		} catch (IOException e) {
			println("Exception!");
		}
		println("Lines: " + lines);
		println("Words: " + words);
		println("Chars: " + chars);
	}
	
	private int countWords(String line) {
		int words = 0;
		for(int i = 0; i < line.length() - 1; i++) {
			if(Character.isLetterOrDigit(line.charAt(i)) == false && 
						Character.isLetterOrDigit(line.charAt(i-1)) == true) {
					words++;
			}	
		}
		words++;
		return words;
	}
}