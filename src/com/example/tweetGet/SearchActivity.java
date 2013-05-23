package com.example.tweetGet;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hashtag_query);

		Button searchButton = (Button) findViewById(R.id.button1);
		final EditText searchField = (EditText) findViewById(R.id.editText1);
		final ListView tweets = (ListView) findViewById(R.id.listView1);

		searchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v)
			{

				String hashTag = searchField.getText().toString();

				parseTweet parser = new parseTweet(tweets, SearchActivity.this,
						hashTag,getApplicationContext());

				parser.execute();
			}
		});

	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == R.id.back)
		{

			finish();
			overridePendingTransition(R.anim.slide_out_right,
					R.anim.slide_in_right);

		}
		return true;
	}

	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.search_activity, menu);
		return true;
	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflate = getMenuInflater();
		inflate.inflate(R.menu.context, menu);
	}

}
