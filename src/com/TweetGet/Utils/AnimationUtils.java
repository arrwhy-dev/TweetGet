package com.TweetGet.Utils;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import de.keyboardsurfer.android.widget.crouton.Style;

public class AnimationUtils {

	public static class CroutonStyles {
		public static final Style INFINITE = new Style.Builder()
				.setBackgroundColorValue(Style.holoRedLight).build();
	}

	public static void setLoadingAnimation(ListView lv) {

		AnimationSet set = new AnimationSet(true);
		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(200);
		set.addAnimation(animation);

		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(200);
		set.addAnimation(animation);

		LayoutAnimationController controller = new LayoutAnimationController(
				set, 1.0f);

		lv.setLayoutAnimation(controller);

	}

}
