package com.zin.unicorn.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.zin.unicorn.util.ContextUtil;

/**
 * Created by zinmm on 12/14/16.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Must!! First call this method.
        initContextUtil();
    }

    private void initContextUtil() {

        ContextUtil.getInstance().setContext(getApplicationContext());

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                ContextUtil.getInstance().setContext(activity); // Must!! First call this method.
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

            }
        });
    }
}
