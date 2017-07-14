package com.zin.unicorn.module.excel.presenter;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import com.zin.unicorn.base.BasePresenter;
import com.zin.unicorn.module.excel.view.ExcelView;
import com.zin.unicorn.network.HttpManager;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhujinming on 2017/7/11.
 */
public class ExcelPresenter extends BasePresenter<ExcelView> {

    public void requestGetExcel() {

        mActivity.showProgressBar(true);

        String baseUrl = "http://devtest.countrygarden.com.cn:8866/hermes.apis/api/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HttpManager movieService = retrofit.create(HttpManager.class);
        Call<String> call = movieService.getExcel(true, 1);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                mActivity.hideProgressBar();

                String userPojo = response.body();

                if (userPojo == null) {
                    mActivity.showSnackbar("data error", true);
                    return;
                }

                mActivity.showSnackbar("success");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mActivity.hideProgressBar();

                mActivity.showSnackbar("network error", true);
            }
        });
    }
}
