/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	public void run() {
		firstColumn();
		otherColumns();
	}
	
// The first column code will be equal for every scenario
 
	private void firstColumn() {
		if(noBeepersPresent()) {
			putBeeper();
		}
		turnLeft();
		while(frontIsClear()) {
			move();
			if(noBeepersPresent()) {
				putBeeper();
			}
		}
		turnAround();
		while(frontIsClear()) {
			move();
			if(noBeepersPresent()) {
				putBeeper();
			}
		}
		turnLeft();
		
	}
	
// Now we need some kind of loop to do the other columns in a systematic way
	
	private void otherColumns() {
		while(frontIsClear() || rightIsClear()) {
		for(int i = 0; i < 4; i++) {
			move();
		}
		turnLeft();
		if(noBeepersPresent()) {
			putBeeper();
		}
		while(frontIsClear()) {
			if(noBeepersPresent()) {
				putBeeper();
			}
			move();
		}
			turnAround();
			while(frontIsClear()) {
				if(noBeepersPresent()) {
					putBeeper();
				}
				move();
			}
			turnLeft();
		}
	}
}				