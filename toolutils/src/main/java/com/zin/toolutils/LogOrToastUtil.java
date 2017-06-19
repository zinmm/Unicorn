package com.zin.toolutils;

import android.content.Context;

import com.zin.toolutils.log.LogcatUtil;

/**
 * Created by zhujinming on 2017/5/27.
 */

public class LogOrToastUtil {

    private static volatile LogOrToastUtil mInstance;

    public static synchronized LogOrToastUtil getInstance() {
        if (mInstance == null) {
            synchronized (LogOrToastUtil.class) {
                mInstance = new LogOrToastUtil();
            }
        }
        return mInstance;
    }

    public void perform(
            Context context,
            String message,
            Object... object) {

        ToastUtils.showToast(context, message);
        LogcatUtil.getInstance().deBug(message, object);
    }
}
