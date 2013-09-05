package com.TweetGet.Managers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import android.content.Context;
import android.util.Log;

import com.TweetGet.Models.BearerTokenContainer;
import com.TweetGet.Network.ApiPosts;
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
	 * will be thrown. This method exists solely to enable a synchronous call
	 * from an async task
	 */
	public static String getBearerTokenSynchronously(Context context) {
		if (mBearerToken == null) {

			try {

				HttpResponse response = ApiPosts.postToBearerToken();
				HttpEntity entity = response.getEntity();

				InputStream inputStream = entity.getContent();

				BearerTokenContainer bearerTokenContainer = new Gson()
						.fromJson(new InputStreamReader(inputStream),
								BearerTokenContainer.class);

				mBearerToken = bearerTokenContainer.getAccessToken();
			} catch (IOException e) {
				Log.e("getBearerTokenSynchronously", "IO-Exception:" + e.getMessage());
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
