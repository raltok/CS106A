import acm.program.*;
import java.io.*;

public class Histogram extends ConsoleProgram {
	
	public void run() {
		createArray();
		updateArray();
		completeHistogram();
	}
	
	private void createArray() {
		boxes = new int [11];
		for(int i = 0; i < 11; i++) {
			boxes[i] = 0;
		}
	}
	
	private void updateArray() {
		try {
			BufferedReader rd = 
					new BufferedReader(new FileReader("MidtermScores.txt"));
			while(true) {
				String line = rd.readLine();
				if(line == null) break;
				int x = Integer.parseInt(line);
				if(x < 0 || x > 100) {
					println("Illegal.");
				} else {
					int j = x / 10;
					boxes[j]++;
				}	
			}
		} catch (IOException ex) {
			println("Can't open that file.");
		}
	}
	
	private void completeHistogram() {
		int x = 0;
		for(int i = 0; i < boxes.length; i++) {
			String star = "";
			x = i;
			for(int j = 0; j < boxes[i]; j++) {
				star += "*";
			}
			if( i < 10) {
				println(x + "0-" + i + "9: " + star);
			} else println("  100: " + star);
			
		}
	}
	
	private int[] boxes;
}