package com.TweetGet.Models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class statusesContainer {
	
	@SerializedName("statuses")
	private List<TweetStatus> statuses;
	
	
	public List<TweetStatus>getStatuses()
	{
		return statuses;
	}

}
