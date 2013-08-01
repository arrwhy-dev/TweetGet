package com.TweetGet.Tasks;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.EndPoints.ApiHeaders;
import com.TweetGet.Models.TweetModel;
import com.google.gson.Gson;

public class SearchFeedTask extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {

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
			
			//InputStream inputStream = entity.getContent();

			
			/* 
			 * TODO TODO TODO TODO 
			 * BUILD OUT THE TWEET MODEL SO YOU CAN USE
			 * GSON INSTEAD OF PARSING THOSE FRIGGIN STRINGS!!!!
			 */
		//	Gson gm = new Gson();
		//	TweetModel tweet = new Gson().fromJson(new InputStreamReader(inputStream), TweetModel.class);
					
			
			
			String result = EntityUtils.toString(entity);

			return result;
		} catch (Exception e) {
			Log.e("GetFeedTask", "Error:" + e.getMessage());
			return null;
		}
	}

	@Override
	protected void onPostExecute(String jsonText) {
		try {

			ApiEndPoints.setJsonString(jsonText);

			new TweetsParseTask().execute();
		} catch (Exception e) {
			Log.e("GetFeedTask", "Error:" + e.getMessage());
		}
	}

}
