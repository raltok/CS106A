import acm.program.*;
import acm.util.RandomGenerator;

import java.util.*;

public class RandomCard extends ConsoleProgram {
	
	private static final int RANK = 13;
	private static final int SUIT = 4;
	
	public void run() {
		println("This program displays a randomly chosen card.");
		getSuit();
		getRank();
		println("" + getRank() + " of " + getSuit());
	}
	
	private String getRank() {
		int x = rgen.nextInt(1, RANK);
		switch(x) {
		case 1:  return "Ace";  
		case 11: return"Jack"; 
		case 12: return "Queen"; 
		case 13: return "King"; 
		default: return "" + x;
		}
	}
	
	private String getSuit() {
		int x = rgen.nextInt(1, SUIT);
		switch(x) {
		case 1: return "Clubs"; 
		case 2: return "Diamonds";
		case 3: return "Hearts";
		case 4: return "Spades"; 
		default: return "";
		}
	}
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
}

