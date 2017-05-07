package com.zin.unicorn.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by zinmm on 17/3/21.
 */
public class ObservableScrollView extends ScrollView {
    /**
     * ScrollView正在向上滑动
     */
    public static final int SCROLL_UP = 0x01;
    /**
     * ScrollView正在向下滑动
     */
    public static final int SCROLL_DOWN = 0x10;
    /**
     * 最小的滑动距离
     */
    private static final int SCROLLLIMIT = 40;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private ScrollListener mListener;

    public interface ScrollListener {
        void scrollOritention(int oritention);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (oldt > t && oldt - t > SCROLLLIMIT) {// 向下
            if (mListener != null) {
                mListener.scrollOritention(SCROLL_DOWN);
            }
        } else if (oldt < t && t - oldt > SCROLLLIMIT) {// 向上
            if (mListener != null) {
                mListener.scrollOritention(SCROLL_UP);
            }
        }
    }

    public void setScrollListener(ScrollListener l) {
        this.mListener = l;
    }

}
