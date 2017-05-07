package com.zin.unicorn.module.user.presenter;

import com.bumptech.glide.Glide;
import com.zin.unicorn.base.BasePresenter;
import com.zin.unicorn.module.user.view.ProfileView;
import com.zin.unicorn.network.HttpManager;
import com.zin.unicorn.pojo.UserPojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhujinming on 17/5/5.
 */

public class ProfilePresenter extends BasePresenter<ProfileView> {

    public void requestGetUserInfo() {

        mActivity.showProgressBar(true);

        String baseUrl = "http://192.168.1.116:8080/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HttpManager movieService = retrofit.create(HttpManager.class);
        Call<UserPojo> call = movieService.getUserInfo();
        call.enqueue(new Callback<UserPojo>() {
            @Override
            public void onResponse(Call<UserPojo> call, Response<UserPojo> response) {

                mActivity.hideProgressBar();

                UserPojo userPojo = response.body();

                if (userPojo == null) {
                    mActivity.showSnackbar("data error", true);
                    return;
                }

                Glide.with(mContext).load(userPojo.getAvatar()).centerCrop().into(getView().getIvAvatar());
                getView().getTvId().setText(String.valueOf(userPojo.getId()));
                getView().getTvGender().setText(userPojo.getGender());
                getView().getTvUsername().setText(userPojo.getUserName());
                getView().getTvPassword().setText(userPojo.getPassword());

                mActivity.showSnackbar("success");
            }

            @Override
            public void onFailure(Call<UserPojo> call, Throwable t) {
                mActivity.hideProgressBar();

                mActivity.showSnackbar("network error", true);
            }
        });
    }
}
