package com.TweetGet.Utils;

import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.TweetGet.Models.TweetModel;

public class ImageDownloaderUtils extends AsyncTask<Void, Void, Bitmap> {
	private int mPosition;
	private ViewHolder mHolder;
	private String url;
	private TweetModel item;

	public ImageDownloaderUtils(int position, ViewHolder holder, String url,
			TweetModel item, Context c) {
		mPosition = position;
		mHolder = holder;
		this.item = item;
		this.url = url;

	}

	protected void onPostExecute(Bitmap bitmap) {
		if (mHolder.position == mPosition) {
			mHolder.profilePicture.setImageBitmap(bitmap);
			item.setPic(bitmap);

		}
	}

	@Override
	protected Bitmap doInBackground(Void... params) {

		try {
			URL url = new URL(this.url);
			Bitmap pic = BitmapFactory.decodeStream(url.openConnection()
					.getInputStream());
			return pic;
		} catch (Exception e) {
		}
		;
		return null;
	}
	
	public static class ViewHolder
	{
		public TextView tweetText;
		public TextView userId;
		public TextView dateAndTime;
		public ImageView profilePicture;
		public int position;
	}
}