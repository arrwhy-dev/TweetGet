package com.TweetGet.Models;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class User {

	@SerializedName("screen_name")
	private String mScreenName;

	@SerializedName("profile_image_url")
	private String mProfileImageUrl;
	
	@SerializedName("profile_background_image_url")
	private String mProfileBannerImageUrl;
	
	@SerializedName("description")
	private String mDescription;
	
	@SerializedName("name")
	private String mName;
	
	private Bitmap mProfilePic;
	
	
	public String getScreenName()
	{
		return mScreenName;
	}
	
	public String getProfileImageUrl()
	{
		return mProfileImageUrl;
	}
	
	public Bitmap getProfileImageBitmap()
	{
		return mProfilePic;
	}
	
	public String getName()
	{
		return mName;
	}
	
	public void setProfileImageBitmap(Bitmap profile)
	{
		mProfilePic=profile;
	}
	
	public String getDescription()
	{
		return mDescription;
	}

}
