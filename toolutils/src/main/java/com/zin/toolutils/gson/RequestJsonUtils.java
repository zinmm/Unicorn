package com.zin.toolutils.gson;

/**
 * 判断当前请求返回值是否正常
 * Created by zinmm (www.zinmm-dev.com) on 9/11/15.
 */
public class RequestJsonUtils {

    private RequestJsonUtils() {

    }

    public static synchronized <T> T getObj(Class<T> clazz, String json) {
        if (clazz.equals(String.class)) {
            return (T) json;
        } else {
            if (json.startsWith("[")) { // is list
                return (T) GsonUtils.jsonToObjectList(json, clazz);
            } else if (json.startsWith("{")) { // is object
                return GsonUtils.jsonToObject(json, clazz);
            } else {
                return (T) json;
            }
        }
    }
}
