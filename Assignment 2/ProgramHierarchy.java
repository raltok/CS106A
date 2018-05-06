package Assignment2_2007;
/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	private static final double CLASS_BOX_WIDTH = 200;
	private static final double CLASS_BOX_HEIGHT = 70;
	private static final double CONNECTOR = 30;
	private static final double MIDDLE_LINE = 50;
	
	public void run() {
		double x = (getWidth() - CLASS_BOX_WIDTH * 3 - CONNECTOR * 2)/2;
		double y = (getHeight() - CLASS_BOX_HEIGHT * 2 - MIDDLE_LINE)/2;
		lowerBoxes(x, y);
		upperBox(x, y);
		middleLine(x,y);
		otherLines(x,y);
		addProgram(x,y);
		addGraphics(x,y);
		addConsole(x,y);
		addDialog(x,y);
	}
	
	private void lowerBoxes(double x, double y) {
		for(int i = 0; i<3; i++) {
			double x2 = y + CLASS_BOX_HEIGHT + MIDDLE_LINE;
			double x1 = x + i*CLASS_BOX_WIDTH + i*CONNECTOR;
			add(new GRect(x1, x2,CLASS_BOX_WIDTH,CLASS_BOX_HEIGHT));
		}
	}
	
	private void upperBox(double x, double y) {
		double x2 = y;
		double x1 = x + CLASS_BOX_WIDTH + CONNECTOR;
		add(new GRect(x1, x2,CLASS_BOX_WIDTH,CLASS_BOX_HEIGHT));
	}
	
	private void middleLine(double x, double y) {
		double y1 = y + CLASS_BOX_HEIGHT;
		double x1 = x + CLASS_BOX_WIDTH + CONNECTOR + CLASS_BOX_WIDTH/2;
		double y2 = y1 + MIDDLE_LINE;
		double x2 = x1;
		add(new GLine(x1, y1, x2, y2));
	}
	
	private void otherLines(double x, double y) {
		double y1 = y + CLASS_BOX_HEIGHT;
		double x1 = x + CLASS_BOX_WIDTH + CONNECTOR + CLASS_BOX_WIDTH/2;
		double y2 = y1 + MIDDLE_LINE;
		double x2 = x + CLASS_BOX_WIDTH/2;
		double x3 = x1 + CLASS_BOX_WIDTH + CONNECTOR;
		add(new GLine(x1, y1, x2, y2));
		add(new GLine(x1, y1, x3, y2));
	}
	
	private void addProgram(double x, double y) {
		double x2 = y;
		double x1 = x + CLASS_BOX_WIDTH + CONNECTOR;
		GLabel label = new GLabel("Program", x1, x2);
		add(label);
		label.setLocation(x1  + (CLASS_BOX_WIDTH - label.getWidth())/2, 
							x2 + label.getAscent() +
							(CLASS_BOX_HEIGHT - label.getHeight())/2);

	}
	
	private void addGraphics(double x, double y) {
		double x2 = y + CLASS_BOX_HEIGHT + MIDDLE_LINE;
		double x1 = x;
		GLabel label = new GLabel("GraphicsProgram", x1, x2);
		add(label);
		label.setLocation (x1 + (CLASS_BOX_WIDTH - label.getWidth())/2, 
							x2 + label.getAscent() +
							(CLASS_BOX_HEIGHT - label.getHeight())/2);
	}
	
	private void addConsole(double x, double y) {
		double x2 = y + CLASS_BOX_HEIGHT + MIDDLE_LINE;
		double x1 = x + CLASS_BOX_WIDTH + CONNECTOR;
		GLabel label = new GLabel("ConsoleProgram", x1, x2);
		add(label);
		label.setLocation (x1 + (CLASS_BOX_WIDTH - label.getWidth())/2, 
							x2 + label.getAscent() + 
							(CLASS_BOX_HEIGHT - label.getHeight())/2);
	}
	
	private void addDialog(double x, double y) {
		double x2 = y + CLASS_BOX_HEIGHT + MIDDLE_LINE;
		double x1 = x + CLASS_BOX_WIDTH*2 + CONNECTOR*2;
		GLabel label = new GLabel("DialogProgram", x1, x2);
		add(label);
		label.setLocation (x1 + (CLASS_BOX_WIDTH - label.getWidth())/2, 
							x2 + label.getAscent() +
							(CLASS_BOX_HEIGHT - label.getHeight())/2);
	}
}