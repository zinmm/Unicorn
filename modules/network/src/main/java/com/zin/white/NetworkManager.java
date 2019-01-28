package com.zin.white;

import android.os.Handler;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class NetworkManager extends Handler {

    private final static String METHOD_GET = "get";
    private final static String METHOD_POST = "post";

    private void init(String domain, String method, Map<String, Object> objectMap, HttpResultCallback<String> httpResultCallback) {

        method = method.toLowerCase();
        method = !TextUtils.isEmpty(method) ?
                method.contains(METHOD_GET) ?
                        METHOD_GET :
                        method.contains(METHOD_POST) ?
                                METHOD_POST : METHOD_GET : METHOD_GET;
        if (!TextUtils.isEmpty(method)) {
            if (method.contains(METHOD_GET)) {

            } else {
                if (method.contains(METHOD_POST)) {

                }
            }
        }
        method = method.toUpperCase();

        HttpURLConnection conn = null;
        InputStream is = null;
        StringBuilder resultData = new StringBuilder();
        try {

            URL url = new URL(domain);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);

            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            for (Map.Entry<String, Object> parameters : objectMap.entrySet()) {

                String parameterKey = parameters.getKey();
                String parameterValue = String.valueOf(parameters.getValue());
                conn.addRequestProperty(parameterKey, parameterValue);
            }

            int code = conn.getResponseCode();
            //返回200表示连接成功
            if (code == 200) {

                //获取输入流
                is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader bufferReader = new BufferedReader(isr);
                String inputLine;
                while ((inputLine = bufferReader.readLine()) != null) {
                    resultData.append(inputLine);
                }

                String responseJson = resultData.toString();
                httpResultCallback.onSuccess(responseJson);

                isr.close();
                bufferReader.close();
            } else {
                httpResultCallback.onFailure(code, conn.getResponseMessage());
            }

        } catch (IOException e) {
            httpResultCallback.onFailure(831091, e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
