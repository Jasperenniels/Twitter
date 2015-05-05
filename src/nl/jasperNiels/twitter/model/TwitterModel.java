package nl.jasperNiels.twitter.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class TwitterModel extends Observable implements Observer {
	
	private ArrayList<Tweet> tweets;
	
	public TwitterModel() {
		tweets = new ArrayList<>();
		createTweet(new User("Jasper", "van Lierop", "JasperVL"), new Content());  //Hardcoded example;
		createTweet(new User("Niels Jan", "van de Pol", "EnJee"), new Content());  //Hardcoded example
	}
	
	private void createTweet(User sender, Content content) {
		Tweet newTweet = new Tweet(sender, content);
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
