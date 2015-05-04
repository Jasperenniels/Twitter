package nl.jasperNiels.twitter.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class TwitterModel extends Observable implements Observer {
	
	private ArrayList<Tweet> tweets;
	
	public TwitterModel() {
		tweets = new ArrayList<>();
		createTweet(new Sender("Jasper", "van Lierop"));  //Hardcoded example;
		createTweet(new Sender("Niels Jan", "van de Pol"));  //Hardcoded example
	}
	
	private void createTweet(Sender sender) {
		Tweet newTweet = new Tweet(sender);
		newTweet.addObserver(this);
		tweets.add(newTweet);
	}

	public ArrayList<Tweet> getTweets() {
		return tweets;
	}

	@Override
	public void update(Observable observable, Object data) {
		setChanged();
		notifyObservers();
	}
}
