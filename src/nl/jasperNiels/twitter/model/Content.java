package nl.jasperNiels.twitter.model;

import java.util.Observable;

public class Content extends Observable {

	private String text;
	
	public Content(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
}
