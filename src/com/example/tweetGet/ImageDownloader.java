package com.example.tweetGet;

import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

class ImageDownloader extends AsyncTask<Void, Void, Bitmap>
{
	private int mPosition;
	private ViewHolder mHolder;
	private String url;
	private TweetData item;

	public ImageDownloader(int position, ViewHolder holder, String url,
			TweetData item)
	{
		mPosition = position;
		mHolder = holder;
		this.item = item;
		this.url = url;

	}

	protected void onPostExecute(Bitmap bitmap)
	{
		if (mHolder.position == mPosition)
		{
			mHolder.profilePicture.setImageBitmap(bitmap);
			item.setPic(bitmap);
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