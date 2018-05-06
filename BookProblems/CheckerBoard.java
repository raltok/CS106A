import acm.program.*;
import acm.graphics.*;

public class CheckerBoard extends GraphicsProgram {
	
	private static final int N_ROWS = 10; //constants defined inside the class,
	private static final int N_COLUMNS = 10; //but not inside the method.
	
/*
 * We build the CheckerBoard ROW BY ROW
 */
	public void run() {
		int sqSquare = getHeight() / N_ROWS; //this gives me how many squares I need in order for the board to fit in the window
		for(int i = 0; i < N_ROWS; i++) {
			for(int j = 0; j < N_COLUMNS; j++) {
				double x = j * sqSquare;
				double y = i * sqSquare;
				GRect sq = new GRect(x, y, sqSquare, sqSquare);
				sq.setFilled((i+j) % 2 != 0);
				add(sq);
			}
		}
	}
}
