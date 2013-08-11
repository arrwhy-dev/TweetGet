package com.TweetGet.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.TweetGet.R;
import com.TweetGet.Models.TweetStatus;
import com.TweetGet.Models.statusesContainer;
import com.TweetGet.Utils.ImageDownloaderUtils;
import com.TweetGet.Utils.ImageDownloaderUtils.ViewHolder;
import com.TweetGet.activites.MainActivity;
import com.TweetGet.activites.TransparentActivity;


public class TweetListAdapter extends BaseAdapter
{

	LayoutInflater inflater;
	statusesContainer statuses;
	Context mContext;
	Activity mActivity;
	
	public TweetListAdapter(Context context,statusesContainer items)
	{
		super();
		this.mContext=context;
		statuses = items;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mActivity=MainActivity.mActivity;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{

		TweetStatus item = statuses.getStatuses().get(position);

		ViewHolder holder;

		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.item_row, null);

			holder = new ViewHolder();
			holder.tweetText = (TextView) convertView.findViewById(R.id.tweet);
			holder.userId = (TextView) convertView.findViewById(R.id.userName);
			holder.dateAndTime = (TextView) convertView.findViewById(R.id.dateAndTime);
			holder.profilePicture = (ImageView) convertView.findViewById(R.id.profileImage);
			
			holder.profilePicture.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					zoomInToImage( v);
				}

				private void zoomInToImage(View v) {
					int[] location = new int[2];
					v.getLocationOnScreen(location);
					Rect rectangle = new Rect();
					Window window = mActivity.getWindow();
					window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
					int statusBarHeight = rectangle.top;
					TransparentActivity.newInstance(mActivity, v.getMeasuredWidth() - v.getPaddingLeft() - v.getPaddingRight(),
							v.getMeasuredHeight() - v.getPaddingTop() - v.getPaddingBottom(), location[0] + v.getPaddingLeft(), location[1] - statusBarHeight + v.getPaddingTop()
							);
				}
			});

			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tweetText.setText(item.getText());
		holder.userId.setText(item.getUser().getScreenName());
		holder.dateAndTime.setText(item.getDateTime());
		holder.position = position;
	

		getProfilePic(item, position, holder);

		return convertView;
	}

	public void getProfilePic(TweetStatus item, int position, ViewHolder holder)
	{
		if (item.getUser().getProfilePic() == null)
		{
			ImageDownloaderUtils i = new ImageDownloaderUtils(position, holder,
					item.getUser().getProfileImageUrl(), item,mContext);
			i.execute();

		} else
		{
			holder.profilePicture.setImageBitmap(item.getUser().getProfilePic());

		}

	}

	@Override
	public int getCount()
	{

		return statuses.getStatuses().size();
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