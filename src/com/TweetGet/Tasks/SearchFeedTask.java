package com.TweetGet.Tasks;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.TweetGet.Activites.MainActivity;
import com.TweetGet.Adapters.TweetListAdapter;
import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.EndPoints.ApiHeaders;
import com.TweetGet.Managers.BearerTokenManager;
import com.TweetGet.Models.statusesContainer;
import com.TweetGet.Utils.TweetDatabaseUtils;
import com.google.gson.Gson;

public class SearchFeedTask extends AsyncTask<String, Void, statusesContainer> {

	private ListView mListView;
	private WeakReference<Context> mContext;

	public SearchFeedTask(WeakReference<Context> context, ListView listview) {
		mContext = context;
		mListView = listview;
	}

	@Override
	protected statusesContainer doInBackground(String... params) {

		try {

			String authToken = BearerTokenManager
					.getBearerTokenSynchronously(mContext.get());

			DefaultHttpClient httpclient = new DefaultHttpClient(
					new BasicHttpParams());
			HttpGet httpget = new HttpGet(ApiEndPoints.TWITTER_DEFAULT_SEARCH);

			httpget.setHeader(ApiHeaders.AUTHORIZATION, ApiHeaders.BEARER
					+ authToken);

			httpget.setHeader(ApiHeaders.CONTENT_TYPE,
					ApiHeaders.APPLICATION_JSON);

			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			InputStream inputStream = entity.getContent();

			statusesContainer statuses = new Gson()
					.fromJson(new InputStreamReader(inputStream),
							statusesContainer.class);

			//TweetDatabaseUtils.insertTweetsToDatabase(statuses, mContext.get());

			return statuses;
		} catch (Exception e) {
			Log.e("GetFeedTask", "Error:" + e.getMessage());
			return null;
		}
	}

	@Override
	protected void onPostExecute(statusesContainer statuses) {
		try {
			TweetListAdapter adapter = new TweetListAdapter(mContext.get(),
					statuses.getStatuses());
			mListView.setAdapter(adapter);
			MainActivity.mTweetListAdapter = adapter;
		} catch (Exception e) {
			Log.e("GetFeedTask", "Error:" + e.getMessage());
		}
	}

}
