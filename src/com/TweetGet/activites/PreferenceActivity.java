package com.TweetGet.activites;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;

import com.TweetGet.R;

public class PreferenceActivity extends android.preference.PreferenceActivity implements OnSharedPreferenceChangeListener{
	
	@SuppressWarnings("deprecation")
	/*
	 * (non-Javadoc)
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
	

}
