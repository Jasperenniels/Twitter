package nl.jasperNiels.twitter.model;

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

	private Sender sender;

	public Tweet(Sender sender) {
		this.sender = sender;
		sender.addObserver(this);
	}
	
	public Sender getSender() {
		return sender;
	}
	
	
	@Override
	public void update(Observable observable, Object data) {
		setChanged();
		notifyObservers();
	}
}
