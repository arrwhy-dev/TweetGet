package com.example.tweetGet;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// final ListView tweetList = (ListView) findViewById(R.id.listView);
		// TextView tv = new TextView(this);
		// tv.setText("tweets about Android");
		// tweetList.addHeaderView(tv);
		// final Handler tweetHandler = new Handler();
		//
		// Runnable task = new Runnable() {
		//
		// public void run()
		// {
		//
		// animateListView(tweetList);
		// parseTweet parser = new parseTweet(tweetList,
		// MainActivity.this, "Android",getApplicationContext());
		// parser.execute();
		// //tweetHandler.postDelayed(this, 10000);
		//
		// }
		// };
		//
		//
		// if (wifiEnabled())
		// {
		// tweetHandler.post(task);
		// }else
		// {
		// Toast.makeText(MainActivity.this,
		// "No network connection going to load previous tweets",Toast.LENGTH_LONG).show();
		// loadCache(tweetList);
		//
		// }

	}

	public void animateListView(ListView lv) {

		AnimationSet set = new AnimationSet(true);
		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(200);
		set.addAnimation(animation);

		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(200);
		set.addAnimation(animation);

		LayoutAnimationController controller = new LayoutAnimationController(
				set, 1.0f);

		lv.setLayoutAnimation(controller);

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.Search) {
			Intent i = new Intent(this, SearchActivity.class);

			startActivity(i);

			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_left);

		}

		if (item.getItemId() == R.id.settings) {
			Intent i = new Intent(this, PreferenceActivity.class);

			startActivity(i);
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

	public boolean wifiEnabled() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			return false;
		} else
			return true;
	}

	public void loadCache(ListView lv) {

		DbHelper dbhelper = new DbHelper(this);
		SQLiteDatabase db = dbhelper.getWritableDatabase();

		String[] columns = { DbHelper.USERNAME, DbHelper.TWEET, DbHelper.DATE,
				DbHelper.PICTURE };
		Cursor cursor = db.query(DbHelper.TABLE_NAME, columns, null, null,
				null, null, null);

		cursor.moveToFirst();
		ArrayList<TweetData> items = new ArrayList<TweetData>();

		while (cursor.moveToNext()) {
			String user = cursor.getString(cursor
					.getColumnIndex(DbHelper.USERNAME));
			String tweet = cursor.getString(cursor
					.getColumnIndex(DbHelper.TWEET));

			String date = cursor
					.getString(cursor.getColumnIndex(DbHelper.DATE));

			TweetData current = new TweetData(tweet, user, date);
			byte[] bb = cursor.getBlob(cursor.getColumnIndex(DbHelper.PICTURE));

			current.setPic(BitmapFactory.decodeByteArray(bb, 0, bb.length));

			items.add(current);
		}

		CustomListViewAdapter lva = new CustomListViewAdapter(this,
				MainActivity.this, items);
		lv.setAdapter(lva);

	}

}
