/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */


import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		categoryOccupied = new int[nPlayers][N_CATEGORIES];
		dice = new int[N_DICE];
		scores = new int[nPlayers][N_CATEGORIES];
		int rounds = 0;
		while(rounds < N_SCORING_CATEGORIES*2) {
			for(int i = 1; i < nPlayers + 1; i++) {
				int turn = 0;
				display.printMessage(playerNames[i - 1] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
				display.waitForPlayerToClickRoll(i);
				for(int j = 0; j < dice.length; j++) {
					dice[j] = rgen.nextInt(1, 6);
				}
				display.displayDice(dice);
				while (turn < 2) {
					display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\"");
					display.waitForPlayerToSelectDice();
					for(int j = 0; j < dice.length; j++) {
						if(display.isDieSelected(j) == true) {
							dice[j] = rgen.nextInt(1, 6);
						}
					}
					turn++;
					display.displayDice(dice);
				}
				display.printMessage("Select a category for this roll.");
				while(true) {
					int category = display.waitForPlayerToSelectCategory();
					if(categoryOccupied[i - 1][category - 1] == 0) {
						boolean p = checkCategory(dice, category);
						scores[i - 1][category - 1] = calculateScore(dice, category, p, i);
						display.updateScorecard(category, i, scores[i - 1][category - 1]);
						categoryOccupied[i - 1][category - 1] = 1;
						rounds++;
						break;
					} else {
						display.printMessage("Error! Select a free category for this roll.");
					}	
				}
			}
		}
		finishGame(scores);
	}

	private void finishGame(int scores[][]) {
		for(int j = 1; j < nPlayers + 1; j++) {
			int upperScore = 0;
			int upperBonus = 0;
			int lowerScore = 0;
			for(int i = 0; i < UPPER_SCORE; i++) {
				upperScore += scores[j - 1][i];
			}
			if(upperScore >= 63) {
				upperBonus = 35;
			}
			for(int i = 8; i < LOWER_SCORE; i++) {
				lowerScore += scores[j - 1][i];
			}
			display.updateScorecard(UPPER_SCORE, j, upperScore);
			display.updateScorecard(UPPER_BONUS, j, upperBonus);
			display.updateScorecard(LOWER_SCORE, j, lowerScore);
			int total = upperScore + upperBonus + lowerScore;
			scores[j -1][TOTAL-1] = total;
			display.updateScorecard(TOTAL, j, total);
		}
		checkWinner(scores);
	}
	
	private void checkWinner(int scores[][]) {
		int score = 0;
		int player = 0;
		for(int i = 1; i < nPlayers; i++) { 
			if (scores[player][TOTAL - 1] < scores[i][TOTAL - 1]) {
		         score = scores[i][TOTAL - 1];
		         player = i;
			} else {
				score = scores[i-1][TOTAL - 1];
				player = i-1;
			}
		}
		display.printMessage("Congratulations, " + playerNames[player] + 
				", you're the winner with a total score of " + score + ".");
	}
	
	private boolean checkCategory(int dice[], int category) {
		boolean result = false;
		ArrayList<Integer> ones = new ArrayList<Integer>();
		ArrayList<Integer> twos = new ArrayList<Integer>();
		ArrayList<Integer> threes = new ArrayList<Integer>();
		ArrayList<Integer> fours = new ArrayList<Integer>();
		ArrayList<Integer> fives = new ArrayList<Integer>();
		ArrayList<Integer> sixes = new ArrayList<Integer>();
		for(int i = 0; i < dice.length; i++) {
			if(dice[i] == ONES) {
				ones.add(dice[i]);
			} else if(dice[i] == TWOS) {
				twos.add(dice[i]);
			} else if(dice[i] == THREES) {
				threes.add(dice[i]);
			} else if(dice[i] == FOURS) {
				fours.add(dice[i]);
			} else if(dice[i] == FIVES) {
				fives.add(dice[i]);
			} else if(dice[i] == SIXES) {
				sixes.add(dice[i]);
			}
		}
		if((category >= ONES && category <= SIXES) || category == CHANCE) {
			result = true;
		} else if(category == THREE_OF_A_KIND) {
			if(ones.size() >= 3 || twos.size() >= 3 || threes.size() >= 3 ||
					fours.size() >= 3 || fives.size() >= 3 || sixes.size() >= 3) {
				result = true;
			}
		} else if(category == FOUR_OF_A_KIND) {
			if(ones.size() >= 4 || twos.size() >= 4 || threes.size() >= 4 ||
					fours.size() >= 4 || fives.size() >= 4 || sixes.size() >= 4) {
				result = true;
			}
		} else if(category == YAHTZEE) {
			if(ones.size() == 5 || twos.size() == 5 || threes.size() == 5 ||
					fours.size() == 5 || fives.size() == 5 || sixes.size() == 5) {
				result = true;
			}
		} else if(category == FULL_HOUSE) {
			if((ones.size() == 3 || twos.size() == 3 || threes.size() == 3 ||
					fours.size() == 3 || fives.size() == 3 || sixes.size() == 3) &&
						(ones.size() == 2 || twos.size() == 2 || threes.size() == 2 ||
							fours.size() == 2 || fives.size() == 2 || sixes.size() == 2)) {
				result = true;
			}
		} else if(category == LARGE_STRAIGHT) {
			if((ones.size() == 1 && twos.size() == 1 && threes.size() == 1 &&
					fours.size() == 1 && fives.size() == 1)
						|| (twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && 
								fives.size() == 1 && sixes.size() == 1)) {
				result = true;
			}
		} else if(category == SMALL_STRAIGHT) {
			if(ones.size() == 1 && twos.size() == 1 && threes.size() == 1 && fours.size() == 1){
				result = true;
			} else if(twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && fives.size() == 1){
				result = true;
			} else if(threes.size() == 1 && fours.size() == 1 && fives.size() == 1 && sixes.size() == 1){
				result = true;
			}
		}
		ones.clear(); 
		twos.clear(); 
		threes.clear(); 
		fours.clear(); 
		fives.clear(); 
		sixes.clear();
		return result;
	}
	
	private int calculateScore(int dice[], int category, boolean p, int i) {
		int score = 0;
		if(p == false) {
			display.updateScorecard(category, i, 0);
		} else {
			if(category == ONES || category == TWOS || category == THREES || category == FOURS
					|| category == FIVES || category == SIXES) {
				for(int j = 0; j < dice.length; j++) {
					if(category == ONES) {
						if(dice[j] == 1) {
							score += dice[j];
						}
					} else if(category == TWOS) {
						if(dice[j] == 2) {
							score += dice[j];
						}
					} else if(category == THREES) {
						if(dice[j] == 3) {
							score += dice[j];
						}
					} else if(category == FOURS) {
						if(dice[j] == 4) {
							score += dice[j];
						}
					} else if(category == FIVES) {
						if(dice[j] == 5) {
							score += dice[j];
						}
					} else if(category == SIXES) {
						if(dice[j] == 6) {
							score += dice[j];
						}
					}
				}
			} else if(category == THREE_OF_A_KIND) {
				for(int j = 0; j < dice.length; j++) {
					score += dice[j];
				}
			} else if(category == FOUR_OF_A_KIND) {
				for(int j = 0; j < dice.length; j++) {
					score += dice[j];
				}
			} else if(category == FULL_HOUSE) {
				score = 25;
			} else if(category == SMALL_STRAIGHT) {
				score = 30;
			} else if(category == LARGE_STRAIGHT) {
				score = 40;
			} else if(category == YAHTZEE) {
				score = 50;
			} else if(category == CHANCE) {
				for(int j = 0; j < dice.length; j++) {
					score += dice[j];
				}
			} 
		}
		return score;
	}
	
/* Private instance variables */
	int categoryOccupied[][];
	int dice[];
	private int scores[][];
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
}