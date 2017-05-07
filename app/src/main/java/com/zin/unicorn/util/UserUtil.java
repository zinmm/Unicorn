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

    /**
     * user is login
     *
     * @return true: is login, false: no login
     */
    public boolean getUserLoginStatus() {
        return !TextUtils.isEmpty(AccessTokenUtils.getAccessToken());
    }

    public boolean isUserLogin() {
        return getUserInfo() != null;
    }

    @CheckResult
    public UserPojo getUserInfo() {
        if (mUser == null) {
            String userInfo = SharePreferencesUtils.getInstance().getString(Constants.User.USER_INFO).get();
            if (!TextUtils.isEmpty(userInfo)) {
                mUser = GsonUtils.getGsonInstance().fromJson(userInfo, UserPojo.class);
            }
        }
        return mUser;
    }

    public synchronized void saveUserInfo(UserPojo user) {
        SharePreferencesUtils.getInstance().getString(Constants.User.USER_INFO).set(GsonUtils.getGsonInstance().toJson(user));
    }

    public synchronized void updateUserInfo(UserPojo user) {
        Preference<String> userInfoPreference = SharePreferencesUtils.getInstance().getString(Constants.User.USER_INFO);
        userInfoPreference.defaultValue();
        userInfoPreference.set(GsonUtils.getGsonInstance().toJson(user));

        mUser = user;

        EventBus.getDefault().post(UpdateUserInfoEvent.newInstance());
    }

    public synchronized void clearUserInfo() {
        SharePreferencesUtils.getInstance().getString(Constants.User.USER_INFO).delete();
    }
}
