package com.zin.unicorn.network;

import com.zin.unicorn.pojo.UserPojo;
import com.zin.unicorn.util.Constants;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * Created by zhujinming on 17/4/26.
 */
public interface HttpManager {

    @GET("Unicorn//getUser")
    Call<UserPojo> getUserInfo();

    @Streaming
    @GET("Diary/ExportDiary")
    Call<ResponseBody> getExcel(@Query("isMonth") boolean isMonth, @Query("employeeId") int employeeId);
}
