package com.TweetGet.Models;

import com.google.gson.annotations.SerializedName;

public class BannerSizes {
	
	
	@SerializedName("ipad")
	private BannerSize mIpadBannerSize;
	
	
	@SerializedName("mobile")
	private BannerSize mMobileBannerSize;
	
	
	@SerializedName("mobile_retina")
	private BannerSize mMobileRetinaBannerSize;
	
	public BannerSize getIpadBannerSize()
	{
		return mIpadBannerSize;
	}
	
	public BannerSize getMobileRetinaBannerSize()
	{
		return mMobileRetinaBannerSize;
	}
	
	public BannerSize getMobileBannerSize()
	{
		return mMobileBannerSize;
	}
	
	
	

}
