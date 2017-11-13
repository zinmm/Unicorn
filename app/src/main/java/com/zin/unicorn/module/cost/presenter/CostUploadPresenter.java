package com.zin.unicorn.module.cost.presenter;

import com.zin.unicorn.base.BasePresenter;
import com.zin.unicorn.module.cost.view.CostUploadView;
import com.zin.unicorn.network.HttpManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZhuJinMing on 2017/7/21.
 */
public class CostUploadPresenter extends BasePresenter<CostUploadView> {

    public void uploadFile() {

        String mark = getView().getMarkEditText().getText().toString();
        String name = getView().getNameEditText().getText().toString();
        String money = getView().getMoneyEditText().getText().toString();

        String baseUrl = "http://upload.qiniu.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HttpManager movieService = retrofit.create(HttpManager.class);
//        Call<ResponseBody> call = movieService.upload(true, 1);

//        call.enqueue(new Callback<ResponseBody>() {
    }
}
