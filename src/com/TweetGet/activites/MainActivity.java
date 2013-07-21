package com.TweetGet.activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.TweetGet.R;
import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.Managers.SharedPreferencesManager;
import com.TweetGet.Tasks.BearerTokenTask;
import com.TweetGet.Utils.AnimationUtils;
import com.TweetGet.Utils.NetworkUtils;

public class MainActivity extends Activity {

	public static ListView tweetList;
	public static Context mContext;
	public static Activity mActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = getApplicationContext();
		mActivity = this;

		
		SharedPreferencesManager.init(this);
		SharedPreferencesManager  ms = SharedPreferencesManager.getInstance();
		

		String tag = getTargetUrl(ms);

		setUpTweetList(tag);
		
		if (NetworkUtils.wifiIsEnaled(this)) {
			new BearerTokenTask().execute();
		} else {
			NetworkUtils.notifyWifiState(this);
		}
	}

	private String getTargetUrl(SharedPreferencesManager ms) {
		String tag = ms.getDefaultHashTag();
		String count = ms.getTweetNum();
		String result_type = ms.getTweetType();

		
		ApiEndPoints.TWITTER_DEFAULT_SEARCH = ApiEndPoints.TWITTER_SEARCH + tag
				+ ApiEndPoints.RESULT_COUNT + count + ApiEndPoints.RESULT_TYPE
				+ result_type;
		return tag;
	}

	private void setUpTweetList(String tag) {
		tweetList = (ListView) findViewById(R.id.listView);
		TextView tv = new TextView(this);
		tv.setText("tweets about " + tag);
		tweetList.addHeaderView(tv);

		AnimationUtils.setLoadingAnimation(tweetList);
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.Search:

			startActivity(new Intent(this, SearchActivity.class));

			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_left);

			break;

		case R.id.action_settings:

			startActivity(new Intent(this, PreferenceActivity.class));
			break;

		}
		return true;
	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflate = getMenuInflater();
		inflate.inflate(R.menu.context, menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
