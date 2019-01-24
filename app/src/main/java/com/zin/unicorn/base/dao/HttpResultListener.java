package com.zin.unicorn.base.dao;

public interface HttpResultListener<T> {

    void onSuccess(int what, String url, T result);

    void onFailure(int what, String url, int code, String message, Throwable throwable);

}
