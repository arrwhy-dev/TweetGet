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
import android.widget.RelativeLayout;

import com.TweetGet.R;
import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.Tasks.ProfileBannerTask;
import com.TweetGet.Tasks.TimelineTask;
import com.TweetGet.Utils.AnimationUtils;
import com.TweetGet.Utils.NetworkUtils;

/*
 * Should be refactored so the timelinefragment
 * and the mainfeedfragment extend the same abstract
 * this was a quick and dirty fix.
 */
public class TimelineFragment extends Fragment {

	ListView mTweetList;
	private FrameLayout mCover;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_timeline, null);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mTweetList = (ListView) view.findViewById(R.id.tweetList);
		mCover = (FrameLayout) view.findViewById(R.id.no_wifi_cover);
		RelativeLayout banner = (RelativeLayout)view.findViewById(R.id.timeline_header);
		
		Bundle args = getArguments();
		String query;

		if (args != null) {
			query = args.getString("query");
			SetUpTargetUrl(query);
		} else {

			SetUpTargetUrl(null);
		}

		setUpTweetList();

		if (NetworkUtils.wifiIsEnaled(getActivity())) {

			new TimelineTask(new WeakReference<Context>(getActivity()),
					mTweetList,banner).execute();
			
			/*
			 * TURNS OUT APP-ONLY AUTHENICATION WILL NOT ALLOW
			 * ACCESS TO THE PROFILE BANNER API
			 * 
			 * WILL NEED TO REVISIT THIS LATER.
			 */
//			
//			new ProfileBannerTask(new WeakReference<Context>(getActivity()),
//					mTweetList,banner).execute();

		} else {
			mCover.setVisibility(View.VISIBLE);
			// NetworkUtils.notifyWifiState(getActivity());
		}

	}

	private void SetUpTargetUrl(String tag) {

		String userTag = (tag == null) ? "sample" : tag;

		ApiEndPoints.TWITTER_USER_TIMElINE_DEFAULT_SEARCH = ApiEndPoints.TWITTER_USER_TIMELINE
				+ "?" + "screen_name" + "=" + userTag;
		
		ApiEndPoints.TWITTER_DEFAULT_USER_DETAILS = ApiEndPoints.TWITTER_USER_DETAILS  + "screen_name" + "=" + userTag;

	}

	private void setUpTweetList() {
		AnimationUtils.setLoadingAnimation(mTweetList);
	}

}
