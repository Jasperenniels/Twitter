package nl.jasperNiels.twitter.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Model that contains all tweets and listens to changes to them.
 * Tweets are created here aswell
 * @author enjee
 */
public class TwitterModel extends Observable implements Observer {
	
	private ArrayList<Tweet> tweets;
	
	public TwitterModel() {
		tweets = new ArrayList<>();
//		createTweet(new User("Jasper", "van Lierop", "JasperVL"), new Content("Hoi"));  //Hardcoded example;
//		createTweet(new User("Niels Jan", "van de Pol", "EnJee"), new Content("Man"));  //Hardcoded example
	}
	
	private void createTweet(User sender, Content content, String created_at) {
		Tweet newTweet = new Tweet(sender, content, created_at);
		newTweet.addObserver(this);
		tweets.add(newTweet);
	}
	
	public void addTweet(Tweet newTweet) {
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
