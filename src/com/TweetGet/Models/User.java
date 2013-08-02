package com.TweetGet.Models;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class User {

	@SerializedName("screen_name")
	private String mScreenName;

	@SerializedName("profile_image_url")
	private String mProfileImageUrl;
	
	private Bitmap profilePic;
	
	
	public String getScreenName()
	{
		return mScreenName;
	}
	
	public String getProfileImageUrl()
	{
		return mProfileImageUrl;
	}
	
	public Bitmap getProfilePic()
	{
		return profilePic;
	}
	
	public void setProfilePic(Bitmap profile)
	{
		profilePic=profile;
	}

}
