package com.zin.unicorn.base;

/**
 * Created by zhujinming on 17/5/4.
 */

public abstract class BaseMVPActivity<V, T extends BasePresenter<V>>
        extends BaseActivity {

    protected T mPresenter;

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

    protected abstract T createPresenter();

    protected abstract int rootLayoutId();

    protected abstract void initData();

}
