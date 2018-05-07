import acm.program.*;

import java.awt.Color;

import acm.graphics.*;

public class addPumpkin extends GraphicsProgram {
	
	private static final double PUMPKIN_WIDTH = 200;
	private static final double PUMPKIN_HEIGHT = 200;
	
	public void run() {
		GPumpkin pumpkin = new GPumpkin(PUMPKIN_WIDTH, PUMPKIN_HEIGHT, Color.ORANGE);
		add(pumpkin, (getWidth() - PUMPKIN_WIDTH)/2,
				(getHeight() - PUMPKIN_HEIGHT) / 2);	
	}
}
