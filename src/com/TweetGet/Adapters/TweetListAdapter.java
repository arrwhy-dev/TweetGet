package com.TweetGet.Adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.TweetGet.R;
import com.TweetGet.Models.TweetStatus;
import com.TweetGet.Utils.ImageDownloaderUtils;
import com.TweetGet.Utils.ImageDownloaderUtils.ViewHolder;

public class TweetListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<TweetStatus> mTweetStatuses;

	public TweetListAdapter(Context context, List<TweetStatus> tweetStatuses) {
		super();
		this.mTweetStatuses = tweetStatuses;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		TweetStatus item = mTweetStatuses.get(position);
		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_row, null);

			holder = new ViewHolder();
			holder.tweetText = (TextView) convertView.findViewById(R.id.tweet);
			holder.userId = (TextView) convertView.findViewById(R.id.userName);
			holder.dateAndTime = (TextView) convertView
					.findViewById(R.id.dateAndTime);
			holder.profilePicture = (ImageView) convertView
					.findViewById(R.id.profileImage);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tweetText.setText(item.getTweetText());
		holder.userId.setText(item.getUser().getScreenName());
		holder.dateAndTime.setText(item.getFormatedDateTime());
		holder.position = position;

		getProfilePic(item, position, holder);

		return convertView;
	}

	public void getProfilePic(TweetStatus item, int position, ViewHolder holder) {

		if (item.getUser().getProfileImageBitmap() == null) {
			ImageDownloaderUtils i = new ImageDownloaderUtils(position, holder,
					item.getUser().getProfileImageUrl(), item);
			i.execute();

		} else {
			holder.profilePicture
					.setImageBitmap(item.getUser().getProfileImageBitmap());

		}

	}

	@Override
	public int getCount() {

		return mTweetStatuses.size();
	}

	@Override
	public Object getItem(int position) {

		return mTweetStatuses.get(position);
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

}