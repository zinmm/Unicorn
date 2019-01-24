package com.zin.unicorn.module;

import android.annotation.SuppressLint;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseActivity;
import com.zin.unicorn.base.Navigator;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class SplashActivity extends BaseActivity {

    private final static int LOAD_HOME_TIME = 1500;

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

    @SuppressLint("CheckResult")
    private void loadHome() {
        Observable.timer(LOAD_HOME_TIME, MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(aLong -> {
                    Navigator.INSTANCE.navigateToDemo(mContext);
                    finish();
                });
    }

    @Override
    protected int getRootLayoutId() {
        return R.layout.splash_activity;
    }
}
