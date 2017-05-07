package com.zin.unicorn.base;

import android.content.Context;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Base of Presenter
 * Created by zhujinming on 2017/05/07.
 */
public abstract class BasePresenter<V> {

    protected BaseActivity mActivity;
    protected Context mContext;
    protected Context mAppcationContext;

    private Reference<V> mViewRef;

    protected void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    protected void attachContext(Context mContext) {
        this.mContext = mContext;
        this.mAppcationContext = mContext.getApplicationContext();
        this.mActivity = (BaseActivity) mContext;
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