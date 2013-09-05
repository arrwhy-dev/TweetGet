package com.TweetGet.Tasks;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.TweetGet.Managers.BearerTokenManager.BearerTokenListener;
import com.TweetGet.Network.ApiPosts;

public class BearerTokenTask extends AsyncTask<Void, Void, String> {

	private BearerTokenListener mListener;

	public BearerTokenTask(BearerTokenListener listener) {
		mListener = listener;
	}

	@Override
	protected String doInBackground(Void... params) {

		try {

			HttpResponse response = ApiPosts.postToBearerToken();
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);

			return result;
		} catch (IOException e) {
			Log.e("GetBearerTokenTask", "IO-Exception:" + e.getMessage());
			return null;
		}
	}

	@Override
	protected void onPostExecute(String jsonText) {
		try {
			JSONObject root = new JSONObject(jsonText);
			String bearer_token = root.getString("access_token");
			mListener.onSuccess(bearer_token);
		} catch (Exception e) {
			Log.e("GetBearerTokenTask", "Error:" + e.getMessage());
		}

	}

	public interface BearerTokenTaskListener {

		public void onSuccess();

		public void onFailure();
	}

}
