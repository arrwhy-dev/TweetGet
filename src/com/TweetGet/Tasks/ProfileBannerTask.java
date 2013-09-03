package com.TweetGet.Tasks;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.TweetGet.R;
import com.TweetGet.EndPoints.ApiEndPoints;
import com.TweetGet.EndPoints.ApiHeaders;
import com.TweetGet.Managers.BearerTokenManager;
import com.TweetGet.Models.TimelineBannerContainer;
import com.google.gson.Gson;

public class ProfileBannerTask extends
		AsyncTask<TimelineBannerContainer, TimelineBannerContainer, TimelineBannerContainer> {

	private ListView mListView;
	private WeakReference<Context> mContext;
	private RelativeLayout profileBanner;
	
	public ProfileBannerTask(WeakReference<Context> context, ListView listview,RelativeLayout rl) {
		mContext = context;
		mListView = listview;
		profileBanner=rl;
	}

	@Override
	protected TimelineBannerContainer doInBackground(TimelineBannerContainer... params) {
		try {

			String authToken = BearerTokenManager
					.getBearerTokenSynchronously(mContext.get());

			DefaultHttpClient httpclient = new DefaultHttpClient(
					new BasicHttpParams());
			HttpGet httpget = new HttpGet(ApiEndPoints.TWITTER_DEFAULT_USER_DETAILS);

			httpget.setHeader(ApiHeaders.AUTHORIZATION, ApiHeaders.BEARER
					+ authToken);

			httpget.setHeader(ApiHeaders.CONTENT_TYPE,
					ApiHeaders.APPLICATION_JSON);

			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			
			String jsonString = EntityUtils.toString(entity);
			Log.d("PROFILE_BANNER_JSON", jsonString);
			//InputStream inputStream = entity.getContent();

			
//			TimelineBannerContainer bannerContainer = new Gson()
//					.fromJson(new InputStreamReader(inputStream),
//							TimelineBannerContainer.class);

			
			
			

			return new TimelineBannerContainer();
		} catch (Exception e) {
			Log.e("ProfileBannerTask", "Error:" + e.getMessage());
			return null;
		}
	}
	
	@Override
	protected void onPostExecute(TimelineBannerContainer result) {
	
		try {
		
			String url = result.getBannerSizes().getMobileRetinaBannerSize().getImageUrl();
			URL imageURl = new URL(url);
			Bitmap pic = BitmapFactory.decodeStream(imageURl.openConnection()
					.getInputStream());
			
			
			((ImageView)profileBanner.findViewById(R.id.profile_banner_image)).setImageBitmap(pic);
			
		} catch (Exception e) {
			Log.e("ProfileBannerTask-PostExecute", "Error:" + e.getMessage());
		}
	}

}
