package com.TweetGet.Models;

import com.google.gson.annotations.SerializedName;

public class BannerSize {

	@SerializedName("w")
	private float mImageWidth;

	@SerializedName("h")
	private float mImageHeight;

	@SerializedName("url")
	private String mImageUrl;
	
	
	public float getWidth()
	{
		return mImageWidth;
	}
	
	public float getHeight()
	{
		return mImageHeight;
	}
	
	public String getImageUrl()
	{
		return mImageUrl;
	}

}
