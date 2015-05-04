package nl.jasperNiels.twitter;

import nl.jasperNiels.twitter.model.TwitterModel;
import android.app.Application;

/** Application class to make sure there is one instance of the model. */
public class TwitterApplication extends Application {
	
	private TwitterModel model = new TwitterModel();
	
	public TwitterModel getModel() {
		return model;
	}
}
