import acm.program.*;
import acm.util.RandomGenerator;

import java.util.*;

public class MonteCarloIntegration extends ConsoleProgram {
	
	private static final int SIMULATIONS = 10000;
	
	public void run() {
		double PI = (insideCircle() * 4) / SIMULATIONS;
		print("Pi is approximately "+ PI);
	}
	
	private double insideCircle() {
		int inCircle = 0;
		for(int i = 0; i <= SIMULATIONS; i++) {
			double x = rgen.nextDouble(-1.0, 1.0);
			double y = rgen.nextDouble(-1.0, 1.0);
			if(x*x + y*y <= 1) inCircle++;
		}
		return inCircle;
	}
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
