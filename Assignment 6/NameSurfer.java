/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		
	    add(new JLabel("Name"), SOUTH);
	    textField = new JTextField(TEXT_FIELD_WIDTH);
	    textField.addActionListener(this);
	    add(textField, SOUTH);
	    
	    add(new JButton("Graph"), SOUTH);
	    add(new JButton("Clear"), SOUTH);
	    
	    graph = new NameSurferGraph();
		add(graph);
		
		data = new NameSurferDataBase(NAMES_DATA_FILE);
	    
	    addActionListeners();
	}
	

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(e.getSource() == textField) {
			graph.addEntry(data.findEntry(caseInsensitive(textField.getText())));
			graph.update();
		} else if(cmd.equals("Graph")) {
			graph.addEntry(data.findEntry(caseInsensitive(textField.getText())));
			graph.update();
		} else if(cmd.equals("Clear")) {
			graph.clear();
			graph.update();
		}
	}
	
	private String caseInsensitive(String name) {
		String nameCorrect = "";
		for(int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if(i == 0) {
				nameCorrect += Character.toUpperCase(ch);
			} else {
				nameCorrect += Character.toLowerCase(ch);
			}
		}
		return nameCorrect;
	}
	
	private JTextField textField;
	private NameSurferGraph graph;
	private NameSurferDataBase data;
}
