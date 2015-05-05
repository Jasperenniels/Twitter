package nl.jasperNiels.twitter.model;

import java.util.Observable;

public class User extends Observable {
	private String firstname;
	private String lastname;
	private String username;
	
	
	public User(String firstname, String lastname, String username) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
	}
	
	public String getFullName() {
		return firstname + " " + lastname + " @" + username;
	}
	
}
