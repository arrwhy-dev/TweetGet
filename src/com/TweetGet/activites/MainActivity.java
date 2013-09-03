package com.TweetGet.Activites;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.TweetGet.R;
import com.TweetGet.Adapters.TweetListAdapter;
import com.TweetGet.Fragments.MainFeedFragment;
import com.TweetGet.Fragments.TimelineFragment;

public class MainActivity extends FragmentActivity {

	public static TweetListAdapter mTweetListAdapter;
	private String[] mDrawerOptions;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mActionBarDrawerToggle;
	private Fragment mFragment;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private static final int MAIN_FEED_FRAGMENT_POSITION = 0;
	private static final int ACCOUNT_SEARCH_FRAGMENT_POSITION = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();
		mDrawerOptions = getResources().getStringArray(
				R.array.nav_drawer_menu_options);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mDrawerOptions));

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
		mDrawerList.setHeaderDividersEnabled(true);
		selectItem(MAIN_FEED_FRAGMENT_POSITION, 3);

	}

	public boolean onOptionsItemSelected(MenuItem item) {

		if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		switch (item.getItemId()) {
		case R.id.action_settings:

			PreferenceActivity.newInstance(this);
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
		SearchView searchView = (SearchView) menu.findItem(R.id.search)
				.getActionView();

		setSearchListener(searchView);

		return true;
	}

	private void setSearchListener(SearchView searchView) {

		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextChange(String newText) {
				return true;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {

				String searchQuery = query.trim();
				FragmentManager fm = getSupportFragmentManager();
				Bundle data = new Bundle();
				data.putString("query", searchQuery);
				if (mFragment instanceof MainFeedFragment) {
					MainFeedFragment frag = new MainFeedFragment();
					frag.setArguments(data);

					FragmentTransaction ft = fm.beginTransaction();
					ft.add(R.id.content_frame, frag,"main");
					Fragment fragment = fm.findFragmentByTag("main");
					ft.remove(fragment);
					ft.addToBackStack("main");
					ft.commit();

				} else if (mFragment instanceof TimelineFragment) {
					TimelineFragment frag = new TimelineFragment();
					frag.setArguments(data);
					FragmentTransaction ft = fm.beginTransaction();
					ft.add(R.id.content_frame, frag,"account");
					Fragment fragment = fm.findFragmentByTag("account");
					ft.remove(fragment);
					ft.addToBackStack("account");
					ft.commit();
				}

				return true;
			}
		});
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {

		protected int currentPosition;

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			selectItem(position, currentPosition);

		}
	}

	private void selectItem(int position, int currentPosition) {

		String tag = "";
		switch (position) {
		case MAIN_FEED_FRAGMENT_POSITION:
			mFragment = new MainFeedFragment();
			currentPosition = MAIN_FEED_FRAGMENT_POSITION;
			tag = "main";
			break;
		case ACCOUNT_SEARCH_FRAGMENT_POSITION:
			mFragment = new TimelineFragment();
			currentPosition = ACCOUNT_SEARCH_FRAGMENT_POSITION;
			tag = "account";
			break;

		}

		if (mFragment != null) {
			getSupportFragmentManager().beginTransaction()
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
					.replace(R.id.content_frame, mFragment, tag).commit();
		}
		mDrawerList.setItemChecked(position, true);
		setTitle(mDrawerOptions[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(title);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mActionBarDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mActionBarDrawerToggle.onConfigurationChanged(newConfig);
	}

}
