package com.zin.unicorn.util;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created by ZhuJinMing on 17/4/27.
 */

public class OkHttp3Utils {

    private static OkHttpClient mOkHttpClient;

    //设置缓存目录
    private static File cacheDirectory = new File(ContextUtil.getInstance().getApplicationContext().getCacheDir().getAbsolutePath(), "MyCache");
    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);

    /**
     * 获取OkHttpClient对象
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {

        if (null == mOkHttpClient) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .cookieJar(new CookieJar() {
                        @Override
                        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                        }

                        @Override
                        public List<Cookie> loadForRequest(HttpUrl url) {
                            return null;
                        }
                    })
                    //.addInterceptor(new MyIntercepter())
                    //.addNetworkInterceptor(new CookiesInterceptor(MyApplication.getInstance().getApplicationContext()))
                    //设置请求读写的超时时间
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .cache(cache)
                    .build();
        }
        return mOkHttpClient;
    }
}
