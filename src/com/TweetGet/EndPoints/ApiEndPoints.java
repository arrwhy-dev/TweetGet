package com.TweetGet.EndPoints;

import android.util.Base64;

public class ApiEndPoints {

	public static final String TWITTER_SEARCH = "https://api.twitter.com/1.1/search/tweets.json?q=%23";
	public static final String TWITTER_USER_TIMELINE = "https://api.twitter.com/1.1/statuses/user_timeline.json";
	public static final String TWITTER_USER_DETAILS = "https://api.twitter.com/1.1/users/profile_banner.json?";
	public static String TWITTER_USER_TIMElINE_DEFAULT_SEARCH = TWITTER_USER_TIMELINE;
	public static String TWITTER_DEFAULT_SEARCH = TWITTER_SEARCH + "android";
	public static String TWITTER_DEFAULT_USER_DETAILS=TWITTER_USER_DETAILS;
	public static final String APIKEY = "jdFRhDgtmTufsRspN1RZDA";
	public static final String APISECRET = "wINJDeWfNzM0CSnRQemgyH5IRhidDajy6m06eLinY";
	public static final String COMBINED_KEY = APIKEY + ":" + APISECRET;
	public static final String RESULT_COUNT = "&count=";
	public static final String RESULT_TYPE = "&result_type=";
	public static final String TWITTER_AUTH_TOKEN = "https://api.twitter.com/oauth2/token";

	public static final String getApiAuthKey() {
		return "Basic "
				+ Base64.encodeToString(COMBINED_KEY.getBytes(), Base64.NO_WRAP);
	}

}