package com.unicorn.brain;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unicorn.brain.base.BrainActivity;
import com.unicorn.brain.util.density.DensityUtils;

import java.lang.ref.WeakReference;

/**
 * Created by zinmm on 16/7/13.
 */
public class BrainTopBarView {

    /**
     * @param mContext
     * @param text
     * @param linearLayout
     * @return TextView
     */
    public static TextView contentTextView(Context mContext, String text, LinearLayout linearLayout) {

        WeakReference<Context> contextWeakReference = new WeakReference<>(mContext);

        int textColorResources = contextWeakReference.get().getApplicationContext().getResources().getColor(R.color.black);

        TextView contentTextView = new TextView(contextWeakReference.get());
        contentTextView.setTextSize(17);
        contentTextView.setTextColor(textColorResources);
        contentTextView.setText(text);

        linearLayout.addView(contentTextView);

        return contentTextView;
    }

    /**
     * default textView
     *
     * @param mContext        context
     * @param stringResources string resource
     * @param linearLayout
     * @return TextView
     */
    public static TextView contentTextView(Context mContext, int stringResources, LinearLayout linearLayout) {

        WeakReference<Context> contextWeakReference = new WeakReference<>(mContext);

        String content = contextWeakReference.get().getApplicationContext().getResources().getString(stringResources);
        int textColorResources = contextWeakReference.get().getApplicationContext().getResources().getColor(R.color.black);

        TextView contentTextView = new TextView(contextWeakReference.get());
        contentTextView.setTextSize(17);
        contentTextView.setTextColor(textColorResources);
        contentTextView.setText(content);

        linearLayout.addView(contentTextView);

        return contentTextView;
    }

    /**
     * default back button
     *
     * @param mContext     context
     * @param linearLayout
     * @return ImageView
     */
    public static ImageView backImageView(Context mContext, LinearLayout linearLayout) {

        int dip15 = DensityUtils.dip2px(15);

        final WeakReference<Context> contextWeakReference = new WeakReference<>(mContext);
        final ImageView backImageView = new ImageView(contextWeakReference.get());

        backImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        backImageView.setImageResource(R.drawable.nav_back_enabled);
        linearLayout.addView(backImageView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BrainActivity) contextWeakReference.get()).onBackPressed();
            }
        });

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        backImageView.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        backImageView.setEnabled(true);
                        break;
                }
                return false;
            }
        });

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) backImageView.getLayoutParams();
        layoutParams.height = dip15;
        backImageView.setLayoutParams(layoutParams);

        return backImageView;
    }

    /**
     * default image
     *
     * @param mContext     context
     * @param resId        resId
     * @param linearLayout
     * @return ImageView
     */
    public static ImageView imageView(Context mContext, int resId, LinearLayout linearLayout) {

        int dip18 = DensityUtils.dip2px(18);

        WeakReference<Context> contextWeakReference = new WeakReference<>(mContext);

        ImageView imageView = new ImageView(contextWeakReference.get());
        imageView.setImageResource(resId);

        linearLayout.addView(imageView);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width = dip18;
        layoutParams.height = dip18;
        imageView.setLayoutParams(layoutParams);

        return imageView;
    }
}