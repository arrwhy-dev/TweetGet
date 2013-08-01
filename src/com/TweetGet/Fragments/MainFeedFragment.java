package com.TweetGet.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.TweetGet.R;
import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.Managers.SharedPreferencesManager;
import com.TweetGet.Tasks.BearerTokenTask;
import com.TweetGet.Tasks.SearchFeedTask;
import com.TweetGet.Utils.AnimationUtils;
import com.TweetGet.Utils.NetworkUtils;
import com.TweetGet.activites.MainActivity;

public class MainFeedFragment extends Fragment {

	public static ListView mTweetList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_main_feed, null);

		// TODO FIND A BETTER WAY TO REFERENCE THESE OBJECTS AS THE CURRENT
		// IMPLEMENTATION IS DANGEROUS.
		mTweetList = (ListView) view.findViewById(R.id.tweetList);

		MainActivity.mContext = getActivity();
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		super.onCreate(savedInstanceState);

		SharedPreferencesManager.init(getActivity());
		SharedPreferencesManager ms = SharedPreferencesManager.getInstance();

		String tag = getTargetUrl(ms);

		setUpTweetList(tag);

		if (NetworkUtils.wifiIsEnaled(getActivity())) {

			if (ApiEndPoints.getBearerToken() == null) {
				new BearerTokenTask().execute();
			} else {
				new SearchFeedTask().execute();
			}
		} else {
			NetworkUtils.notifyWifiState(getActivity());
		}
	}

	private String getTargetUrl(SharedPreferencesManager ms) {
		String tag = ms.getDefaultHashTag();
		String count = ms.getTweetNum();
		String result_type = ms.getTweetType();

		ApiEndPoints.TWITTER_DEFAULT_SEARCH = ApiEndPoints.TWITTER_SEARCH + tag
				+ ApiEndPoints.RESULT_COUNT + count + ApiEndPoints.RESULT_TYPE
				+ result_type;
		return tag;
	}

	private void setUpTweetList(String tag) {

		TextView tv = new TextView(getActivity());
		tv.setText("tweets about " + tag);
		mTweetList.addHeaderView(tv);

		AnimationUtils.setLoadingAnimation(mTweetList);
	}

}
