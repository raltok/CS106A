import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class boxDiagram extends GraphicsProgram {
	
	public void init() {
		map = new HashMap<String, GObject>();
		label = new JLabel("Name");
		add(label, SOUTH);
		
		fontField = new JTextField(MAX_NAME);
		fontField.addActionListener(this);

		add(fontField, SOUTH);
		
		add(new JButton("Add"), SOUTH);
		add(new JButton("Remove"), SOUTH);
		add(new JButton("Clear"), SOUTH);

		addMouseListeners();
		addActionListeners();
	}
	
	public void actionPerformed(ActionEvent e) {
		String name = fontField.getText();
		String cmd = e.getActionCommand();
		if(cmd.equals("Add")) {
			addBox(name);
		} else if(cmd.equals("Remove")) {
			removeBox(name);	
		} else if(cmd.equals("Clear")) {
			clear();
		}
	}
	
	private void addBox(String name) {
		GCompound box = new GCompound();
		GRect rect = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel(name);
		double x = (BOX_WIDTH - label.getWidth()) / 2;
		double y = (BOX_HEIGHT + label.getAscent()) / 2;
		
		box.add(rect, 0, 0);
		box.add(label, x, y);
		
		add(box, (getWidth() - BOX_WIDTH)/2, (getHeight() - BOX_HEIGHT)/2);
		map.put(name, box);
	}
	
	private void removeBox(String name) {
		GObject box = map.get(name);
		if (box != null) {
			remove(box);
		}
	}
	
	private void clear() {
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()) {
			removeBox(iterator.next());
		}
		map.clear();
	}
	
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		obj = getElementAt(last);	
	}
	
	public void mouseDragged(MouseEvent e) {
		if(obj != null) {
			obj.move(e.getX() - last.getX(), e.getY() - last.getY());
			last = new GPoint(e.getPoint());
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if(obj != null) obj.sendToFront();
	}
	
	
	private static final int MAX_NAME = 25;
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	
	private JTextField fontField;
	private JLabel label;
	private GPoint last;
	private GObject obj;
	Map<String, GObject> map;
}