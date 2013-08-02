package com.TweetGet.Models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class statusesContainer {
	
	@SerializedName("statuses")
	List<Status> statuses;
	
	
	public List<Status>getStatuses()
	{
		return statuses;
	}

}
