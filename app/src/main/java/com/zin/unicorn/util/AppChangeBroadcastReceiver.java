package com.zin.unicorn.util;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by zinmm on 12/5/16.
 */
public class AppChangeBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //接收广播：系统启动完成后运行程序
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

        }
        //接收广播：设备上新安装了一个应用程序包后自动启动新安装应用程序。
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String packageName = intent.getDataString().substring(8);
            System.out.println("---------------" + packageName);
        }
        //接收广播：设备上删除了一个应用程序包。
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            System.out.println("********************************");
            String packageName = intent.getDataString().substring(8);
        }

        if (intent.getAction().equals("android.intent.action.USER_PRESENT")) {
            System.out.println("android.intent.action.USER_PRESENT");

            System.out.println(IsinKeyguardRestrictedInputMode(context) + "123132132213");
        }
    }

    public boolean IsinKeyguardRestrictedInputMode(Context context) {
        KeyguardManager mKeyguardManager;
        mKeyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        boolean isScreenOn = mKeyguardManager.inKeyguardRestrictedInputMode();//

        return isScreenOn;
    }
}
