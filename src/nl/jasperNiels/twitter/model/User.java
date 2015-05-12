package nl.jasperNiels.twitter.model;

import java.util.Observable;

public class User extends Observable {
	private String id;
	private String name;
	private String screen_name;
	private String location;		// optioneel
	private String description;	
	
	
	public User(String id, String name, String screen_name) {
		this.id = id;
		this.name = name;
		this.screen_name = screen_name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getScreenName() {
		return screen_name;
	}
	
}
