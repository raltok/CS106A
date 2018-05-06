package Assignment2_2007;
/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int SENTINEL = 0;
	
	public void run() {
		println("This program finds the largest and smallest numbers.");
		int x = 0;
		int y = 0;
		int large = 0;
		int small = 0;
		x = readInt("? ");
		if(x == 0 ) {
			println("No values have been entered.");
		} else if (x !=0) {
			y = readInt("? ");
			if(y != 0) {
				if(x > y) {
					small = y;
				} else if(y > x) {
					large =y;
				}
				while(true) {
					y = readInt("? ");
					if (y == SENTINEL) break;
					if(y > large) {
						large = y;
					} else if(y < small) {
						small = y;
						}
					}
				println("smallest: " + small);
				println("largest: " + large);	
			} else if (y == 0) {
				println("smallest: " + x); 
				println("largest: " + x);
			}
		}
	}
}