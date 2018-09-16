/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		add(new JLabel("Name "), NORTH);
	    textField1 = new JTextField(TEXT_FIELD_SIZE);
	    textField2 = new JTextField(TEXT_FIELD_SIZE);
	    textField3 = new JTextField(TEXT_FIELD_SIZE);
	    textField4 = new JTextField(TEXT_FIELD_SIZE);
	    add(textField1, NORTH);
	    
	    add(new JButton("Add"), NORTH);
	    add(new JButton("Delete"), NORTH);
	    add(new JButton("Lookup"), NORTH);
	    
	    textField2.addActionListener(this);
	    add(textField2, WEST);
	    add(new JButton("Change Status"), WEST);
	    add(new JLabel(EMPTY_LABEL_TEXT), WEST);
	    
	    textField3.addActionListener(this);
	    add(textField3, WEST);
	    add(new JButton("Change Picture"), WEST);
	    add(new JLabel(EMPTY_LABEL_TEXT), WEST);
	    
	    textField4.addActionListener(this);
	    add(textField4, WEST);
	    add(new JButton("Add Friend"), WEST);
	    
	    canvas = new FacePamphletCanvas();
	    add(canvas);
	    
	    addActionListeners();
    }

	
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Add")) {
			addProfile();
		} else if(cmd.equals("Delete")) {
			deleteProfile();
		} else if(cmd.equals("Lookup")) {
			lookProfile();
		} else if(cmd.equals("Change Status") || e.getSource() == textField2) {
			changeStatus();
		} else if(cmd.equals("Change Picture") || e.getSource() == textField3) {
			addPicture(textField3.getText());
		} else if(cmd.equals("Add Friend") || e.getSource() == textField4) {
			addFriend();
		}	
	}
	
	private void addProfile() {
		if(data.containsProfile(textField1.getText()) == false) {
			FacePamphletProfile profile = new FacePamphletProfile(textField1.getText());
			currentProfile = profile;
			data.addProfile(currentProfile);	
			canvas.removeAll();
			canvas.displayProfile(currentProfile);
			canvas.showMessage("New profile created");
		} else {
			currentProfile = data.getProfile(textField1.getText());
			canvas.removeAll();
			canvas.displayProfile(currentProfile);
			canvas.showMessage("A profile with the name " + currentProfile.getName() + 
					" already exists");
		}
	}
	
	private void deleteProfile() {
		if(data.containsProfile(textField1.getText()) == true) {
			data.deleteProfile(textField1.getText());
			currentProfile = null;
			canvas.removeAll();
			canvas.showMessage("Profile of " + textField1.getText() + " deleted");
		} else {
			currentProfile = null;
			canvas.removeAll();
			canvas.showMessage("A profile with the name " + textField1.getText() + " does not exist");
		}
	}
	
	private void lookProfile() {
		if(data.containsProfile(textField1.getText()) == true) {
			currentProfile = data.getProfile(textField1.getText());
			canvas.removeAll();
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Displaying " + currentProfile.getName() );
		} else {
			canvas.removeAll();
			currentProfile = null;
			canvas.showMessage("A profile with the name " + textField1.getText() + " does not exist");
		}
	}
	
	private void changeStatus() {
		if(currentProfile != null) {
			data.getProfile(currentProfile.getName()).setStatus(textField2.getText());
			canvas.removeAll();
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Status updated to " + textField2.getText());
		} else {
			canvas.showMessage("Please select a profile to change status");
		}
	}
	
	private void addFriend() {
		if(currentProfile != null) {
			if(data.containsProfile(textField4.getText()) == true) {
				if(data.getProfile(currentProfile.getName()).addFriend(textField4.getText()) == true) {
					data.getProfile(textField4.getText()).addFriend(currentProfile.getName());
					canvas.removeAll();
					canvas.displayProfile(currentProfile);
					canvas.showMessage(textField4.getText() + " added as a friend");
				} else {
					canvas.showMessage(currentProfile.getName() + " already has " + 
							textField4.getText() + " as a friend");
				}
			} else {
				canvas.showMessage(textField4.getText() + " does not exist");
			}
		} else {
			canvas.showMessage("Please select a profile to add friend");
		}
	}
	
	private void addPicture(String filename) {
		if(currentProfile == null) {
			canvas.showMessage("Please select a profile to change picture");
			return;
		}
		GImage image = null;
		try {
			image = new GImage(filename);
		} catch(ErrorException ex) {
			canvas.showMessage("Unable to open image file: " + filename);
			return;
			}
		if(image != null) {
			currentProfile.setImage(image);
			canvas.removeAll();
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Picture updated");
		}
	}

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private FacePamphletCanvas canvas;
    FacePamphletDatabase data = new FacePamphletDatabase();
    private FacePamphletProfile currentProfile;
}