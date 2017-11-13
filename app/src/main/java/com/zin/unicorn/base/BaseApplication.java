package com.zin.unicorn.base;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.zin.toolutils.ContextUtil;
import com.zin.toolutils.density.DensityUtils;
import com.zin.toolutils.log.LogcatUtil;
import com.zin.unicorn.module.MainActivity;

import static com.taobao.sophix.PatchStatus.CODE_REQ_START;

/**
 * Created by zinmm on 12/14/16.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initContextUtil();

        DensityUtils.setAppContext(this);

        initHotfix();
    }

    private void initHotfix() {

        String appVersion;
        try {
            appVersion = getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0.0";
        }

        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setEnableDebug(true)
                .setPatchLoadStatusStub((mode, code, info, handlePatchVersion) -> {

                    LogcatUtil.getInstance().deBug(info, this);
                    // 补丁加载回调通知
                    if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                        // 表明补丁加载成功
                    } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                        // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                        // 建议: 用户可以监听进入后台事件, 然后应用自杀
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        getApplicationContext().startActivity(intent);
                        SophixManager.getInstance().killProcessSafely();
                    } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                        // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                        SophixManager.getInstance().cleanPatches();
                    } else if (code == CODE_REQ_START) {

                    } else {
                        // 其它错误信息, 查看PatchStatus类说明
                    }
                }).initialize();
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
