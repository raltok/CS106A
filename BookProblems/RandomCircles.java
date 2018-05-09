import acm.program.*;
import acm.graphics.*;
import java.awt.*;
import acm.util.*;

public class RandomCircles extends GraphicsProgram {
	
	private static final int LOWER_BOUND_RADIUS = 5;
	private static final int UPPER_BOUND_RADIUS = 50;
	
	public void run() {
		for(int i = 0; i < 10; i++) {
			 double radius = rgen.nextDouble(LOWER_BOUND_RADIUS , UPPER_BOUND_RADIUS );
			 double x = rgen.nextDouble(0, getWidth());
			 double y = rgen.nextDouble(0, getHeight());
			 circle = new GOval (2 * radius, 2 * radius);
			 circle.setFilled(true);
			 circle.setColor(rgen.nextColor());
			 add(circle ,setXCoordinate(x, radius), setYCoordinate(y, radius));
		}
	}
	
	/** X coordinate is reset to ensure that the entire
	 *  circle fits inside the canvas */
	private double setXCoordinate(double x, double radius) {
		double location = 0;
		if(getWidth() < x + radius * 2) {
			location = x - radius*2 + getWidth();
		} else location = x;
		return location;
	}
	
	/** Y coordinate is reset to ensure that the entire
	 *  circle fits inside the canvas */
	private double setYCoordinate(double y, double radius) {
		double location = 0;
		if(getHeight() < y + radius * 2) {
			location = y -  radius*2 + getHeight();
		} else location = y;
		return location;
	}

	private GOval circle;
	private RandomGenerator rgen = RandomGenerator.getInstance();
}