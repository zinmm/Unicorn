package com.zin.dao;

import com.zin.toolutils.file.FileUtils;
import com.zin.toolutils.log.LogcatUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http downloader file
 *
 * Created by ZhuJinMing on 2018/4/12.
 */
public class HttpDownloader {

    private StringBuffer strBuffer = new StringBuffer();
    private BufferedReader bufferReader = null;

    private static HttpDownloader instance;

    public static HttpDownloader getInstance() {
        if (instance == null) {
            synchronized (HttpDownloader.class) {
                if (instance == null) {
                    instance = new HttpDownloader();
                }
            }
        }
        return instance;
    }

    //下载小型的文档文件，返回文档的String字符串
    public String downloadFiles(String urlStr) {
        try {
            InputStream inputStream = getInputStreamFromUrl(urlStr);
            bufferReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferReader.readLine()) != null) {
                strBuffer.append(line + '\n');
            }
        } catch (Exception e) {
            strBuffer.append("something is wrong!!");
            LogcatUtil.getInstance().error("读取数据异常: " + e.getMessage(),
                    HttpDownloader.class);
        } finally {
            try {
                bufferReader.close();
            } catch (Exception e) {
                strBuffer.append("something is wrong!!");
                e.printStackTrace();
            }
        }
        return strBuffer.toString();
    }

    //可以下载任意文件，例如MP3，并把文件存储在制定目录（-1：下载失败，0：下载成功，1：文件已存在）
    public int downloadFiles(String urlStr, String path, String fileName) {
        try {
            FileUtils fileUtils = new FileUtils();
            fileUtils.deleteFile(fileName, path);
            InputStream inputStream = getInputStreamFromUrl(urlStr);
            File resultFile = fileUtils.write2SDFromInput(fileName, path, inputStream);
            if (resultFile == null) {
                return -1;
            }
        } catch (Exception e) {
            LogcatUtil.getInstance().error("读写数据异常: " + e.getMessage(),
                    HttpDownloader.class);
            return -1;
        }
        return 0;
    }

    public InputStream getInputStreamFromUrl(String urlStr) throws IOException {
        //创建一个URL对象
        URL url = new URL(urlStr);
        //创建一个HTTP链接
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        //使用IO流获取数据
        InputStream inputStream = urlConn.getInputStream();
        return inputStream;
    }
}
