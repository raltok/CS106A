import acm.graphics.*;
import acm.program.*;

public class flipHorizontal extends GraphicsProgram{
	
	public void run() {
		GImage image = new GImage("Milkmaid.jpeg");
		add(flipIt(image));
	}
	
	private GImage flipIt(GImage image) {
		int[][] array = image.getPixelArray();
		int width = array[0].length;
		int height = array.length;
		for(int i = 0; i < width / 2; i++) {
			for(int j = 0; j < height; j++) {
				int p1 = width - 1 - i;
				int temp = array[j][i];
				array[j][i] = array[j][p1];
				array[j][p1] = temp;
			}
		}
		return new GImage(array);
	}
	
/* 	Set the dimensions of the window */
	public static final int APPLICATION_WIDTH = 350;
	public static final int APPLICATION_HEIGHT = 390;
}