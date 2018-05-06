package Assignment2_2007;
/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
		
	public void run() {
		int x = readInt("Enter a number: ");
		int k = 0;
		while(x!= 1) {
			if(x % 2 != 0) {
				int y = x*3 + 1;
				println(x + " is odd, so I make 3n + 1: " + y);
				x = y;
				k += 1;
			} else if (x % 2 == 0) {
				int y = x/2;
				println(x + " is even so I take half: " + y);
				x = y;
				k += 1;
			}
		}
		println("The process took " + k + " to reach 1");
	}
}