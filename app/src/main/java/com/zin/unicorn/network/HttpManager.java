package com.zin.unicorn.network;

import com.zin.unicorn.pojo.UserPoJo;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * Created by zhujinming on 17/4/26.
 */
public interface HttpManager {

    @GET("Unicorn//getUser")
    Call<UserPoJo> getUserInfo();

    @Streaming
    @GET("Diary/ExportDiary")
    Call<ResponseBody> getExcel(@Query("isMonth") boolean isMonth, @Query("employeeId") int employeeId);

    @Multipart
    @POST("multipart/form-data")
    Call<ResponseBody> upload(@Part("key") String key, @Part("token") String tokeng,
                              @Part("crc32") String crc32, @Part("accept") String accept,
                              @Part MultipartBody.Part file);

    @Multipart
    @POST("/api/Account/UpdateMyAvatar")
    Call<ResponseBody> uploadAvatar(@Part("Authorization") MultipartBody.Part file);
}
