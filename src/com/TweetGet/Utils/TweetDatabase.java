package com.TweetGet.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * 
 * Add suppress warning until databases are properly implemented
 */

@SuppressWarnings("unused")
public class TweetDatabase extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "tweetDatabase.db";
	public static final String DATABASE_TABLE = "TweetTable";
	private static final int DATABASE_VERSION = 1;

	public static class Columns {
		public static final String KEY_ID = "_id";
		public static final String USER_NAME = "USERNAME";
		public static final String TWEET_TEXT = "TWEET_TEXT";
		public static final String TWEET_DATE_TIME = "DATE_TIME";
	}

	private static final String DATABASE_CREATE = "create table "
			+ DATABASE_TABLE + " (" + Columns.KEY_ID
			+ " integer primary key autoincrement, " + Columns.USER_NAME
			+ " text, " + Columns.TWEET_TEXT + " text, "
			+ Columns.TWEET_DATE_TIME + " text);";

	public TweetDatabase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
