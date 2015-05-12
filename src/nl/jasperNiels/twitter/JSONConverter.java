package nl.jasperNiels.twitter;

import java.util.ArrayList;
import java.util.Scanner;

import android.util.Log;
import nl.jasperNiels.twitter.model.Content;
import nl.jasperNiels.twitter.model.Tweet;
import nl.jasperNiels.twitter.model.User;

public class JSONConverter {

	/* The line of JSON which is passed to this converter */
	private String jsonLine;
	
	/* The attributes of a tweet */
	private String id_str;
	private String text;
	private String name;
	private String screen_name;
	private String created_at;

	public JSONConverter(String jsonLine) {
		this.jsonLine = jsonLine;
	}

	public ArrayList<Tweet> constructTweets() {
		ArrayList<Tweet> tweets = new ArrayList<>();
		Scanner sc = new Scanner(jsonLine);

		clearStrings();

		while (sc.hasNext()) {
			sc.useDelimiter("\"");
			String next = sc.next();
			if (next.equals("id_str")) {
				sc.next();
				id_str = sc.next();
			} else if (next.equals("text")) {
				sc.next();
				text = sc.next().replaceAll("\\\\n", "\n");  //replace all escaped 'new-lines' with working 'new-lines'
				System.out.println(text);
			} else if (next.equals("name")) {
				sc.next();
				name = sc.next();
			} else if (next.equals("screen_name")) {
				sc.next();
				screen_name = sc.next();
			} else if (next.equals("created_at")) {
				sc.next();
				created_at = sc.next();
			}
			
			if (tweetDone(id_str, text, name, screen_name, created_at)) {
				
				Tweet newTweet = new Tweet(new User(id_str, name, screen_name), new Content(text), created_at);
				tweets.add(newTweet);
				
				clearStrings();
			}
		}
		sc.close();
		return tweets;
	}

	/**
	 * Clear all strings which are the attributes of a tweet
	 */
	private void clearStrings() {
		id_str = "";
		text = "";
		name = "";
		screen_name = "";
		created_at = "";
	}

	/**
	 * Check for empty values inside the twitter attributes
	 * @param strings	The array of strings which are the twitter attributes
	 * @return false for a empty attribute, else true (done)
	 */
	public boolean tweetDone(String... strings) {
		
		for (String s : strings) {
			if (s.isEmpty()) {
				return false;
			}
		}
		return true;
	}

}
