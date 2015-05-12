package nl.jasperNiels.twitter;

import java.util.ArrayList;
import java.util.Scanner;

import nl.jasperNiels.twitter.model.Content;
import nl.jasperNiels.twitter.model.Tweet;
import nl.jasperNiels.twitter.model.User;

public class JSONConverter {

	private String jsonLine;

	public JSONConverter(String jsonLine) {
		this.jsonLine = jsonLine;
	}

	public ArrayList<Tweet> constructTweets() {
		ArrayList<Tweet> tweets = new ArrayList<>();
		Scanner sc = new Scanner(jsonLine);

		String id_str = "";
		String text = "";
		String name = "";
		String screen_name = "";
		String created_at = "";

		while (sc.hasNext()) {
			sc.useDelimiter("\"");
			String next = sc.next();
			if (next.equals("id_str")) {
				sc.next();
				id_str = sc.next();
			} else if (next.equals("text")) {
				sc.next();
				text = sc.next();
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
				
				Tweet newTweet = new Tweet(new User(id_str, text, name), new Content(text), created_at);
				
				tweets.add(newTweet);
				
				id_str = "";
				text = "";
				name = "";
				screen_name = "";
				created_at = "";
			}
			

		}

		return tweets;
	}
	
	public boolean tweetDone(String... strings) {
		
		for (String s : strings) {
			if (s.isEmpty()) {
				return false;
			}
		}
		
		return true;
	}

}
