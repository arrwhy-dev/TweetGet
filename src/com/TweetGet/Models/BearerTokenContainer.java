package com.TweetGet.Models;

import com.google.gson.annotations.SerializedName;

public class BearerTokenContainer {

	@SerializedName("token_type")
	private String mTokenType;

	@SerializedName("access_token")
	private String mAccessToken;
	
	
	public String getAccessToken()
	{
		return mAccessToken;
	}

}
