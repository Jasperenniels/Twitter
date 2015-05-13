package nl.jasperNiels.twitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.jasperNiels.twitter.model.Content;
import nl.jasperNiels.twitter.model.Tweet;
import nl.jasperNiels.twitter.model.TwitterModel;
import nl.jasperNiels.twitter.model.User;
import nl.jasperNiels.twitter.view.TwitterAdapter;
import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView lvTwitter;
	private TwitterModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		model = ((TwitterApplication) getApplication()).getModel();

		lvTwitter = (ListView) findViewById(R.id.lvTwitter);
		TwitterAdapter adapter = new TwitterAdapter(this, model.getTweets());
		lvTwitter.setAdapter(adapter);
		model.addObserver(adapter);
		
		try {
			String jsonLine = readAssetIntoString("tweets.json");
			try {
				JSONObject jsonObject = new JSONObject(jsonLine);
				JSONArray jsonArray = jsonObject.getJSONArray("statuses");
				
				Log.w("Begin loop", "array length: " + jsonArray.length());
				for (int i = 0 ; i < jsonArray.length() ; i++) {
					 
					JSONObject tweetObject = (JSONObject) jsonArray.get(i); 
					createTweet(tweetObject);
					
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createTweet(JSONObject tweetObject) throws JSONException {
		// tweet shizzle
		String id_str = tweetObject.getString("id_str");
		String date = tweetObject.getString("created_at");
		String text =  tweetObject.getString("text");
		
		JSONObject userObject = tweetObject.getJSONObject("user");

		// user gebeurens
		String name = userObject.getString("name");
		String user_id = userObject.getString("id_str");
		String screen_name = userObject.getString("screen_name");
		
		Tweet newTweet = new Tweet(id_str, new User(user_id, name, screen_name), new Content(text), date);
		model.addTweet(newTweet);
		
	}

	// Reads an asset file and returns a string with the full contents.
	//
	// @param filename The filename of the file to read.
	// @return The contents of the file.
	// @throws IOException If file could not be found or not read.
	private String readAssetIntoString(String filename) throws IOException {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {
			InputStream is = getAssets().open(filename,
					AssetManager.ACCESS_BUFFER);
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}