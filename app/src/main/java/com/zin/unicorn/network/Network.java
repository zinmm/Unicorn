package com.zin.unicorn.network;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zinmm on 17/3/28.
 */

public enum Network {

    INSTANCE;

    public void requestGet(String url) {

        BufferedReader bufferedReader = null;

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);

            bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            httpResponse.getEntity().getContent()));
            StringBuilder stringBuffer = new StringBuilder("");
            String line = "";
            String nl = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + nl);
            }
            bufferedReader.close();

            String page = stringBuffer.toString();
            System.out.println(page);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void requestPOST(String url, String... param) {

    }


}
