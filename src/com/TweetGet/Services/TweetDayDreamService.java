package com.TweetGet.Services;

import android.annotation.SuppressLint;
import android.service.dreams.DreamService;

import com.TweetGet.R;
/*
 * 
 * TODO: SO FAR DAYDREAM HAVE JUST BEEN AN EXPERIEMENT
 * 		NOT SURE WHERE THIS IS HEADING, COOL POSSIBILITIES
 * 
 */

@SuppressLint("NewApi")
public class TweetDayDreamService extends DreamService {

	@Override
	public void onDreamingStarted() {
		// TODO Auto-generated method stub
		super.onDreamingStarted();
		setContentView(R.layout.dream_layout);
	}
}