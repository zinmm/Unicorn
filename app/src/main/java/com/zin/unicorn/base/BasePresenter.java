package com.zin.unicorn.base;

import android.content.Context;
import android.content.Intent;

import com.unicorn.brain.base.BrainPresenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Base of Presenter
 * Created by ZhuJinMing on 2017/05/07.
 */
public abstract class BasePresenter<V> extends BrainPresenter<V> {

    protected BaseActivity mActivity;
    protected Context mContext;
    protected Context mApplicationContext;

    private Reference<V> mViewRef;

    protected void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    protected void attachContext(Context context) {
        this.mContext = context;
        this.mApplicationContext = context.getApplicationContext();
        this.mActivity = (BaseActivity) context;
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

    public void startActivity(Intent intent) {
        mContext.startActivity(intent);
    }
}