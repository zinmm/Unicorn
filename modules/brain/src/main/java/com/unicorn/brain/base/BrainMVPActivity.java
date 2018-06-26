package com.unicorn.brain.base;

/**
 * Base MVP Activity
 * Created by ZhuJinMing on 2017/7/23.
 */
public abstract class BrainMVPActivity<V, P extends BrainPresenter<V>>
        extends BrainActivity {

    protected P mPresenter;

    @Override
    public void init() {

        if (createPresenter() != null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
            mPresenter.attachContext(mContext);
        }

        initData();
    }

    @Override
    protected final int getRootLayoutId() {
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

    protected abstract P createPresenter();

    protected abstract int rootLayoutId();
}