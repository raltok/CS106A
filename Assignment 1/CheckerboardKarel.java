/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		if (frontIsBlocked()) {
			turnLeft();
			while(frontIsClear()) {
			oddRow();
			}
		} else {
		while(frontIsClear()) {
		oddRow();
		if(frontIsClear() || rightIsClear()) {
			passage();
			evenRow();
			if(frontIsClear() || rightIsClear()) {
			passage();
				}
			}
		}
	}
}
	private void oddRow() {
		putBeeper();
		move();
		while(frontIsClear()) {
			move();
			putBeeper();
			if(frontIsClear()) {
			move(); }
			}
		turnAround();
		while(frontIsClear()) {
			move();
		}
}
	private void passage() {
		turnRight();
		move();
		turnLeft();
		turnAround();
		}
	private void evenRow() {
		move();
		putBeeper();
		while(frontIsClear()) {
			move();
			if(frontIsClear()) {
			move();
			putBeeper(); 
				}
			}
		turnAround();
		while(frontIsClear()) {
			move();
		}
	}
}