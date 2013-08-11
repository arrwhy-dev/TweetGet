package com.TweetGet.Tasks;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.EndPoints.ApiHeaders;
import com.TweetGet.Managers.BearerTokenManager.BearerTokenListener;

public class BearerTokenTask extends AsyncTask<Void, Void, String> {

	private BearerTokenListener mListener;

	public BearerTokenTask(BearerTokenListener listener) {
		mListener = listener;
	}

	@Override
	protected String doInBackground(Void... params) {

		try {
			DefaultHttpClient httpclient = new DefaultHttpClient(
					new BasicHttpParams());

			HttpPost httppost = new HttpPost(ApiEndPoints.TWITTER_AUTH_TOKEN);
			httppost.setHeader(ApiHeaders.AUTHORIZATION,
					ApiEndPoints.getApiAuthKey());

			httppost.setHeader(ApiHeaders.CONTENT_TYPE, ApiHeaders.UTF_ENCODING);
			httppost.setEntity(new StringEntity(ApiHeaders.GRANT_TYPE));

			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			String result = EntityUtils.toString(entity);

			return result;
		} catch (Exception e) {
			Log.e("GetBearerTokenTask", "Error:" + e.getMessage());
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
