package com.TweetGet.EndPoints;

import android.util.Base64;

public class ApiEndPoints {

	public static final String TWITTER_SEARCH = "https://api.twitter.com/1.1/search/tweets.json?q=%23";
	public static  String TWITTER_DEFAULT_SEARCH = TWITTER_SEARCH+"android";
	public static final String APIKEY = "jdFRhDgtmTufsRspN1RZDA";
	public static final String APISECRET = "wINJDeWfNzM0CSnRQemgyH5IRhidDajy6m06eLinY";
	public static final String COMBINED_KEY = APIKEY + ":" + APISECRET;
	public static String RESULT_COUNT="&count=";
	public static String RESULT_TYPE="&result_type=";

	public static final String TWITTER_AUTH_TOKEN = "https://api.twitter.com/oauth2/token";
	private static String BearerToken; 
	private static String jsonString;

	public static final String getApiAuthKey() {
		return "Basic "
				+ Base64.encodeToString(COMBINED_KEY.getBytes(), Base64.NO_WRAP);
	}
	
	public static final void setBearerToken(String s )
	{
		BearerToken=s;
	}
	
	public static final String getBearerToken()
	{
		return BearerToken;
	}
	
	
	public static final void setJsonString(String s )
	{
		jsonString=s;
	}
	
	public static final String getJSONString()
	{
		return jsonString;
	}
}