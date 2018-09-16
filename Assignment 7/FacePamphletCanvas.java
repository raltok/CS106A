/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		if(message != null) {
			remove(message);
			newMessage(msg);
		} else {
			newMessage(msg);
		}
	}
	
	private void newMessage(String msg) {
		message = new GLabel(msg);
		message.setLocation((getWidth()/2) - (message.getWidth()/2), 
				getHeight() - BOTTOM_MESSAGE_MARGIN);
		message.setFont(MESSAGE_FONT);
        add(message);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		setName(profile);
		setImage(profile);
		setStatus(profile);
		setFriends(profile);
	}
	
	private void setFriends(FacePamphletProfile profile) {
		int i = 0;
		GLabel friends = new GLabel("Friends:");
		friends.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friends, getWidth() / 2, name.getY() + IMAGE_MARGIN);
		Iterator <String> iterator = profile.getFriends();
		while(iterator.hasNext()) { 
			i++;
			GLabel list = new GLabel(iterator.next());
			list.setFont(PROFILE_FRIEND_FONT );
			add(list, getWidth()/2, friends.getY() + list.getHeight()*i);
		}
	}	
	
	
	private void setStatus(FacePamphletProfile profile) {
		if(profile.getStatus() == null) {
			GLabel status = new GLabel("No current status");
			status.setFont(PROFILE_STATUS_FONT);
			add(status, LEFT_MARGIN, rect.getY() + IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent());
		} else {
			GLabel status = new GLabel("" + profile.getName() + " is " + profile.getStatus());
			status.setFont(PROFILE_STATUS_FONT);
			add(status, LEFT_MARGIN, rect.getY() + IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent());
		}
	}
	
	private void setImage(FacePamphletProfile profile) {
		if(profile.getImage() == null) {
			rect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(rect, LEFT_MARGIN, name.getY() + IMAGE_MARGIN);
			GLabel noImage = new GLabel("No Image");
			noImage.setFont(PROFILE_IMAGE_FONT);
			add(noImage, (LEFT_MARGIN + (rect.getWidth()/2) - noImage.getWidth()/2), 
					rect.getY() + (rect.getHeight()/2) + 
						(name.getAscent() - name.getDescent()) / 2);
		} else {
			GImage image = profile.getImage();
			image.scale(IMAGE_WIDTH/image.getWidth(), IMAGE_HEIGHT/image.getHeight());
			add(image, LEFT_MARGIN, name.getY() + IMAGE_MARGIN);
		}
	}
	
	private void setName(FacePamphletProfile profile) {
		name = new GLabel(profile.getName());
		name.setColor(Color.BLUE);
		name.setFont(PROFILE_NAME_FONT);
		add(name, LEFT_MARGIN, TOP_MARGIN + name.getAscent());
	}
	
	private GRect rect;
	private GLabel name;
	private GLabel message;
}