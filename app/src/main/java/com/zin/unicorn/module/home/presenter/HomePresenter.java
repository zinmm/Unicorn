package com.zin.unicorn.module.home.presenter;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.zin.toolutils.gson.GsonUtils;
import com.zin.unicorn.base.BasePresenter;
import com.zin.unicorn.module.home.view.HomeView;
import com.zin.unicorn.module.killapp.KillApplicationActivity;
import com.zin.unicorn.pojo.app.AppPoJo;
import com.zin.unicorn.pojo.app.AppTypePoJo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.pm.PackageManager.GET_ACTIVITIES;

/**
 * Created by ZhuJinMing on 2017/8/16.
 */
public class HomePresenter extends BasePresenter<HomeView> {

    public void startAllApp() {
        Intent intent = new Intent(mContext, KillApplicationActivity.class);
        startActivity(intent);
    }

    public void switchAPP() throws IOException {

        List<AppTypePoJo> appTypePoJos = new ArrayList<>();
        List<AppPoJo> systemAppPoJos = new ArrayList<>();
        List<AppPoJo> generalAppPoJos = new ArrayList<>();

        List<PackageInfo> packageInfoList = mContext.getPackageManager().getInstalledPackages(GET_ACTIVITIES);

        for (PackageInfo packageInfo : packageInfoList) {
            systemAppPoJos.add(getAppPoJoByPackageInfo(packageInfo));
            if ((packageInfo.applicationInfo.flags & packageInfo.applicationInfo.FLAG_SYSTEM) > 0) {
                continue;
            }
            generalAppPoJos.add(getAppPoJoByPackageInfo(packageInfo));
        }
        AppTypePoJo systemAppTypePoJo = new AppTypePoJo("system", systemAppPoJos);
        AppTypePoJo generalAppTypePoJo = new AppTypePoJo("general", generalAppPoJos);
        appTypePoJos.add(systemAppTypePoJo);
        appTypePoJos.add(generalAppTypePoJo);

        String json = GsonUtils.objectToJson(appTypePoJos);
        System.out.println(json);

//        Runtime runtime = Runtime.getRuntime();
//        runtime.exec("input keyevent " + KeyEvent.KEYCODE_MENU);
//        runtime.exec("input keyevent " + KeyEvent.KEYCODE_MENU);
    }

    private AppPoJo getAppPoJoByPackageInfo(PackageInfo packageInfo) {
        String name = mApplicationContext.getPackageManager().getApplicationLabel(packageInfo.applicationInfo).toString();
        String packageName = packageInfo.packageName;
//        Drawable icon = packageInfo.applicationInfo.loadIcon(mApplicationContext.getPackageManager());
        return new AppPoJo(name, packageName);
    }
}
