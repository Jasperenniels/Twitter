package nl.jasperNiels.twitter.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import nl.jasperNiels.twitter.R;
import nl.jasperNiels.twitter.model.Tweet;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TwitterAdapter extends ArrayAdapter implements Observer {

	private ViewHolder holder;
	private Tweet selectedTweet;

	static class ViewHolder {
		TextView tvName;
		TextView tvScreenName;
		TextView tvContent;
	}

	public TwitterAdapter(Context context, ArrayList<Tweet> objects) {
		super(context, 0, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.view_tweet, parent, false);

			holder = new ViewHolder();
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvScreenName = (TextView) convertView.findViewById(R.id.tvScreenName);
			holder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		selectedTweet = (Tweet) getItem(position);
		String day = selectedTweet.getDate().substring(8,10);
		String month = selectedTweet.getDate().substring(4,7);
		String year = selectedTweet.getDate().substring(27,30);
		holder.tvName.setText(selectedTweet.getSender().getName());
		holder.tvScreenName.setText(" @" + selectedTweet.getSender().getScreenName() + " - " + day + "/" + month + "/" + year);
		holder.tvContent.setText(selectedTweet.getContent().getText());	
		
		return convertView;
	}

	@Override
	public void update(Observable observable, Object data) {
		notifyDataSetChanged();
	}

}
