package com.zin.unicorn.module;

import com.zin.toolutils.gson.GsonUtils;
import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseActivity;
import com.zin.unicorn.base.Navigator;
import com.zin.unicorn.pojo.ad.ActivityPoJo;
import com.zin.unicorn.pojo.ad.NoticePoJo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseActivity {

    private final int LOAD_HOME_TIME = 1500;

    @Override
    public void init() {
//        NoticePoJo noticePoJo = new NoticePoJo();
//        noticePoJo.setFrequencyInteval(1090921);
//        noticePoJo.setPassword("#$%^&*");
//
//        List<ActivityPoJo> activityPoJoList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            ActivityPoJo activityPoJo = new ActivityPoJo();
//            activityPoJo.setContent("它是公的狮子");
//            activityPoJo.setTitle("这是只美丽的狮子");
//            activityPoJo.setIconUrl("http://aaa.jpg");
//            activityPoJo.setRoundedIconUrl("http://aaa.jpg");
//            activityPoJo.setScheme(i + "://scheme");
//            activityPoJoList.add(activityPoJo);
//        }
//
//        noticePoJo.setActivityPoJos(activityPoJoList);
//        String gson = GsonUtils.objectToJson(noticePoJo);
//        System.out.println(gson);

        loadHome();
    }

    private void loadHome() {
        Observable.timer(LOAD_HOME_TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(aLong -> {
                    Navigator.INSTANCE.navigateToHome(mContext);
                    finish();
                });
    }

    @Override
    protected int getRootLayoutId() {
        return R.layout.activity_main;
    }
}
