package com.TweetGet.Application;

import android.app.Application;
import android.content.Context;

public class TweetgetApplication extends Application {

	private static Context sContext;

	@Override
	public void onCreate() {
		super.onCreate();

		sContext = getApplicationContext();
	}

	public static final Context getContext() {
		return sContext;
	}

}