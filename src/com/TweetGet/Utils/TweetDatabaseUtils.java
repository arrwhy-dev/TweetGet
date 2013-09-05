package com.TweetGet.Utils;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.TweetGet.Models.TweetStatus;
import com.TweetGet.Models.statusesContainer;

public class TweetDatabaseUtils {

	public static void insertTweetsToDatabase(final statusesContainer container,
			final Context context) {

		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {

				TweetDatabase tweetDb = new TweetDatabase(context,
						"TweetTable.db", null, 1);

				List<TweetStatus> statuses = container.getStatuses();
				SQLiteDatabase db = tweetDb.getWritableDatabase();

				for (TweetStatus status : statuses) {
					ContentValues values = new ContentValues();

					values.put(TweetDatabase.Columns.USER_NAME, status
							.getUser().getScreenName());
					values.put(TweetDatabase.Columns.TWEET_TEXT,
							status.getTweetText());
					values.put(TweetDatabase.Columns.TWEET_DATE_TIME,
							status.getDateTime());
					
					Log.d("content-Values",values.toString());
					db.insert(TweetDatabase.DATABASE_TABLE, null, values);
				}
				return null;
			}
		}.execute();

	}
}
