package nl.jasperNiels.twitter.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * Tweet contains more data like Sender, contents, etc. If they change (e.g.
 * sender changes name: The tweet will update the sender and tell the main
 * model).
 * 
 * @author enjee
 */
public class Tweet extends Observable implements Observer {

	private User sender;
	private Date date;
	private Content content;
	private ArrayList<Tweet> retweets;	// optioneel
	private Place place;				// optioneel

	public Tweet(User sender, Content content) { 
		this.sender = sender;
		this.date = new Date(); // set de date naar <<create>> datum
		this.content = content;
		retweets = new ArrayList<Tweet>();
		sender.addObserver(this);
		checkForPlace();
	}
	
	private void checkForPlace() {
		// Als er in de content een place zit, set die.
	}

	public User getSender() {
		return sender;
	}
	
	
	@Override
	public void update(Observable observable, Object data) {
		setChanged();
		notifyObservers();
	}
}
