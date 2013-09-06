package com.TweetGet.Fragments;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.TweetGet.R;
import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.Managers.SharedPreferencesManager;
import com.TweetGet.Tasks.SearchFeedTask;

public class MainFeedFragment extends AbsFeedFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_main_feed, null);
		return view;
	}

	@Override
	protected void fetchDataAsynchronously() {
		new SearchFeedTask(new WeakReference<Context>(getActivity()),
				mTweetList).execute();
	}
	
	protected String getTargetUrl(SharedPreferencesManager ms, String tag) {
		String hashTag = (tag == null) ? (ms.getDefaultHashTag()) : tag;
		String count = ms.getTweetNum();
		String result_type = ms.getTweetType();

		ApiEndPoints.TWITTER_DEFAULT_SEARCH = ApiEndPoints.TWITTER_SEARCH
				+ hashTag + ApiEndPoints.RESULT_COUNT + count
				+ ApiEndPoints.RESULT_TYPE + result_type;
		return hashTag;
	}
}
