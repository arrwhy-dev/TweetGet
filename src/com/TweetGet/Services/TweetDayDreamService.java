package com.TweetGet.Services;

import android.annotation.SuppressLint;
import android.service.dreams.DreamService;

import com.TweetGet.R;

@SuppressLint("NewApi")
public class TweetDayDreamService extends DreamService {

@Override
public void onDreamingStarted() {
	// TODO Auto-generated method stub
	super.onDreamingStarted();
	setContentView(R.layout.dream_layout);
}
}