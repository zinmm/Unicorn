package com.zin.unicorn.module.user.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.bumptech.glide.Glide;
import com.zin.toolutils.LogOrToastUtil;
import com.zin.toolutils.image.ImageUtils;
import com.zin.unicorn.base.BasePresenter;
import com.zin.unicorn.module.user.view.ProfileView;
import com.zin.unicorn.network.HttpManager;
import com.zin.unicorn.pojo.UserPoJo;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zin.unicorn.module.user.activity.ProfileActivity.REQUEST_CODE_PHOTO_ZOOM;
import static com.zin.unicorn.module.user.activity.ProfileActivity.REQUEST_CODE_PICK;
import static com.zin.unicorn.module.user.activity.ProfileActivity.SUCCESS;

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
        Call<UserPoJo> call = movieService.getUserInfo();
        call.enqueue(new Callback<UserPoJo>() {
            @Override
            public void onResponse(Call<UserPoJo> call, Response<UserPoJo> response) {

                mActivity.hideProgressBar();

                UserPoJo userPoJo = response.body();

                if (userPoJo == null) {
                    mActivity.showSnackbar("data error", true);
                    return;
                }

                Glide.with(mContext).load(userPoJo.getAvatar()).centerCrop().into(getView().getIvAvatar());
                getView().getTvId().setText(String.valueOf(userPoJo.getId()));
                getView().getTvGender().setText(userPoJo.getGender());
                getView().getTvUsername().setText(userPoJo.getUserName());
                getView().getTvPassword().setText(userPoJo.getPassword());

                mActivity.showSnackbar("success");
            }

            @Override
            public void onFailure(Call<UserPoJo> call, Throwable t) {
                mActivity.hideProgressBar();

                mActivity.showSnackbar("network error", true);
            }
        });
    }

    public void startPick() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
        // image/jpeg 、 image/png、image/*
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        mActivity.startActivityForResult(pickIntent, REQUEST_CODE_PICK);
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        mActivity.startActivityForResult(intent, REQUEST_CODE_PHOTO_ZOOM);
    }

    public void uploadAvatar(Intent data) {
        Bundle extras = data.getExtras();
        Bitmap avatar = extras.getParcelable("data");
        String avatarFilePath = mAppcationContext.getExternalCacheDir() + "/" + "avatar.jpg";

        if (avatar != null) {

            try {

                ImageUtils.saveFile(avatar, avatarFilePath);

                    requestUploadAvatar(avatarFilePath);
            } catch (Exception e) {}
        }
    }

    private void requestUploadAvatar(String avatarFilePath) {

        File file = new File(avatarFilePath);

        mActivity.showProgressBar(true);

        String baseUrl = "http://devtest.countrygarden.com.cn:8866/hermes.apis";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HttpManager movieService = retrofit.create(HttpManager.class);

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", file.getName(),requestBody);
        Call<ResponseBody> call = movieService.uploadAvatar(fileBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                mActivity.hideProgressBar();

                ResponseBody responseBody = response.body();

                mActivity.showSnackbar("success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mActivity.hideProgressBar();

                mActivity.showSnackbar("network error", true);
            }
        });
    }

    public void activityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQUEST_CODE_PICK:

                if (resultCode == SUCCESS) {

                    Uri uri = data.getData();
                    startPhotoZoom(uri);

                    return;
                }

                showErrorSnackbar("PICK ERROR");
                break;

            case REQUEST_CODE_PHOTO_ZOOM:

                if (resultCode == SUCCESS) {

                    uploadAvatar(data);

                    return;
                }

                showErrorSnackbar("PHOTO_ZOOM ERROR");
                break;

            default:
                LogOrToastUtil.getInstance().perform(mContext, "AVATAR ERROR");
        }
    }

    private void showErrorSnackbar(String errorMessage) {
        mActivity.showSnackbar(errorMessage, true);
    }
}
