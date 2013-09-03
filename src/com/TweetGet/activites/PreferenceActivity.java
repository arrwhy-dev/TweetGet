package com.TweetGet.Activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;

import com.TweetGet.R;
/*
 * TODO: USE PREFERENCE FRAGMENTS FOR BETTER COMPATIBILITY!
 * 
 * 
 */

public class PreferenceActivity extends android.preference.PreferenceActivity
		implements OnSharedPreferenceChangeListener {

	@SuppressWarnings("deprecation")
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
	 * Kept for compatability reasons.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.preferenceslayout);

	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {

	}

	public static void newInstance(Context context) {
		Intent i = new Intent(context, PreferenceActivity.class);
		context.startActivity(i);

	}
	
}
