package com.TweetGet.Fragments;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.TweetGet.R;
import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.Managers.SharedPreferencesManager;
import com.TweetGet.Tasks.SearchFeedTask;
import com.TweetGet.Utils.AnimationUtils;
import com.TweetGet.Utils.NetworkUtils;

public class MainFeedFragment extends Fragment {

	private ListView mTweetList;
	private TextView mHeaderView;
	private FrameLayout mCover;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_main_feed, null);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		super.onCreate(savedInstanceState);
		mTweetList = (ListView) view.findViewById(R.id.tweetList);
		mCover = (FrameLayout) view.findViewById(R.id.no_wifi_cover);

		Bundle args = getArguments();
		String query;
		SharedPreferencesManager.init(getActivity());
		SharedPreferencesManager ms = SharedPreferencesManager.getInstance();

		if (args != null) {
			query = args.getString("query");
			query = getTargetUrl(ms, query);
		} else {

			query = getTargetUrl(ms);
		}

		setUpTweetList(query);

		if (NetworkUtils.wifiIsEnaled(getActivity())) {

			new SearchFeedTask(new WeakReference<Context>(getActivity()),
					mTweetList).execute();

		} else {
			mCover.setVisibility(View.VISIBLE);
			// NetworkUtils.notifyWifiState(getActivity());
		}
	}

	private String getTargetUrl(SharedPreferencesManager ms) {
		return getTargetUrl(ms, null);
	}

	private String getTargetUrl(SharedPreferencesManager ms, String tag) {
		String hashTag = (tag == null) ? (ms.getDefaultHashTag()) : tag;
		String count = ms.getTweetNum();
		String result_type = ms.getTweetType();

		ApiEndPoints.TWITTER_DEFAULT_SEARCH = ApiEndPoints.TWITTER_SEARCH
				+ hashTag + ApiEndPoints.RESULT_COUNT + count
				+ ApiEndPoints.RESULT_TYPE + result_type;
		return hashTag;
	}

	private void setUpTweetList(String tag) {

		mHeaderView = new TextView(getActivity());
		mHeaderView.setText("tweets about " + tag);
		mTweetList.addHeaderView(mHeaderView);

		AnimationUtils.setLoadingAnimation(mTweetList);
	}

	@Override
	public void onPause() {
		super.onPause();

	}

}
