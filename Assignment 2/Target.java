package Assignment2_2007;
/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	private static final int OUTER_CIRCLE_RADIUS = 72;
	private static final double WHITE_CIRCLE_RADIUS = 46.8;
	private static final double INNER_CIRCLE_RADIUS = 21.6;
	
	public void run() {
		double x = getWidth() /2;
		double y = getHeight() /2;
		drawFirst(x, y, Color.RED);
		drawSecond(x, y, Color.WHITE);
		drawThird(x, y, Color.RED);

	}
	
	private void drawFirst(double x, double y, Color color) {
		double i = x - OUTER_CIRCLE_RADIUS ;
		double j = y - OUTER_CIRCLE_RADIUS;
		GOval circle = new GOval (i, j, OUTER_CIRCLE_RADIUS*2, OUTER_CIRCLE_RADIUS*2);
		circle.setFilled(true);
		circle.setFillColor(color);
		add(circle);
	}
	
	private void drawSecond(double x, double y, Color color) {
		double i = x - WHITE_CIRCLE_RADIUS ;
		double j = y - WHITE_CIRCLE_RADIUS;
		GOval circle = new GOval (i, j, WHITE_CIRCLE_RADIUS*2, WHITE_CIRCLE_RADIUS*2);
		circle.setFilled(true);
		circle.setFillColor(color);
		add(circle);
	}
	
	private void drawThird(double x, double y, Color color) {
		double i = x - INNER_CIRCLE_RADIUS ;
		double j = y - INNER_CIRCLE_RADIUS;
		GOval circle = new GOval (i, j, INNER_CIRCLE_RADIUS*2, INNER_CIRCLE_RADIUS*2);
		circle.setFilled(true);
		circle.setFillColor(color);
		add(circle);
	}
}
