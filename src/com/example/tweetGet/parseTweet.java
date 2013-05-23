package com.example.tweetGet;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

public class parseTweet extends AsyncTask<Void, Void, ArrayList<TweetData>>
{

	private String tweeturl = "http://search.twitter.com/search.json?q=%23";
	private ListView tweetList;
	private Activity context;
	private Context c;
	private ArrayList<TweetData> items;
	
	public parseTweet(ListView lv, Activity context, String hashtag,Context c)
	{
		tweetList = lv;
		this.context = context;
		tweeturl = tweeturl + hashtag;
		this.c=c;
	}

	protected void onPostExecute(ArrayList<TweetData> items)
	{
		this.items=items;
		CustomListViewAdapter adapter = new CustomListViewAdapter (context,c,
				items);
		tweetList.setAdapter(adapter);
		
	
	}

	@Override
	protected ArrayList<TweetData> doInBackground(Void... params)
	{
		ArrayList<TweetData> items = new ArrayList<TweetData>();

		try
		{

			JSONArray sessions = getJsonArray(tweeturl);

			// only get text, get images later via async.
			for (int i = 0; i < sessions.length(); ++i)
			{

				JSONObject temp = sessions.getJSONObject(i);

				String userId = temp.getString("from_user");
				String dateAndTime = temp.getString("created_at").split("\\+")[0];
				String tweet = temp.getString("text");
				String profilepic = temp.getString("profile_image_url");

				TweetData currentTweet = new TweetData(profilepic, tweet,
						userId, dateAndTime);

				items.add(currentTweet);

			}

		} catch (JSONException e)
		{

			e.printStackTrace();
		}
		
		return items;
	}

	public JSONArray getJsonArray(String url)
	{
		try
		{
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(tweeturl);
			HttpResponse response = client.execute(get);
			HttpEntity twitterEntity = response.getEntity();
			String result = EntityUtils.toString(twitterEntity);

			JSONObject root = new JSONObject(result);

			JSONArray sessions = root.getJSONArray("results");
			return sessions;
		} catch (Exception e)
		{
			Log.d("exception", "fails to retrive JSON");
		}
		return null;
	}
	
	
	public ArrayList<TweetData> getItems()
	{
		return items;
	}

}
