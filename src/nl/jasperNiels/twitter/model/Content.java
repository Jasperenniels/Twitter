package nl.jasperNiels.twitter.model;

import java.util.Observable;

import android.text.SpannableString;

public class Content extends Observable {

	private SpannableString text;
	
	public Content(SpannableString text) {
		this.text = text;
	}
	
	public SpannableString getText() {
		return text;
	}
	
}
