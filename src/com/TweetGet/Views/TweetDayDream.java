package com.TweetGet.Views;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.TweetGet.Utils.TweetDatabase;

public class TweetDayDream extends FrameLayout {

	public TweetDayDream(Context context, AttributeSet attrs) {
		super(context, attrs);

		TextView text = new TextView(context);
		TextView username = new TextView(context);
		TextView dateTime = new TextView(context);

		TweetDatabase tweetDb = new TweetDatabase(context, "TweetTable", null,
				1);

		SQLiteDatabase db = tweetDb.getWritableDatabase();

		String[] cols = { TweetDatabase.Columns.KEY_ID,
				TweetDatabase.Columns.USER_NAME,
				TweetDatabase.Columns.TWEET_TEXT,
				TweetDatabase.Columns.TWEET_DATE_TIME };

		String where = TweetDatabase.Columns.KEY_ID + "=" + 1;

		Cursor cusor = db.query(TweetDatabase.DATABASE_TABLE, cols,where, null, null,
				null, null, null);

		String textText = cusor.getString(cusor
				.getColumnIndex(TweetDatabase.Columns.TWEET_TEXT));
		String UsernameText = cusor.getString(cusor
				.getColumnIndex(TweetDatabase.Columns.USER_NAME));
		String dateTimeText = cusor.getString(cusor
				.getColumnIndex(TweetDatabase.Columns.TWEET_DATE_TIME));

		text.setText(textText);
		username.setText(UsernameText);
		dateTime.setText(dateTimeText);

		Typeface font = Typeface.createFromAsset(context.getAssets(),
				"font/DINCondensedC.otf");

		text.setTypeface(font);
		username.setTypeface(font);
		dateTime.setTypeface(font);

		LinearLayout ll = new LinearLayout(context);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.addView(text);
		ll.addView(username);
		ll.addView(dateTime);

		this.addView(ll);

	}

	Runnable mRunnable = new Runnable() {

		@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
		@Override
		public void run() {

			final View parent = (View) getParent();

			if (parent == null)
				return;

			final float width = getMeasuredWidth();
			final float height = getMeasuredHeight();
			final float parentw = parent.getMeasuredWidth();
			final float parenth = parent.getMeasuredHeight();

			animate().x((float) Math.random() * (parentw - width))
					.y((float) Math.random() * (parenth - height))
					.setDuration(500);

			postDelayed(this, 1000);
		}
	};

	protected void onAttachedToWindow() {

		getHandler().post(mRunnable);

	}

}
