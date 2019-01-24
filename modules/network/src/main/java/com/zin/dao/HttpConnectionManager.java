package com.zin.dao;

import android.text.TextUtils;
import android.util.Log;

import com.zin.toolutils.log.LogcatUtil;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static com.zin.toolutils.MyTextUtils.countStr;
import static com.zin.dao.HttpConstant.METHOD_GET;
import static com.zin.dao.HttpConstant.METHOD_POST;
import static com.zin.dao.HttpConstant.OTHER_ERROR;

/**
 * Http connection manager.
 *
 * Created by ZhuJinMing on 14/3/20.
 */
public class HttpConnectionManager {

    private TrustManager[] mTrustAllCerts = null;
    private HostnameVerifier mHostnameVerifier = null;

    private static HttpConnectionManager instance;

    public static HttpConnectionManager getInstance() {
        if (instance == null) {
            synchronized (HttpConnectionManager.class) {
                if (instance == null) {
                    instance = new HttpConnectionManager();
                }
            }
        }
        return instance;
    }

    public void connection(int what, String urlStr, String method, Map<String, Object> objectMap,
                           HttpResultListener<String> httpResultListener) {
        new Thread(() ->
                connectionRequest(what, urlStr, method, objectMap, httpResultListener)).start();
    }

    /**
     * 创建连接分发结果
     *`
     * @param what               唯一标示
     * @param urlStr             接口
     * @param method             GET POST
     * @param objectMap          parameter
     * @param httpResultListener 唯一回调
     */
    private void connectionRequest(int what, String urlStr, String method,
                                   Map<String, Object> objectMap,
                                   HttpResultListener<String> httpResultListener) {

        if (TextUtils.isEmpty(urlStr)) {
            Log.e("Network Error: ",
                    "code: 704, urlStr is error! to terminate execution.");
            return;
        }

        if (TextUtils.isEmpty(method)) {
            Log.e("Network Error: ",
                    "code: 706, method is error, have automatic conversion POST.");
            method = METHOD_POST;
        }

        method = method.toLowerCase();
        if (method.contains("g")) {
            method = METHOD_GET;
        } else if (method.contains("p")) {
            method = METHOD_POST;
        }
        method = method.toUpperCase();

        HttpURLConnection httpURLConnection = null;
        InputStream is = null;
        StringBuilder resultData = new StringBuilder();

        try {

            if (method.equals(METHOD_GET)) {

                int urlStrParameterStartIndex = urlStr.indexOf("?") + 1;
                int urlStrIndex = urlStr.length();

                if (urlStr.contains("?")
                        && urlStrParameterStartIndex == urlStrIndex
                        && countStr(urlStr, '?') == 1) {
                    urlStr = urlStr.replace("?", "");
                }

                if (!urlStr.contains("?") && objectMap != null) {

                    StringBuilder urlStrBuilder = new StringBuilder(urlStr);
                    for (Map.Entry<String, Object> parameters : objectMap.entrySet()) {

                        String parameterKey = parameters.getKey();
                        Object parameterValue = parameters.getValue();
                        if (!urlStrBuilder.toString().contains("?")) {
                            urlStrBuilder.append("?");
                        } else {
                            urlStrBuilder.append("&");
                        }
                        urlStrBuilder.append(parameterKey);
                        urlStrBuilder.append("=");
                        urlStrBuilder.append(parameterValue);
                    }
                    urlStr = urlStrBuilder.toString();
                }

                if (urlStr.contains("?") && objectMap != null) {
                    LogcatUtil.getInstance().error(
                            "Network Error: \ncode: 705, tr please have a check url parameter!"
                            , HttpConnectionManager.class);
                }
            }

            URL url = new URL(urlStr);

            httpURLConnection = (HttpURLConnection) url.openConnection();

            boolean useHttps = urlStr.startsWith("https");

            if (useHttps) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
                trustAllHosts(httpsURLConnection);
                httpsURLConnection.getHostnameVerifier();
                httpsURLConnection.setHostnameVerifier(getHostnameVerifier());
            }

            httpURLConnection.setRequestMethod(method);

            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);


            if (method.equals(METHOD_POST)) {

                // 设置运行输出
                httpURLConnection.setDoOutput(true);
                // 设置运行输入
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);

                httpURLConnection.setRequestProperty(
                        "Content-type", "application/x-www-form-urlencoded");

                JSONObject jsonObject = new JSONObject();
                for (Map.Entry<String, Object> parameters : objectMap.entrySet()) {

                    String parameterKey = parameters.getKey();
                    String parameterValue = String.valueOf(parameters.getValue());

                    jsonObject.put(parameterKey, parameterValue);
                }

                OutputStream outputStream = httpURLConnection.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(
                        httpURLConnection.getOutputStream());
                dataOutputStream.writeBytes(jsonObject.toString());
                dataOutputStream.flush();
                outputStream.flush();
            }

            int code = httpURLConnection.getResponseCode();
            //返回200表示连接成功
            if (code == 200) {

                //获取输入流
                is = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader bufferReader = new BufferedReader(inputStreamReader);
                String inputLine;
                while ((inputLine = bufferReader.readLine()) != null) {
                    resultData.append(inputLine);
                }

                // response data
                String responseJson = resultData.toString();

                if (responseJson != null) {
                    onSuccess(httpResultListener, what, urlStr, responseJson);
                }

                inputStreamReader.close();
                bufferReader.close();
            } else {
                failureCallBack(httpResultListener, what, urlStr, code,
                        httpURLConnection.getResponseMessage(), new Error());
            }
        } catch (Throwable e) {
            failureCallBack(httpResultListener, what, urlStr, OTHER_ERROR, e.getMessage(), e);
        } finally {
            try {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException ignored) {
                failureCallBack(httpResultListener, what, urlStr, OTHER_ERROR,
                        ignored.getMessage(), ignored);
            }
        }
    }

    /**
     * 覆盖java默认的证书验证
     */
    private TrustManager[] getTrustAllCerts() {

        if (mTrustAllCerts == null) {
            // 覆盖java默认的证书验证
            mTrustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }

                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }
            }};
        }
        return mTrustAllCerts;
    }

    /**
     * 成功回调
     * 
     * @param httpResultListener 回调类
     * @param what               唯一标示
     * @param urlStr             请求地址
     * @param responseJson       返回数据
     * @return                   是否回调成功
     */
    private boolean onSuccess(HttpResultListener<String> httpResultListener,
                              int what, String urlStr, String responseJson) {
        if (httpResultListener == null) {
            failureCallBack(null, what, urlStr, 1001,
                    "callback is null", new Error());
            return false;
        }
        httpResultListener.onSuccess(what, urlStr, responseJson);
        LogcatUtil.getInstance().json(responseJson);
        return true;
    }

    /**
     * 失败回调
     *
     * @param httpResultListener 回调类
     * @param what               唯一标示
     * @param urlStr             请求地址
     * @param code               返回 code
     * @param errorMessage       错误信息
     * @param throwable          错误
     * @return                   是否回调成功
     */
    private boolean failureCallBack(
            HttpResultListener<String> httpResultListener,
            int what, String urlStr, int code, String errorMessage, Throwable throwable) {

        try {
            if (httpResultListener == null) {
                LogcatUtil.getInstance().error(errorMessage, HttpConnectionManager.class);
                return false;
            }
            httpResultListener.onFailure(what, urlStr, code, errorMessage, throwable);
            LogcatUtil.getInstance().error("code: " + code + "\nmessage: " + errorMessage,
                    HttpConnectionManager.class);
        } catch (Throwable ignored) {
            return false;
        }
        return true;
    }

    /**
     * 设置不验证主机
     * @return HostnameVerifier
     */
    private HostnameVerifier getHostnameVerifier() {
        if (mHostnameVerifier == null) {
            mHostnameVerifier = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
        }
        return mHostnameVerifier;
    }


    /**
     * 信任所有
     *
     * @param httpsURLConnection HttpsURLConnection
     * @return SSLSocketFactory
     */
    private SSLSocketFactory trustAllHosts(HttpsURLConnection httpsURLConnection) {
        SSLSocketFactory oldFactory = httpsURLConnection.getSSLSocketFactory();
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, getTrustAllCerts(), new java.security.SecureRandom());
            SSLSocketFactory newFactory = sc.getSocketFactory();
            httpsURLConnection.setSSLSocketFactory(newFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oldFactory;
    }
}
