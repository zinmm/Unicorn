package com.zin.unicorn.util;

import com.zin.unicorn.pojo.UserPoJo;

/**
 * Created by zinmm on 17/3/20.
 */
public class UserUtil {

    private volatile UserPoJo mUser;

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
