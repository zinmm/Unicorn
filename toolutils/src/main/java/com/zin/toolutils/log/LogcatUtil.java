package com.zin.toolutils.log;

import com.zin.toolutils.BuildConfig;

/**
 * Created by zhujinming on 2017/5/27.
 */
public class LogcatUtil {

    private static volatile LogcatUtil mInstance;
    
    private static final boolean isShowLogcat = BuildConfig.DEBUG;

    public static synchronized LogcatUtil getInstance() {
        if (mInstance == null) {
            synchronized (LogcatUtil.class) {
                mInstance = new LogcatUtil();
            }
        }
        return mInstance;
    }


    public void error(String msg, Object... args) {
        if (isShowLogcat) {
            Logger.e(msg, args);
        }
    }

    public void error(Throwable throwable, String msg, Object... args) {
        if (isShowLogcat) {
            Logger.e(throwable, msg, args);
        }
    }

    public void deBug(Throwable throwable) {
        if (isShowLogcat) {
            throwable.printStackTrace();
        }
    }

    public void deBug(String msg, Object... args) {
        if (isShowLogcat) {
            Logger.d(msg, args);
        }
    }

    public void deBug(Object args) {
        if (isShowLogcat) {
            Logger.d(args);
        }
    }

    public void warn(String msg, Object... args) {
        if (isShowLogcat) {
            Logger.w(msg, args);
        }
    }

    public void info(String msg, Object... args) {
        if (isShowLogcat) {
            Logger.i(msg, args);
        }
    }


    public void verbose(String msg, Object... args) {
        if (isShowLogcat) {
            Logger.v(msg, args);
        }
    }

    //红色突出显示
    public void wtf(String msg, Object... args) {
        if (isShowLogcat) {
            Logger.wtf(msg, args);
        }
    }

    public void json(String json) {
        if (isShowLogcat) {
            Logger.json(json);
        }
    }

    public void xml(String xml) {
        if (isShowLogcat) {
            Logger.xml(xml);
        }
    }
}
