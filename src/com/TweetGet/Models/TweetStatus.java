package com.TweetGet.Models;

import com.google.gson.annotations.SerializedName;

public class TweetStatus {

	@SerializedName("created_at")
	private String mCreationDate;

	@SerializedName("text")
	private String mTweetText;

	@SerializedName("user")
	private User mUser;

	public String getDateTime() {
		return mCreationDate;
	}

	public String getTweetText() {
		return mTweetText;
	}

	public User getUser() {
		return mUser;
	}

}
