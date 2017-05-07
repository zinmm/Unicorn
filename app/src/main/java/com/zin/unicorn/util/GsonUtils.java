package com.zin.unicorn.util;

import com.google.gson.Gson;

/**
 * gson utils
 * Created by zinmm on 17/3/20.
 */
public class GsonUtils {

    private static Gson sGson;

    private GsonUtils() {

    }

    public static Gson getGsonInstance() {
        if (sGson == null) {
            synchronized (GsonUtils.class) {
                if (sGson == null) {
                    sGson = new Gson();
                }
            }
        }
        return sGson;
    }
}
