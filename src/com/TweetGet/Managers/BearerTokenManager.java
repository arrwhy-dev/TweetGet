package com.TweetGet.Managers;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import android.content.Context;
import android.util.Log;

import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.EndPoints.ApiHeaders;
import com.TweetGet.Models.BearerTokenContainer;
import com.TweetGet.Tasks.BearerTokenTask;
import com.google.gson.Gson;

public class BearerTokenManager {

	private static String mBearerToken;

	public static String getBearerTokenAsynch(Context context) {

		if (mBearerToken == null) {
			new BearerTokenTask(new BearerTokenListener() {

				@Override
				public void onSuccess(String bearerToken) {
					mBearerToken = bearerToken;

				}
			}).execute();
		}

		return mBearerToken;

	}

	/*
	 * Note this should not be called from the UI thread or else an exception
	 * will be thrown.
	 * This method exists solely to enable a synchronous call from an async task
	 */
	public static String getBearerTokenSynchronously(Context context) {
		if (mBearerToken == null) {

			try {
				DefaultHttpClient httpclient = new DefaultHttpClient(
						new BasicHttpParams());

				HttpPost httppost = new HttpPost(
						ApiEndPoints.TWITTER_AUTH_TOKEN);
				httppost.setHeader(ApiHeaders.AUTHORIZATION,
						ApiEndPoints.getApiAuthKey());

				httppost.setHeader(ApiHeaders.CONTENT_TYPE,
						ApiHeaders.UTF_ENCODING);
				httppost.setEntity(new StringEntity(ApiHeaders.GRANT_TYPE));

				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();

				InputStream inputStream = entity.getContent();

				BearerTokenContainer bearerTokenContainer = new Gson()
						.fromJson(new InputStreamReader(inputStream),
								BearerTokenContainer.class);

				mBearerToken = bearerTokenContainer.getAccessToken();
			} catch (Exception e) {
				Log.e("GetBearerTokenTask", "Error:" + e.getMessage());
				return null;
			}
		}

		return mBearerToken;
	}

	public static boolean hasBearerToken() {
		return (mBearerToken != null);
	}

	public interface BearerTokenListener {
		public void onSuccess(String bearerToken);
	}

}
