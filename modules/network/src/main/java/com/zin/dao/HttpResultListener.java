package com.zin.dao;

/**
 * http callback
 * @param <T>
 * Created by ZhuJinMing on 14/3/21.
 */
public interface HttpResultListener<T> {

    void onSuccess(int what, String url, T result);

    void onFailure(int what, String url, int code, String message, Throwable throwable);

}
