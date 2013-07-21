package com.TweetGet.Adapters;

import java.util.List;

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
import com.TweetGet.Models.TweetModel;
import com.TweetGet.Utils.ImageDownloaderUtils;
import com.TweetGet.Utils.ImageDownloaderUtils.ViewHolder;
import com.TweetGet.activites.MainActivity;
import com.TweetGet.activites.TransparentActivity;


public class TweetListAdapter extends BaseAdapter
{

	LayoutInflater inflater;
	List<TweetModel> items;
	Context mContext;
	Activity mActivity;
	
	public TweetListAdapter(Context context,List<TweetModel> items)
	{
		super();
		this.mContext=context;
		this.items = items;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mActivity=MainActivity.mActivity;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{

		TweetModel item = items.get(position);

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

		holder.tweetText.setText(item.getTweet());
		holder.userId.setText(item.getUserID());
		holder.dateAndTime.setText(item.getDateTime());
		holder.position = position;
	

		getProfilePic(item, position, holder);

		return convertView;
	}

	public void getProfilePic(TweetModel item, int position, ViewHolder holder)
	{
		if (item.getPic() == null)
		{
			ImageDownloaderUtils i = new ImageDownloaderUtils(position, holder,
					item.getProfilePicture(), item,mContext);
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