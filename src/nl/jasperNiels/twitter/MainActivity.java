package nl.jasperNiels.twitter;

import nl.jasperNiels.twitter.model.TwitterModel;
import nl.jasperNiels.twitter.view.TwitterAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView lvTwitter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TwitterModel model = ((TwitterApplication) getApplication()).getModel();
		
		lvTwitter = (ListView) findViewById(R.id.lvTwitter);
		TwitterAdapter adapter = new TwitterAdapter(this, model.getTweets());
		lvTwitter.setAdapter(adapter);
		model.addObserver(adapter);
	}
}


/**  
 
// Reads an asset file and returns a string with the full contents.
//
// @param filename  The filename of the file to read.
// @return          The contents of the file.
// @throws IOException  If file could not be found or not read.


private String readAssetIntoString(String filename) throws IOException {
	BufferedReader br = null;
	StringBuilder sb = new StringBuilder();

	String line;
	try {
		InputStream is = getAssets().open(filename, AssetManager.ACCESS_BUFFER);
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

*/