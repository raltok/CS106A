/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		times = 0;
		array = new ArrayList<NameSurferEntry>();
		Entry = null;
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		array.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		Entry = entry;
		if(Entry != null) {
			if(array.contains(Entry) == false) {
				array.add(Entry);
			} else Entry = null;
		}
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		uploadArray();
		createLines();
		createLabels();
		drawGraph();
	}
	
	private void createLines() {
		GLine line1 = new GLine(0, 0 + GRAPH_MARGIN_SIZE,
				getWidth(), 0 + GRAPH_MARGIN_SIZE);
		GLine line2 = new GLine(0, getHeight()  - GRAPH_MARGIN_SIZE,
				getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(line1);
		add(line2);
		for(int i = 0; i < NDECADES; i++) {
			GLine line = new GLine(0 + (getWidth() / NDECADES)*i, 0, 
					0 + (getWidth() / NDECADES)*i , getHeight());
			add(line);
		}
	}
	
	private void createLabels() {
		for(int i = 0; i < NDECADES; i++) {
			int year = START_DECADE + i*10;
			GLabel label = new GLabel("" + year);
			add(label, 0 + i*(getWidth() / NDECADES), getHeight() - DECADE_LABEL_MARGIN_SIZE);
		}
	}
	
	private void uploadArray() {
		double lowerBound = getHeight() - GRAPH_MARGIN_SIZE;
		map = new HashMap<Integer, Double>();
		map.put(0, lowerBound);
		map.put(MAX_RANK, lowerBound);
		for(int i = 1; i < 1000; i++) {
			double y = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2) * i / MAX_RANK;
			map.put(i, y);
		}	
	}
	
	private void drawGraph() {
		times = 0;
		if(Entry != null || array.isEmpty() == false) {
			double x1 = 0;
			double y1 = 0;
			double x2 = getWidth()/2;
			double y2 = getHeight()/2;
			for(int j = 0; j < array.size(); j++) {
				times++;
				if(times % 5 == 0) times = 1;
				for(int i = 1; i < NDECADES; i++) {
					x1 = 0 + (getWidth() / NDECADES)*i - (getWidth() / NDECADES);
					y1 = map.get(array.get(j).getRank(i));
					x2 = x1 + (getWidth() / NDECADES);
					y2 = map.get(array.get(j).getRank(i + 1));
					GLine line = new GLine(x1,y1,x2,y2);
					line.setColor(setColors());
					add(line);
				}
			}
			setLabels();
		}
	}
	
	private Color setColors() {
		if(times == 1) {
			return Color.BLACK;
		} else if(times == 2) {
			return Color.RED;
		} else if(times == 3) {
			return Color.BLUE;
		} else if(times == 4) {
			return Color.MAGENTA;
		} else return null;
	}
	
	private void setLabels() {
		times = 0;
		if(Entry != null || array.isEmpty() == false) {
				for(int j = 0; j < array.size(); j++) {
					times++;
					if(times % 5 == 0) times = 1;
					for(int i = 1; i < NDECADES + 1; i++) {
						int x = 0 + (getWidth() / NDECADES)*i - (getWidth() / NDECADES);
						Double y = map.get(array.get(j).getRank(i));
						if(array.get(j).getRank(i) == 0) {
							GLabel label = new GLabel(""+ array.get(j).getName() + "*");
							label.setColor(setColors());
							add(label, x, y);
						} else {
							GLabel label = new GLabel(""+ array.get(j).getName() + " " 
									+ array.get(j).getRank(i));
							label.setColor(setColors());
							add(label, x ,y);
						}
					}
				}
			}
		}
	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private NameSurferEntry Entry;
	private HashMap<Integer, Double> map;
	private ArrayList<NameSurferEntry> array;
	private int times;
}