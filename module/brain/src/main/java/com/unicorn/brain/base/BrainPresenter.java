package com.unicorn.brain.base;

import android.content.Context;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Base Presenter
 * Created by ZhuJinMing on 2017/7/23.
 */
public abstract class BrainPresenter<V> {

    protected BrainActivity mActivity;
    protected Context mContext;
    protected Context mApplicationContext;

    private Reference<V> mViewRef;

    protected void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    protected void attachContext(Context mContext) {
        this.mContext = mContext;
        this.mApplicationContext = mContext.getApplicationContext();
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
        mApplicationContext = null;
        mActivity = null;
    }
}
