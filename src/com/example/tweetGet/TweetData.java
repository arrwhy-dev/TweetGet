package com.example.tweetGet;

import android.graphics.Bitmap;

public class TweetData
{

	private String profileUrl;
	private String tweet;
	private String userID;
	private String dateTime;
	private Bitmap profileImage;

	public TweetData(String pic, String tweet, String userId, String dateTime)
	{
		this.setProfilePicture(pic);
		this.setTweet(tweet);
		this.setUserID(userId);
		this.setDateTime(dateTime);
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
