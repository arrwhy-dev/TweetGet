package com.TweetGet.Utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.TweetGet.R;

import de.keyboardsurfer.android.widget.crouton.Crouton;

public class NetworkUtils {

	public static boolean wifiIsEnaled(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			return false;
		} else
			return true;
	}

	public static void notifyWifiState(Activity activity) {
		if (!wifiIsEnaled(activity)) {

			Crouton.makeText(activity, R.string.NetworkConnectionPrompt,
					AnimationUtils.CroutonStyles.INFINITE).show();

		}
	}

}
