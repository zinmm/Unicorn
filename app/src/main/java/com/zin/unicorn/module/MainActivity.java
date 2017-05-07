package com.zin.unicorn.module;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseActivity;
import com.zin.unicorn.base.Navigator;
import com.zin.unicorn.util.UserUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class MainActivity extends BaseActivity {

    private final int LOAD_HOME_TIME = 1500;

    @Override
    public void init() {

        loadHome();
    }

    private void loadHome() {
        Observable.timer(LOAD_HOME_TIME , TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(aLong -> {
                    if (UserUtil.getInstance().isUserLogin()) {
                        Navigator.INSTANCE.navigateToHome(mContext);
                    } else {
                        Navigator.INSTANCE.navigateToHome(mContext);
                    }

                    finish();
                });
    }

    @Override
    protected int getRootLayoutId() {
        return R.layout.activity_main;
    }
}
