package nl.jasperNiels.twitter.model;

import java.util.Observable;

public class User extends Observable {
	private String firstname;
	private String lastname;
	
	public User(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getFullName() {
		return firstname + " " + lastname;
	}
	
}
