package com.TweetGet.Tasks;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.TweetGet.Adapters.TweetListAdapter;
import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.EndPoints.ApiHeaders;
import com.TweetGet.Fragments.MainFeedFragment;
import com.TweetGet.Models.statusesContainer;
import com.TweetGet.activites.MainActivity;
import com.google.gson.Gson;

public class SearchFeedTask extends AsyncTask<String, Void, statusesContainer> {
	
	private Context mContext;
	private ListView mListView;
	
	public SearchFeedTask(){
		mContext = MainActivity.mContext;
		mListView = MainFeedFragment.mTweetList;
	}

	@Override
	protected statusesContainer doInBackground(String... params) {

		try {
			DefaultHttpClient httpclient = new DefaultHttpClient(
					new BasicHttpParams());
			HttpGet httpget = new HttpGet(ApiEndPoints.TWITTER_DEFAULT_SEARCH);

			httpget.setHeader(ApiHeaders.AUTHORIZATION, ApiHeaders.BEARER
					 + ApiEndPoints.getBearerToken());

			httpget.setHeader(ApiHeaders.CONTENT_TYPE,
					ApiHeaders.APPLICATION_JSON);

			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			
			InputStream inputStream = entity.getContent();

			statusesContainer statuses = new Gson().fromJson(new InputStreamReader(inputStream), statusesContainer.class);

			return statuses;
		} catch (Exception e) {
			Log.e("GetFeedTask", "Error:" + e.getMessage());
			return null;
		}
	}

	@Override
	protected void onPostExecute(statusesContainer statuses) {
		try {

		//	ApiEndPoints.setJsonString(jsonText);

			//new TweetsParseTask().execute();
			
			TweetListAdapter adapter = new TweetListAdapter(mContext, statuses);
			mListView.setAdapter(adapter);
		} catch (Exception e) {
			Log.e("GetFeedTask", "Error:" + e.getMessage());
		}
	}

}
