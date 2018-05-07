import acm.program.*;
import acm.graphics.*;
import java.awt.Color;

public class PacMan extends GraphicsProgram {
	
	private static final int ARC_RADIUS = 100;
	private static final int DELAY = 250;
	private static final int DY = 0;
	
	public void run() {
		start = 45;
		sweep= 270;
		pacman = setup(ARC_RADIUS, ARC_RADIUS, start, sweep, Color.YELLOW);
		add(pacman, 0, getHeight()/2  - ARC_RADIUS);
		letsgo();
	}
	
	private GArc setup(int width, int height, double start, double sweep, Color color) {
		GArc arc = new GArc(width, height, start, sweep);
		arc.setFilled(true);
		arc.setFillColor(color);
		return arc;
	}
	
	private void letsgo() {
		dx = 0.10 * getWidth();
		while(pacman.getX() - getWidth() <= 0) {
			if(pacman.getStartAngle() == 45
				&& pacman.getSweepAngle() == 270) {
				for(int i = 0; i < 3; i++) {
					closeMouth();
				}	
			} else if (pacman.getStartAngle() == 0 
						&& pacman.getSweepAngle() == 360) {
				for(int j = 0; j <3; j++) {
					openMouth();	
				}
			}
		}
	}
	
	private void closeMouth() {
		start -= 15;
		sweep += 30;
		pacman.setStartAngle(start);
		pacman.setSweepAngle(sweep);
		pacman.move(dx, DY);
		pause(DELAY);
	}
	
	private void openMouth() {
		start += 15;
		sweep -= 30;
		pacman.setStartAngle(start);
		pacman.setSweepAngle(sweep);
		pacman.move(dx, DY);
		pause(DELAY);
	}
	
	
	// Set private instance variables
	private GArc pacman;
	private double start, sweep, dx;
}