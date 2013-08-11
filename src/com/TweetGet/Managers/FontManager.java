package com.TweetGet.Managers;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import com.TweetGet.Application.TweetgetApplication;

public enum FontManager {

	INSTANCE;

	private Typeface mAppFont;
	private Typeface mGeorgiaTypeFace;

	private FontManager() {
		AssetManager assetManager = TweetgetApplication.getContext()
				.getResources().getAssets();
		mAppFont = Typeface.createFromAsset(assetManager,
				"assests/fonts/DINCondensedC.otf");
		mGeorgiaTypeFace = Typeface.createFromAsset(assetManager,
				"assets/fonts/Georgia.ttf");

	}

	public Typeface getAppFont() {
		return mAppFont;
	}

	public Typeface getGeorgiaFont() {
		return mGeorgiaTypeFace;
	}
}