package com.zin.unicorn.network;

import com.zin.unicorn.pojo.UserPojo;
import com.zin.unicorn.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhujinming on 17/4/26.
 */
public interface HttpManager {

    @GET("Unicorn//getUser")
    Call<UserPojo> getUserInfo();

    @GET("Diary/ExportDiary")
    Call<String> getExcel(@Query("isMonth") boolean isMonth, @Query("employeeId") int employeeId);
}
