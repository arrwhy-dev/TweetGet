package com.TweetGet.Network;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.EndPoints.ApiHeaders;

public class ApiPosts {

	public static HttpResponse postToBearerToken() throws IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient(
				new BasicHttpParams());

		HttpPost httppost = new HttpPost(ApiEndPoints.TWITTER_AUTH_TOKEN);

		httppost.setHeader(ApiHeaders.AUTHORIZATION,
				ApiEndPoints.getApiAuthKey());

		httppost.setHeader(ApiHeaders.CONTENT_TYPE, ApiHeaders.UTF_ENCODING);
		httppost.setEntity(new StringEntity(ApiHeaders.GRANT_TYPE));

		return httpclient.execute(httppost);

	}
	
	public static HttpResponse postToSearch(String url,String authToken) throws IOException
	{
		DefaultHttpClient httpclient = new DefaultHttpClient(
				new BasicHttpParams());
		HttpGet httpget = new HttpGet(url);

		httpget.setHeader(ApiHeaders.AUTHORIZATION, ApiHeaders.BEARER
				+ authToken);

		httpget.setHeader(ApiHeaders.CONTENT_TYPE,
				ApiHeaders.APPLICATION_JSON);

		return httpclient.execute(httpget);
		
	}

}
