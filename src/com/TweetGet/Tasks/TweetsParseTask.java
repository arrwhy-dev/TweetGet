package com.TweetGet.Tasks;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.TweetGet.Adapters.TweetListAdapter;
import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.Models.TweetModel;
import com.TweetGet.activites.MainActivity;


public class TweetsParseTask extends AsyncTask<Void, Void, ArrayList<TweetModel>> {

	private ListView tweetList;
	private Context mContext;
	private ArrayList<TweetModel> items;

	public TweetsParseTask() {

		tweetList = MainActivity.tweetList;
		mContext = MainActivity.mContext;

	}
	
	public TweetsParseTask(ListView lv , Context context)
	{
		tweetList=lv;
		mContext=context;
	}

	protected void onPostExecute(ArrayList<TweetModel> items) {
		this.items = items;
		TweetListAdapter adapter = new TweetListAdapter(mContext, items);
		tweetList.setAdapter(adapter);

	}

	@Override
	protected ArrayList<TweetModel> doInBackground(Void... params) {
		ArrayList<TweetModel> items = new ArrayList<TweetModel>();

		try {

			JSONArray sessions = getJsonArray(ApiEndPoints.getJSONString());

			// only get text, get images later via async.
			for (int i = 0; i < sessions.length(); ++i) {

				JSONObject temp = sessions.getJSONObject(i);
				JSONObject user = temp.getJSONObject("user");

				String userId = user.getString("screen_name");
				String dateAndTime = temp.getString("created_at").split("\\+")[0];
				String tweet = temp.getString("text");
				String profilepic = user.getString("profile_image_url");

				TweetModel currentTweet = new TweetModel(profilepic, tweet,
						userId, dateAndTime);

				items.add(currentTweet);

			}

		} catch (JSONException e) {

			e.printStackTrace();
		}

		return items;
	}

	public JSONArray getJsonArray(String s) {
		try {

			JSONObject root = new JSONObject(s);

			JSONArray sessions = root.getJSONArray("statuses");
		
			return sessions;
		} catch (Exception e) {
			Log.d("exception", "fails to retrive JSON");
		}
		return null;
	}

	public ArrayList<TweetModel> getItems() {
		return items;
	}

}