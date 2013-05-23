package com.example.tweetGet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper 
{
	
	
	public static final String DATABASE_NAME= "data";
	public static final String TABLE_NAME = "tweet_table";
	public static final String TWEET_ID ="_id";
	public static final String USERNAME = "user_name";
	public static final String TWEET = "tweet";
	public static final String DATE ="date_time";
	public static final String PICTURE = "picture";
	
	private final String createDb = "create table if not exists "+ TABLE_NAME+ " ( "
			+TWEET_ID + " integer primary key autoincrement, "
			+USERNAME + " text, "
			+TWEET + " text, "
			+DATE + " text, "
			+PICTURE + " BLOB); ";
	
	
	
	
	
	
	
	public DbHelper(Context context)
	{
		super(context, DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(createDb);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
	
		db.execSQL("drop table "+ TABLE_NAME);
	}

}
