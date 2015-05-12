package nl.jasperNiels.twitter.model;

import java.net.URL;
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

	private String id_str;
	private User sender; //name
	private String date; //created_at Thu Aug 09 12:23:42 +0000 2012
	private Content content; // text
	private ArrayList<Tweet> retweets;	// optioneel
	private Place place;				// optioneel
	private ArrayList<URL> urls;		// optioneel
	// Links, hashtags, id_str, etc.

	public Tweet(User sender, Content content, String date) { 
		this.sender = sender;
		this.date = date; // set de date naar <<create>> datum
		this.content = content;
		retweets = new ArrayList<Tweet>();
		sender.addObserver(this);
//		content.addObserver(this); doen we nog even niks mee
		checkForPlace();
	}
	
	private void checkForPlace() {
		// Als er in de content een place zit, set die.
	}

	public User getSender() {
		return sender;
	}
	
	public Content getContent() {
		return content;
	}
	
	public void addRetweet(Tweet retweet) {
		retweets.add(retweet);
	}
	
	public int getNumberOfRetweets() {
		return retweets.size();
	}

	public String getDate() {
		return date;
	}
	
	@Override
	public void update(Observable observable, Object data) {
		setChanged();
		notifyObservers();
	}
}
