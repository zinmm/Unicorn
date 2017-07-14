package com.zin.toolutils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zin.toolutils.ContextUtil;

import java.lang.ref.WeakReference;

/**
 * Created by zinmm on 12/8/16.
 */
public class NetworkUtil {
    public static boolean isNetToast(Context context) {
        WeakReference<Context> contextWeakReference = new WeakReference<>(context);
        ConnectivityManager manager = (ConnectivityManager) contextWeakReference.get()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
