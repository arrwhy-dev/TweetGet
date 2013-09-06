package com.TweetGet.Tasks;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.TweetGet.R;
import com.TweetGet.Adapters.TweetListAdapter;
import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.Managers.BearerTokenManager;
import com.TweetGet.Models.TweetStatus;
import com.TweetGet.Models.User;
import com.TweetGet.Network.ApiPosts;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TimelineTask extends
		AsyncTask<List<TweetStatus>, List<TweetStatus>, List<TweetStatus>> {

	private ListView mListView;
	private WeakReference<Context> mContext;
	private RelativeLayout profileBanner;

	public TimelineTask(WeakReference<Context> context, ListView listview,
			RelativeLayout rl) {
		mContext = context;
		mListView = listview;
		profileBanner = rl;
	}

	@Override
	protected List<TweetStatus> doInBackground(List<TweetStatus>... params) {
		try {

			String authToken = BearerTokenManager
					.getBearerTokenSynchronously(mContext.get());
			
			HttpResponse response = ApiPosts.postToSearch(
					ApiEndPoints.TWITTER_USER_TIMElINE_DEFAULT_SEARCH,
					authToken);

			HttpEntity entity = response.getEntity();

			String json = EntityUtils.toString(entity);

			Type listType = new TypeToken<List<TweetStatus>>() {
			}.getType();
			List<TweetStatus> tweets = (List<TweetStatus>) new Gson().fromJson(
					json, listType);

			return tweets;
		} catch (IOException e) {
			Log.e("TimeLineTask", "IO-Exception" + e.getMessage());
			return null;
		}
	}

	@Override
	protected void onPostExecute(List<TweetStatus> result) {

		try {
			TweetListAdapter adapter = new TweetListAdapter(mContext.get(),
					result);
			mListView.setAdapter(adapter);

			User user = result.get(0).getUser();
			String name = user.getName();
			String description = user.getDescription();

			((TextView) profileBanner.findViewById(R.id.timeline_user_name))
					.setText(name);
			((TextView) profileBanner
					.findViewById(R.id.timeline_user_description))
					.setText(description);
		} catch (Exception e) {
			Log.e("TimeLineTask-PostExecute", "Error:" + e.getMessage());
		}
	}

}
