package com.example.tweetGet;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.json.JSONArray;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.AsyncTask;

public class cacheTweets extends AsyncTask<Void , Void, Void>
{

	JSONArray Sessions;
	DbHelper dbhelper;
	SQLiteDatabase db;
	ArrayList <TweetData> items;

	public cacheTweets( Context context, ArrayList<TweetData> items)
	{


		dbhelper = new DbHelper(context);
		db = dbhelper.getWritableDatabase();
		this.items=items;

	}

	protected void onPostExecute(ArrayList<TweetData> items)
	{

	}
	

	@Override
	protected Void doInBackground(Void... params)
	{
		
		for (int i = 0; i < items.size(); ++i)
		{
			
			TweetData current = items.get(i);
			ContentValues cv = new ContentValues();
			
			cv.put(DbHelper.USERNAME, current.getUserID());
			cv.put(DbHelper.TWEET, current.getTweet());
			cv.put(DbHelper.DATE,current.getDateTime());
			try 
			{
			Bitmap bmp = current.getPic();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
			byte[] byteArray = stream.toByteArray();	
			cv.put(DbHelper.PICTURE, byteArray);
			}catch (Exception e){};
			
			db.insert(DbHelper.TABLE_NAME, null, cv);
		
		
		}
		
		return null;
	}
	

}
