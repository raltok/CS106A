import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;

public class DrawLines extends GraphicsProgram{
	
	public void run() {
		addMouseListeners();
	}
	
	public void mousePressed(MouseEvent e) {
		line = new GLine(e.getX(), e.getY(), e.getX(), e.getY());
		add(line);
	}
	
	public void mouseDragged(MouseEvent e) {
		line.setEndPoint(e.getX(), e.getY());
	}

	private GLine line;
}
