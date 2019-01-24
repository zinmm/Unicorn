package com.zin.unicorn.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by ZhuJinMing on 17/5/4.
 */
public abstract class BaseMVPActivity<V, T extends BasePresenter<V>>
        extends BaseActivity {

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    public void init() {
        ButterKnife.bind(this);

        if (createPresenter() != null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
            mPresenter.attachContext(mContext);
        }

        initData();
    }

    @Override
    protected int getRootLayoutId() {
        return rootLayoutId();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.detachView();
        mPresenter.detachContext();
        mPresenter = null;
    }

    protected abstract void initData();

    protected abstract T createPresenter();

    protected abstract int rootLayoutId();
}
