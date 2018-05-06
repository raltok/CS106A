import acm.program.*;
import java.awt.Color;
import acm.graphics.*;

public class Checkers extends GraphicsProgram {
	
	private static final int N_ROWS = 8; //constants defined inside the class,
	private static final int N_COLUMNS = 8; //but not inside the method.
	
/*
 * We build the CheckerBoard ROW BY ROW
 */
	public void run() {
		int sqSquare = getHeight() / N_ROWS; 
		
		int Cx = (getWidth() - getHeight()) /2;
		
		for(int i = 0; i < N_ROWS; i++) {
			for(int j = Cx; j < Cx + sqSquare*8; j+=sqSquare) {
				double y = i * sqSquare;
				double x = j;
				double k = x + (( sqSquare - sqSquare / 2) /2);
				double z = y + (( sqSquare - sqSquare / 2) /2);
				GRect sq = new GRect(x, y, sqSquare, sqSquare);
				GOval circle = new GOval(k, z, sqSquare/2, sqSquare/2);
				sq.setFilled((i+j) % 2 == 0);
				sq.setFillColor(Color.GRAY);
				add(sq);
				if((i+j) % 2 == 0 && i <= 2) {
					circle.setFilled(true);
					circle.setFillColor(Color.ORANGE);
					add(circle);
				} else if((i+j) % 2 == 0 && i > 4) {
						circle.setFilled(true);
						circle.setFillColor(Color.BLACK);
						add(circle);
				}
			}
		}
	}
}