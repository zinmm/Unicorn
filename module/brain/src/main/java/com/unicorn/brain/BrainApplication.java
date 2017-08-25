package com.unicorn.brain;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.zin.toolutils.ContextUtil;
import com.zin.toolutils.density.DensityUtils;

/**
 * Created by zhujinming on 2017/7/23.
 */
public class BrainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initContextUtil();

        DensityUtils.setAppContext(this);
    }

    private void initContextUtil() {

        ContextUtil.getInstance().setContext(getApplicationContext());

        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ContextUtil.getInstance().setContext(activity); // Must!! First call this method.

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ContextUtil.getInstance().onDestroyContext();
            }
        });
    }
}
