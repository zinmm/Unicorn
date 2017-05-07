package com.zin.unicorn.util;

import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences2.RxSharedPreferences;

/**
 * SharePreferencesUtils
 * Created by zinmm on 17/3/20.
 */
public class SharePreferencesUtils {

    private static volatile RxSharedPreferences sRxSharedPreferences;

    public static RxSharedPreferences getInstance() {
        if (sRxSharedPreferences == null) {
            synchronized (SharePreferencesUtils.class) {
                if (sRxSharedPreferences == null) {
                    sRxSharedPreferences = RxSharedPreferences.create(PreferenceManager.getDefaultSharedPreferences(ContextUtil.getInstance().getContext()));
                }
            }
        }
        return sRxSharedPreferences;
    }

    private SharePreferencesUtils() {

    }
}
