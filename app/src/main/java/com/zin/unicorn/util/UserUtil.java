package com.zin.unicorn.util;

import android.support.annotation.CheckResult;
import android.text.TextUtils;

import com.f2prateek.rx.preferences2.Preference;
import com.zin.unicorn.event.UpdateUserInfoEvent;
import com.zin.unicorn.pojo.UserPojo;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zinmm on 17/3/20.
 */
public class UserUtil {

    private volatile UserPojo mUser;

    private static volatile UserUtil sUserUtils;

    public static UserUtil getInstance() {
        if (sUserUtils == null) {
            synchronized (UserUtil.class) {
                if (sUserUtils == null) {
                    sUserUtils = new UserUtil();
                }
            }
        }
        return sUserUtils;
    }
}
