import acm.program.*;
import acm.util.RandomGenerator;

import java.util.*;

public class RadioactiveDecay extends ConsoleProgram {
	
	public void run() {
		int atoms = 10000;
		int x = 10000;
		int year = 0;
		println("There are " + atoms + " atoms initially.");
		while(atoms != 0) {
			for(int i = 0; i <= x; i++) {
				boolean survive = rgen.nextBoolean();
				if(survive) atoms--;
			}
			x = atoms;
			year++;
			println("There are "+ atoms + " atoms at the end of year " + year);
		}
	}
	
	private int atoms;
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
