package com.TweetGet.Managers;

import com.TweetGet.Tasks.BearerTokenTask;

public class BearerTokenManager {

	private static String BearerToken;

	public static String getBearerToken() {
		
		if (BearerToken == null) {

			new BearerTokenTask().execute();
		}

		return BearerToken;

	}
	
	
	public static void setBearerToken(String token)
	{
		BearerToken = token;
	}

}
