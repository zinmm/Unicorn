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
 * Created by ZhuJinMing on 17/4/26.
 */
public interface HttpManager {

    /**
     * 广告位链接接口
     * @param imei Android必填 TelephonyManager.getDeviceId()
     * @param idfa iOS必填idf a
     * @param deviceId 用户唯一标识
     * @param longitude GPS坐标经度
     * @param latitude GPS坐标纬度
     * @param apps 应用包名列表，多个以 , 分隔
     * @param os 操作系统标识: Android/iOS
     * @param gender 性别 : 男，女
     * @param age 年龄 : 0-10,11-17,18-24,25-34,35-44,45+
     * @param education 学历 : 小学及以下，初中，高中，大学本科，硕士及以上
     * @param profession 职业 : 学生，程序员，医生，教师，自由职业
     * @param marriage 婚姻 : 未婚，已婚
     * @param hobby 爱好 : 金融理财类, 游戏类, 购物类, 旅游类, 网络社交, 运动健康类, 教育类, 新闻阅读类, 摄影类, 丽人母婴, 奢侈品消费者
     * @param income 收入水平 : 低，中，高
     * @param consume 消费水平 : 低，中，高
     * @param advertType 用户历史偏好广告 : 贷款类，投资理财类，游戏类，电商类，保险类，家居类，服装类
     * @param appsActivePeriod 应用活跃列表及对应活跃时段 : 包括pkg名称和对应的时段
     * @param viewKeywords 页面关键词
     * @param viewTitle 页面标题
     * @param viewAbstract 页面简介 : 财经，体育，摄影，游戏，汽车，房产
     * @param viewUrl 页面url
     * @param viewContent 页面正文内容 : 部分即可
     * @param nt 网络类型: wif i/3G/4G/2G
     * @param appRate 使用当前app频率 : 低，中，高
     * @param appUsePeriod 使用当前app主要时段 : 以分钟为单位
     * @param appLoginPeriod 当前app已登录时长
     * @param materialPageNum 素材页面访问次数
     * @param userContentKeywords 用户浏览频率最高内容关键词
     * @param locationTag 位置标签 : 商场，学校，酒店，机场，火车站，住宅区，写字楼
     * @param car 是否有车
     * @param house 是否有房 : 否，是，租房
     * @param homeNeeds 是否有家居需求 : 装修，搬家，家政
     * @param child 是否有小孩 : 否，准父母，母婴人群，2-6岁小孩父母，小学生家长，中学生家长
     * @return
     */
    @POST("index/activity")
    Call<String> getData(/*@Query("imei") String imei,*/ @Query("idfa") String idfa,
                               @Query("device_id") String deviceId, @Query("longitude") String longitude,
                               @Query("latitude") String latitude, @Query("apps") String apps,
                               @Query("os") String os, @Query("gender") String gender,
                               @Query("age") String age, @Query("education") String education,
                               @Query("profession") String profession, @Query("marriage") String marriage,
                               @Query("hobby") String hobby, @Query("income") String income,
                               @Query("consume") String consume, @Query("advert_type") String advertType,
                               @Query("apps_active_period") String appsActivePeriod, @Query("view_keywords") String viewKeywords,
                               @Query("view_title") String viewTitle, @Query("view_abstract") String viewAbstract,
                               @Query("view_url") String viewUrl, @Query("view_content") String viewContent,
                               @Query("nt") String nt, @Query("app_rate") String appRate,
                               @Query("app_use_period") String appUsePeriod, @Query("app_login_period") String appLoginPeriod,
                               @Query("material_page_num") String materialPageNum, @Query("user_content_keywords") String userContentKeywords,
                               @Query("location_tag") String locationTag, @Query("car") String car,
                               @Query("house") String house, @Query("home_needs") String homeNeeds,
                               @Query("child") String child
    );

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
