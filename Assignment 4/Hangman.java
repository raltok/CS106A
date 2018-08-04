/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman extends ConsoleProgram {

	/***********************************************************
	 *              CONSTANTS                                  *
	 ***********************************************************/
	
	/* The number of guesses in one game of Hangman */
	private static final int N_GUESSES = 7;
	/* The width and the height to make the karel image */
	private static final int KAREL_SIZE = 150;
	/* The y-location to display karel */
	private static final int KAREL_Y = 230;
	/* The width and the height to make the parachute image */
	private static final int PARACHUTE_WIDTH = 300;
	private static final int PARACHUTE_HEIGHT = 130;
	/* The y-location to display the parachute */
	private static final int PARACHUTE_Y = 50;
	/* The y-location to display the partially guessed string */
	private static final int PARTIALLY_GUESSED_Y = 430;
	/* The y-location to display the incorrectly guessed letters */
	private static final int INCORRECT_GUESSES_Y = 460;
	/* The fonts of both labels */
	private static final String PARTIALLY_GUESSED_FONT = "Courier-36";
	private static final String INCORRECT_GUESSES_FONT = "Courier-26";
	
	/***********************************************************
	 *              Instance Variables                         *
	 ***********************************************************/
	
	/* An object that can produce pseudo random numbers */
	private RandomGenerator rg = new RandomGenerator();
	private GCanvas canvas = new GCanvas();
	GLabel partiallyGuessed, partiallyIncorrect;
	GLine line;
	
	/***********************************************************
	 *                    Methods                              *
	 ***********************************************************/
	
	public void run() {
		drawBackground();
		drawKarel();
		drawParachute();
		drawLines();
		println("Welcome to Hangman.");
		String word = getRandomWord(openFileReader());
		println("Your new word looks like this: " + getDashes(word));
		guessMode(word);
	}
	
	public void init() {
		add(canvas);
	}
	
	private void drawBackground() {
		GImage bg = new GImage("background.jpg");
		bg.setSize(canvas.getWidth(), canvas.getHeight());
		canvas.add(bg, 0, 0);
	}
	
	private void drawKarel() {
		GImage karel = new GImage("karel.png");
		karel.setSize(KAREL_SIZE, KAREL_SIZE);
		canvas.add(karel, (canvas.getWidth() - KAREL_SIZE)/2, KAREL_Y);
	}
	
	private void drawParachute() {
		GImage parachute = new GImage("parachute.png");
		parachute.setSize(PARACHUTE_WIDTH, PARACHUTE_HEIGHT);
		canvas.add(parachute, (canvas.getWidth() - PARACHUTE_WIDTH)/2, PARACHUTE_Y);
	}
	
	private void drawLines() {
		double space = PARACHUTE_WIDTH / 6;
		for(int i = 0; i < 4; i++) {
			line = new GLine(canvas.getWidth() / 2 + i*space, PARACHUTE_Y + PARACHUTE_HEIGHT,
										canvas.getWidth() / 2,KAREL_Y);
			canvas.add(line);
		}
		for(int i = 0; i < 4; i++) {
			line = new GLine(canvas.getWidth() / 2 - i*space, PARACHUTE_Y + PARACHUTE_HEIGHT,
										canvas.getWidth() / 2,KAREL_Y);
			canvas.add(line);
		}
	}
	
	private void removeKarel() {
		GImage karelIn = new GImage("karelFlipped.png");
		karelIn.setSize(KAREL_SIZE, KAREL_SIZE);
		GImage karelOut = (GImage) canvas.getElementAt((canvas.getWidth() - KAREL_SIZE)/2, KAREL_Y);
		canvas.remove(karelOut);
		canvas.add(karelIn, (canvas.getWidth() - KAREL_SIZE)/2, KAREL_Y);
	}
	
	private void cancelLine(int guesses) {
		if(guesses == 6) {
			line = (GLine) canvas.getElementAt(canvas.getWidth() / 2 + PARACHUTE_WIDTH / 6*3, PARACHUTE_Y + PARACHUTE_HEIGHT);
		} else if(guesses == 5) {
			line = (GLine) canvas.getElementAt(canvas.getWidth() / 2 - PARACHUTE_WIDTH / 6*3, PARACHUTE_Y + PARACHUTE_HEIGHT);
		} else if(guesses == 4) {
			line = (GLine) canvas.getElementAt(canvas.getWidth() / 2 + PARACHUTE_WIDTH / 6*2, PARACHUTE_Y + PARACHUTE_HEIGHT);
		} else if(guesses == 3) {
			line = (GLine) canvas.getElementAt(canvas.getWidth() / 2 - PARACHUTE_WIDTH / 6*2, PARACHUTE_Y + PARACHUTE_HEIGHT);
		} else if(guesses == 2) {
			line = (GLine) canvas.getElementAt(canvas.getWidth() / 2 + PARACHUTE_WIDTH / 6, PARACHUTE_Y + PARACHUTE_HEIGHT);
		} else if(guesses == 1) {
			line = (GLine) canvas.getElementAt(canvas.getWidth() / 2 - PARACHUTE_WIDTH / 6, PARACHUTE_Y + PARACHUTE_HEIGHT);
		} else if(guesses == 0) {
			line = (GLine) canvas.getElementAt(canvas.getWidth() / 2, PARACHUTE_Y + PARACHUTE_HEIGHT);
		} 
		canvas.remove(line);
	}
	
	private void setText(String output, String incorrect) {
		partiallyGuessed = new GLabel(output);
		partiallyIncorrect = new GLabel(incorrect);
		partiallyGuessed.setFont(PARTIALLY_GUESSED_FONT);
		partiallyIncorrect.setFont(INCORRECT_GUESSES_FONT);
		canvas.add(partiallyGuessed, (canvas.getWidth() - partiallyGuessed.getWidth())/2, PARTIALLY_GUESSED_Y);
		canvas.add(partiallyIncorrect, (canvas.getWidth() - partiallyIncorrect.getWidth())/2, INCORRECT_GUESSES_Y);
	}
	
	
	private String getDashes(String word) {
		String output = "";
		for(int i = 0; i < word.length(); i++) {
			output += "-";
		}
		return output;
	}

	
	private void guessMode(String word) {
		int guesses = N_GUESSES;
		String output = getDashes(word);
		String incorrect = "";
		setText(output, incorrect);
		while(guesses > 0) {	
			String x = "";
			int counter = 0;
			println("You have " + guesses + " guesses left.");
			String guess = readLine("Your guess: ");
			char ch2 = Character.toUpperCase(guess.charAt(0));
			if(guess.length() == 1 && Character.isLetter(ch2) == true) {
				for(int i = 0; i < word.length(); i++) {
					char ch1 = word.charAt(i);
					if(ch1 == ch2) {
						x += ch2;
					} else if(ch1 != ch2 && 
								Character.isLetter(output.charAt(i)) == false) { 
						x += "-";
						counter ++;
					} else if(ch1 != ch2 && 
							Character.isLetter(output.charAt(i)) == true) { 
						x += output.charAt(i);
						counter ++;
					}
				}
				if(counter < word.length()) {
					println("That guess is correct.");
					if(x.equals(word)) {
						partiallyGuessed.setLabel(x);
						println("You win.");
						println("The word was: " + word + ".");
						break;
					}
					println("Your word now looks like this: " + x);
					output = x;
				} else if(counter == word.length()) {
					guesses--;
					incorrect += ch2;
					cancelLine(guesses);
					if(guesses == 0) {
						cancelLine(guesses);
						removeKarel();
						println("There are no " + guess + "'s in the word.");
						println("You're completely hung.");
						println("The word was: " + word +".");
					} else {
						println("There are no " + guess + "'s in the word.");
						println("Your word now looks like this: " + x);
						output = x;
					}
				}
			} else {
				println("Illegal! Please enter a single letter.");
				println("Your word now looks like this: " + output);
			}	
			partiallyGuessed.setLabel(output);
			partiallyIncorrect.setLabel(incorrect);
			partiallyIncorrect.setLocation((canvas.getWidth() - partiallyIncorrect.getWidth())/2, INCORRECT_GUESSES_Y);
		}
	}
	
	private BufferedReader openFileReader() {
		BufferedReader rd = null;
		while(rd == null) {
			try {
				rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			} catch (IOException ex) {
				println("Can't open that file.");
			}
		}
		return rd;
	}
	

	private String getRandomWord(BufferedReader rd) {
		ArrayList<String> wordList = new ArrayList<>();
		Scanner scanner = new Scanner(rd);
		while(scanner.hasNextLine()) {
			wordList.add(scanner.nextLine());
		}
		scanner.close();
		int TotalCount = wordList.size();
		int index = rg.nextInt(TotalCount);
		String word = wordList.get(index);
		return word;
	}
}