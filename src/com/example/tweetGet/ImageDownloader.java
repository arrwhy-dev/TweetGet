package com.example.tweetGet;

import java.io.ByteArrayOutputStream;
import java.net.URL;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

class ImageDownloader extends AsyncTask<Void, Void, Bitmap>
{
	private int mPosition;
	private ViewHolder mHolder;
	private String url;
	private TweetData item;
	DbHelper dbhelper;
	SQLiteDatabase db;

	public ImageDownloader(int position, ViewHolder holder, String url,
			TweetData item,Context c)
	{
		mPosition = position;
		mHolder = holder;
		this.item = item;
		this.url = url;
		dbhelper = new DbHelper(c);
		db = dbhelper.getWritableDatabase();
	

	}

	protected void onPostExecute(Bitmap bitmap)
	{
		if (mHolder.position == mPosition)
		{
			mHolder.profilePicture.setImageBitmap(bitmap);
			item.setPic(bitmap);
					
			ContentValues cv = new ContentValues();
			
			cv.put(DbHelper.USERNAME, item.getUserID());
			cv.put(DbHelper.TWEET, item.getTweet());
			cv.put(DbHelper.DATE,item.getDateTime());
			
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
			byte[] byteArray = stream.toByteArray();	
			cv.put(DbHelper.PICTURE, byteArray);
		
			db.insert(DbHelper.TABLE_NAME, null, cv);
		

		}
	}

	@Override
	protected Bitmap doInBackground(Void... params)
	{

		try
		{
			URL url = new URL(this.url);
			Bitmap pic = BitmapFactory.decodeStream(url.openConnection()
					.getInputStream());
			return pic;
		} catch (Exception e)
		{
		};
		return null;
	}
}