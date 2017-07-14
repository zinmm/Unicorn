package com.zin.unicorn.base;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.zin.unicorn.module.MainActivity;
import com.zin.unicorn.util.ContextUtil;

import java.io.IOException;

/**
 * Created by zinmm on 12/14/16.
 */
public class BaseApplication extends Application {

    private static final String TAG = "euler";

    private static final String APATCH_PATH = "/out.apatch";
    /**
     * patch manager
     */
    private PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();

        // Must!! First call this method.
        initContextUtil();

        // initialize
        mPatchManager = new PatchManager(this);
        mPatchManager.init("1.0");
        Log.d(TAG, "inited.");

        // load patch
        mPatchManager.loadPatch();
        Log.d(TAG, "apatch loaded.");

        // add patch at runtime
        try {
            // .apatch file path
            //获取当前程序路径

            String a = getApplicationContext().getFilesDir().getAbsolutePath();

            String path = getApplicationContext().getPackageResourcePath();

            String patchFileString = /*Environment.getExternalStorageDirectory()
                    .getAbsolutePath()*/a + APATCH_PATH;
            mPatchManager.addPatch(patchFileString);
            Log.d(TAG, "apatch:" + patchFileString + " added.");
        } catch (IOException e) {
            Log.e(TAG, "", e);
        }
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
