package com.TweetGet.Models;

import android.graphics.Bitmap;

public class TweetModel
{

	private String profileUrl;
	private String tweet;
	private String userID;
	private String dateTime;
	private Bitmap profileImage;

	public TweetModel(String pic, String tweet, String userId, String dateTime)
	{
		this.setProfilePicture(pic);
		this.setTweet(tweet);
		this.setUserID(userId);
		this.setDateTime(dateTime);
	}
	
	public TweetModel(String tweet,String userId,String dateTime)
	{
		this.tweet = tweet;
		this.userID= userId;
		this.dateTime=dateTime;
	}

	public String getTweet()
	{
		return tweet;
	}

	public void setTweet(String tweet)
	{
		this.tweet = tweet;
	}

	public String getDateTime()
	{
		return dateTime;
	}

	public void setDateTime(String dateTime)
	{
		this.dateTime = dateTime;
	}

	public String getProfilePicture()
	{
		return profileUrl;
	}

	public void setProfilePicture(String profilePicture)
	{
		this.profileUrl = profilePicture;
	}

	public String getUserID()
	{
		return userID;
	}

	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	public Bitmap getPic()
	{
		return profileImage;
	}

	public void setPic(Bitmap pic)
	{
		this.profileImage = pic;
	}

}
