package com.zin.unicorn.module.killapp;

import android.widget.ListView;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseMVPActivity;
import com.zin.unicorn.module.killapp.presenter.KillApplicationPresenter;
import com.zin.unicorn.module.killapp.view.KillApplicationView;

import butterknife.BindView;

/**
 * Created by ZhuJinMing on 2017/8/28.
 */
public class KillApplicationActivity extends BaseMVPActivity<KillApplicationView, KillApplicationPresenter> implements KillApplicationView {

    @BindView(R.id.lv_all_application)
    ListView lvAllApplication;

    @Override
    protected void initData() {
        mPresenter.initAllApplicationListView();
    }

    @Override
    protected KillApplicationPresenter createPresenter() {
        return new KillApplicationPresenter();
    }

    @Override
    protected int rootLayoutId() {
        return R.layout.activity_kill_application;
    }

    @Override
    public ListView lvAllApplication() {
        return lvAllApplication;
    }
}
