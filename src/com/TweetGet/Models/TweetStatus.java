package com.TweetGet.Models;

import com.google.gson.annotations.SerializedName;

public class TweetStatus {

	@SerializedName("created_at")
	private String mCreatedAt;

	@SerializedName("text")
	private String text;

	@SerializedName("user")
	private User mUser;

	public String getDateTime() {
		return mCreatedAt;
	}

	public String getText() {
		return text;
	}

	public User getUser() {
		return mUser;
	}

}
