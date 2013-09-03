package com.TweetGet.Activites;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.TweetGet.R;
/*
 * TODO : FIX THIS TO PROPERLY LOAD A ZOOMED IN VERSION
 * 			OF THE TWTITER PROFILE PIC, REQUIRES SOME INVESTIGATION
 * 			ON ANIMATIONS.
 * 
 * 
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
public class TransparentActivity extends Activity {

	private static final int DURATION = 250;

	private static class Extras {
		private static final String WIDTH = "width";
		private static final String HEIGHT = "height";
		private static final String LEFT = "left";
		private static final String TOP = "top";
	}

	public static void newInstance(Activity activity, int width, int height,
			int left, int top) {
		Intent intent = new Intent(activity, TransparentActivity.class);
		intent.putExtra(Extras.WIDTH, width);
		intent.putExtra(Extras.HEIGHT, height);
		intent.putExtra(Extras.LEFT, left);
		intent.putExtra(Extras.TOP, top);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

		activity.startActivity(intent);
	}

	private int mWidth;
	private int mHeight;
	private int mLeft;
	private int mTop;
	private float mFullHeight;
	private ImageView mImageView;
	private View mBackdrop;
	private boolean mExitting = false;
	private Point mScreenSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transparent);

		unpackIntentAndSetFields();

		configureImageView();
		configureBackdrop();

		setCover();
		moveImageOverOldImage(0, null);
		animateToFullSize();
	}

	private void configureBackdrop() {
		mBackdrop = findViewById(R.id.backdrop);
		mBackdrop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				exit();
			}
		});
	}

	private void configureImageView() {
		mImageView = (ImageView) findViewById(R.id.image);
		mImageView.setLayoutParams(new FrameLayout.LayoutParams(mScreenSize.x,
				(int) mFullHeight));
	}

	private void setCover() {
		View cover = findViewById(R.id.cover);
		cover.setLayoutParams(new FrameLayout.LayoutParams(mWidth, mHeight));
		ViewPropertyAnimator animator = cover.animate();
		animator.translationX(mLeft);
		animator.translationY(mTop);
		animator.setDuration(0);
		animator.start();
	}

	@Override
	public void onBackPressed() {
		exit();
	}

	private void exit() {
		if (!mExitting) {
			mExitting = true;
			moveImageOverOldImage(DURATION, new AnimatorListener() {
				@Override
				public void onAnimationStart(Animator arg0) {
				}

				@Override
				public void onAnimationRepeat(Animator arg0) {
				}

				@Override
				public void onAnimationEnd(Animator arg0) {
					finish();
					overridePendingTransition(0, 0);
				}

				@Override
				public void onAnimationCancel(Animator arg0) {
				}
			});
		}
	}

	private void moveImageOverOldImage(long duration, AnimatorListener listener) {
		ViewPropertyAnimator animator = mImageView.animate();

		float scale = ((float) mWidth) / ((float) mScreenSize.x);
		animator.scaleX(scale);
		animator.scaleY(scale);

		float translationX = -1f * ((mScreenSize.x - mWidth) / 2f);
		animator.translationX(translationX + mLeft);

		float translationY = -1f * ((mFullHeight - mHeight) / 2f);
		animator.translationY(translationY + mTop);

		animator.setDuration(duration);
		animator.setListener(listener);
		animator.start();

		animator = mBackdrop.animate();
		animator.alpha(0);
		animator.setDuration(duration);
		animator.start();
	}

	private void animateToFullSize() {
		ViewPropertyAnimator animator = mImageView.animate();
		animator.translationX(0);
		animator.translationY((mScreenSize.y - mFullHeight) / 2.0f);
		animator.scaleX(1);
		animator.scaleY(1);
		animator.setDuration(DURATION);
		animator.start();

		animator = mBackdrop.animate();
		animator.alpha(0.8f);
		animator.setDuration(DURATION);
		animator.start();
	}

	private void unpackIntentAndSetFields() {
		Intent intent = getIntent();
		mWidth = intent.getIntExtra(Extras.WIDTH, 0);
		mHeight = intent.getIntExtra(Extras.HEIGHT, 0);
		mLeft = intent.getIntExtra(Extras.LEFT, 0);
		mTop = intent.getIntExtra(Extras.TOP, 0);

		Display display = getWindowManager().getDefaultDisplay();
		mScreenSize = new Point();
		display.getSize(mScreenSize);

		mFullHeight = mScreenSize.x * ((float) mHeight) / mWidth;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}