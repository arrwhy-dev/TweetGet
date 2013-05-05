package com.example.tweetGet;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListViewAdapter extends BaseAdapter
{

	LayoutInflater inflater;
	List<TweetData> items;

	public CustomListViewAdapter(Activity context, List<TweetData> items)
	{
		super();

		this.items = items;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{

		TweetData item = items.get(position);

		ViewHolder holder;

		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.item_row, null);

			holder = new ViewHolder();
			holder.tweetText = (TextView) convertView.findViewById(R.id.tweet);
			holder.userId = (TextView) convertView.findViewById(R.id.userName);
			holder.dateAndTime = (TextView) convertView
					.findViewById(R.id.dateAndTime);
			holder.profilePicture = (ImageView) convertView
					.findViewById(R.id.profileImage);

			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tweetText.setText(item.getTweet());
		holder.userId.setText(item.getUserID());
		holder.dateAndTime.setText(item.getDateTime());
		holder.position = position;

		getProfilePic(item, position, holder);

		return convertView;
	}

	public void getProfilePic(TweetData item, int position, ViewHolder holder)
	{
		if (item.getPic() == null)
		{
			ImageDownloader i = new ImageDownloader(position, holder,
					item.getProfilePicture(), item);
			i.execute();

		} else
		{
			holder.profilePicture.setImageBitmap(item.getPic());

		}

	}

	@Override
	public int getCount()
	{

		return items.size();
	}

	@Override
	public Object getItem(int position)
	{

		return null;
	}

	@Override
	public long getItemId(int position)
	{

		return 0;
	}

}