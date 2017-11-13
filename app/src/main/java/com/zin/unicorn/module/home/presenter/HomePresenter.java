package com.zin.unicorn.module.home.presenter;

import android.content.Intent;

import com.zin.unicorn.base.BasePresenter;
import com.zin.unicorn.module.home.view.HomeView;
import com.zin.unicorn.module.killapp.KillApplicationActivity;

/**
 * Created by ZhuJinMing on 2017/8/16.
 */
public class HomePresenter extends BasePresenter<HomeView> {

    public void startAllApp() {
        Intent intent = new Intent(mContext, KillApplicationActivity.class);
        startActivity(intent);
    }
}
