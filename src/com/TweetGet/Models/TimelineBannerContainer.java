package com.TweetGet.Models;

import com.google.gson.annotations.SerializedName;

public class TimelineBannerContainer {
	
	
	@SerializedName("sizes")
	private BannerSizes sizes;
	
	
	public 	BannerSizes getBannerSizes()
	{
		return sizes;
	}

}
