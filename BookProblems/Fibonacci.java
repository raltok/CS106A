import acm.program.*;

public class Fibonacci extends ConsoleProgram {
	
	private static final double MAX_TERM_VALUE = 10000;
	
	public void run() {
		println("This program lists the Fibonacci sequence.");
		int x = 0;
		int y = 1;
		int term = x + y;
		println("" + x);
		println("" + y);
		while (term < MAX_TERM_VALUE) {
			println("" + term);
			x = y;
			y = term;
			term = x + y;
		}
	}
}
