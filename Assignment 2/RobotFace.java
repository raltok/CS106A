import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class RobotFace extends GraphicsProgram {
	
	private static final double HEAD_WIDTH = 250;
	private static final double HEAD_HEIGHT = 400;
	private static final double EYE_RADIUS = 30;
	private static final double MOUTH_HEIGHT = 50;
	private static final double MOUTH_WIDTH = 150;
	
	
	public void run() {
		double x = (getWidth() - HEAD_WIDTH) /2;
		double y = (getHeight() - HEAD_HEIGHT) /2;
		drawHead(x,y, Color.GRAY);
		drawEye1(x, y, Color.YELLOW);
		drawEye2(x, y, Color.YELLOW);
		drawMouth(x, y, Color.WHITE);
	}
	
	private void drawHead(double x, double y, Color color) {
		GRect head = new GRect(x, y, HEAD_WIDTH, HEAD_HEIGHT);
		head.setFilled(true);
		head.setFillColor(color);
		add(head);
	}
	
	private void drawEye1(double x, double y, Color color) {
		double xEye = x + HEAD_WIDTH /4 - EYE_RADIUS;
		double yEye = y + HEAD_HEIGHT /4 - EYE_RADIUS;
		GOval eyes = new GOval(xEye, yEye, 2 * EYE_RADIUS, 2 * EYE_RADIUS);
		eyes.setFilled(true);
		eyes.setColor(color);
		eyes.setFillColor(color);
		add(eyes);
		
	}
	
	private void drawEye2(double x, double y, Color color) {
		double xEye = x + HEAD_WIDTH  - EYE_RADIUS - HEAD_WIDTH /4;
		double yEye = y + HEAD_HEIGHT /4 - EYE_RADIUS;
		GOval eyes = new GOval(xEye, yEye, 2 * EYE_RADIUS, 2 * EYE_RADIUS);
		eyes.setFilled(true);
		eyes.setColor(color);
		eyes.setFillColor(color);
		add(eyes);
	}
	
	private void drawMouth(double x, double y, Color color) {
		double xMouth = x + HEAD_WIDTH / 2 - MOUTH_WIDTH /2;
		double yMouth = y + HEAD_HEIGHT - HEAD_HEIGHT /4;
		GRect mouth = new GRect(xMouth, yMouth, MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setFilled(true);
		mouth.setColor(color);
		mouth.setFillColor(color);
		add(mouth);
		
	}
}