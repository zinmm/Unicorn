package com.zin.unicorn.module.excel.presenter;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;

import com.zin.unicorn.base.BasePresenter;
import com.zin.unicorn.module.excel.adapter.ExcelAdapter;
import com.zin.unicorn.module.excel.view.ExcelView;
import com.zin.unicorn.network.HttpManager;
import com.zin.unicorn.pojo.ExcelPoJo;
import com.zin.unicorn.pojo.ad.AdPoJo;
import com.zin.unicorn.util.FileMimeUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.pm.PackageManager.GET_ACTIVITIES;

/**
 * Created by ZhuJinMing on 2017/7/11.
 */
public class ExcelPresenter extends BasePresenter<ExcelView> {

    ExcelAdapter excelAdapter;

    public void requestGetDatas() {

        mActivity.showProgressBar(true);

        new AsyncTask<Void, Long, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {

                String baseUrl = "http://engine.yuyiya.com/";
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                HttpManager movieService = retrofit.create(HttpManager.class);

                AdPoJo adPoJo = new AdPoJo("00329FC8-07EB-4096-B7DE-3CFB620EC83C",
                        "8", "1.0.0", "39.9", "116. 3", "com.sina.weibo, com.eju.shoubo, com.lolo",
                        "iOS", "男", "18-24", "大学本科", "屌丝", "未婚",
                        "单身社交", "高", "高", "游戏类", "微博9:00", null,
                        null, "游戏", null, null, "4G", "高", "29",
                        null, "8", "逆袭", "住宅区", "否", "否",
                        "搬家", "否");

                Call<String> call = movieService.getData("00329FC8-07EB-4096-B7DE-3CFB620EC83C",
                        "8", "39.9", "116. 3", "com.sina.weibo, com.eju.shoubo, com.lolo",
                        "iOS", "男", "18-24", "大学本科", "屌丝", "未婚",
                        "单身社交", "高", "高", "游戏类", "微博9:00", null,
                        null, "游戏", null, null, "4G", "高", "29",
                        null, "8", "逆袭", "住宅区", "否", "否",
                        "搬家", "否");

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        mActivity.hideProgressBar();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String str = response.body();

                                mActivity.showSnackbar(str);
                            }
                        }).start();

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mActivity.hideProgressBar();
                                mActivity.showSnackbar("network error", true);
                            }
                        });
                    }
                });
                return null;
            }
        }.execute();
    }



    public void requestGetExcel() {

        mActivity.showProgressBar(true);

        new AsyncTask<Void, Long, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {

                String baseUrl = "http://devtest.countrygarden.com.cn:8866/hermes.apis/api/";

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                HttpManager movieService = retrofit.create(HttpManager.class);
                Call<ResponseBody> call = movieService.getExcel(true, 1);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        mActivity.hideProgressBar();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                boolean isSuccess = writeResponseBodyToDisk(response.body());

                                if (!isSuccess) {
                                    mActivity.showSnackbar("data error", true);
                                    return;
                                }

                                mActivity.showSnackbar("success");
                            }
                        }).start();

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mActivity.hideProgressBar();

                                mActivity.showSnackbar("network error", true);
                            }
                        });
                    }
                });
                return null;
            }
        }.execute();
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {

        ExcelPoJo excelPoJo = new ExcelPoJo();
        try {
            File futureStudioIconFile = new File(mApplicationContext.getExternalFilesDir(null) + File.separator + "action.xls");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ClipboardManager cm = (ClipboardManager) mApplicationContext.getSystemService(Context.CLIPBOARD_SERVICE);
                    cm.setText(futureStudioIconFile.getPath());
                }
            });

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                excelPoJo.setFileSize(String.valueOf("文件大小：" + fileSize));

                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }

                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                            StrictMode.setVmPolicy(builder.build());
                        }

                        ClipboardManager cm = (ClipboardManager) mApplicationContext.getSystemService(Context.CLIPBOARD_SERVICE);
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        File file = new File(cm.getText().toString());
                        Uri uri = Uri.fromFile(file);

                        intent.setDataAndType(uri, FileMimeUtil.getMIMEType(file));

                        Long time = file.lastModified();
                        Calendar cd = Calendar.getInstance();
                        cd.setTimeInMillis(time);
                        mActivity.showSnackbar(cd.getTime() + "", true);
                        excelPoJo.setCreateTime("创建时间: " + cd.getTime());
                        excelPoJo.setFileName("文间名: xxxooo");
                        excelAdapter.addItem(excelPoJo);
                        excelAdapter.notifyDataSetChanged();

                        System.out.println(cd.getTime());
//                        try {
//                            mActivity.startActivity(intent);
//                        } catch (ActivityNotFoundException e) {
//                            mActivity.showSnackbar("not find excel application", true);
//                        }
                    }
                });

            }
        } catch (IOException e) {
            return false;
        }
    }

    public void initListView() {

        excelAdapter = new ExcelAdapter(mContext);
        getView().getListView().setAdapter(excelAdapter);
    }


}
