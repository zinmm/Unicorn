package com.unicorn.brain;

import android.content.Context;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by zhujinming on 2017/7/23.
 */
public abstract class BrainPresenter<V> {

    protected BrainActivity mActivity;
    protected Context mContext;
    protected Context mAppcationContext;

    private Reference<V> mViewRef;

    protected void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    protected void attachContext(Context mContext) {
        this.mContext = mContext;
        this.mAppcationContext = mContext.getApplicationContext();
        this.mActivity = (BrainActivity) mContext;
    }

    protected V getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    void detachContext() {
        mContext = null;
        mAppcationContext = null;
        mActivity = null;
    }
}
