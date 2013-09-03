package com.TweetGet.Fragments;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.TweetGet.R;
import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.Managers.SharedPreferencesManager;
import com.TweetGet.Tasks.TimelineTask;

public class TimelineFragment extends BaseFeedFragment {

	private RelativeLayout mBanner;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_timeline, null);
		 mBanner = (RelativeLayout) view
				.findViewById(R.id.timeline_header);
		return view;
	}

	@Override
	protected void fetchDataAsynchronously() {

		new TimelineTask(new WeakReference<Context>(getActivity()), mTweetList,
				mBanner).execute();
	}

	@Override
	protected String getTargetUrl(SharedPreferencesManager ms, String tag) {

		String userTag = (tag == null) ? "sample" : tag;

		ApiEndPoints.TWITTER_USER_TIMElINE_DEFAULT_SEARCH = ApiEndPoints.TWITTER_USER_TIMELINE
				+ "?" + "screen_name" + "=" + userTag;

		ApiEndPoints.TWITTER_DEFAULT_USER_DETAILS = ApiEndPoints.TWITTER_USER_DETAILS
				+ "screen_name" + "=" + userTag;

		return userTag;
	}

}
