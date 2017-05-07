package com.zin.unicorn.network;

import com.zin.unicorn.pojo.UserPojo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by zhujinming on 17/4/26.
 */

public interface HttpManager {
    @GET("Unicorn//getUser")
    Call<UserPojo> getUserInfo();
}
