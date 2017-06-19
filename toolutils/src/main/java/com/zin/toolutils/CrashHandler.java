package com.zin.toolutils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类 来接管程序,并记录 发送错误报告.
 */
public class CrashHandler implements UncaughtExceptionHandler {

    private Context mContext;
    private static CrashHandler crashHandler;
    private UncaughtExceptionHandler mDefaultHandler;

    public static CrashHandler getInstance() {
        if (crashHandler == null) {
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }

    /**
     * 需要弹出提示的初始化
     * @param ctx
     */
    public void init(Context ctx) {
        mContext = ctx;
        if (mDefaultHandler != null) {
            mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    /**
     * 不需要弹出提示的初始化
     */
    public void init() {
        if (mDefaultHandler != null) {
            mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {

            // 如果不等待,程序出错的toat没办法弹出来
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return true;
        }
        // final String msg =ex.getCause().getMessage();
        // 使用Toast来显示异常信息
        if (mContext == null) {
            return true;
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
//                Toast.makeText(mContext, R.string.crash_exception, Toast.LENGTH_LONG).show();
                Looper.loop();
            }

        }.start();
        return true;
    }
}