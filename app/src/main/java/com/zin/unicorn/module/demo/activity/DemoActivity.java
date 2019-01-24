package com.zin.unicorn.module.demo.activity;

import android.widget.LinearLayout;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseMVPActivity;
import com.zin.unicorn.base.LoopViewPager;
import com.zin.unicorn.base.ShowTabBarListener;
import com.zin.dao.HttpConnectionManager;
import com.zin.dao.HttpResultListener;
import com.zin.unicorn.module.demo.presenter.DemoPresenter;
import com.zin.unicorn.module.demo.view.DemoView;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by ZhuJinMing on 2019/01/18
 */
public class DemoActivity extends BaseMVPActivity<DemoView, DemoPresenter>
        implements DemoView, ShowTabBarListener {

    @Override
    protected void initData() {
        String urlStr = "http://www.yunqingugm.com:8081/yd3/mediaconfig";
//        String urlStr = "http://slk.jdkic.com/new/newReport/{channel}/{time}";
//        urlStr = urlStr.replace("{channel}", "cloud_see").
//                replace("{time}", String.valueOf(System.currentTimeMillis()));
//
        Map<String, Object> stringStringMap = new HashMap<>();
//        stringStringMap.put("code", "12332132");
//        stringStringMap.put("i", "212112212112");
        stringStringMap.put("mediaId", "szz-an-kp");
        stringStringMap.put("aid", "1");
        stringStringMap.put("ver", "2");
        stringStringMap.put("pkg", "com.zin.compass");
        stringStringMap.put("imei", "35253108074336");
        stringStringMap.put("ts", "1533644501031");
        stringStringMap.put("os", "ANDROID");
        stringStringMap.put("osv", "8.1.0");
        stringStringMap.put("w", "1080");
        stringStringMap.put("h", "1794");
        stringStringMap.put("model", "Pixel");
        stringStringMap.put("nt", "1");
        stringStringMap.put("mac", "02%3A00%3A00%3A00%3A00%3A00");

        HttpConnectionManager.getInstance().connection(1, urlStr, "get",
                stringStringMap, new HttpResultListener<String>() {
            @Override
            public void onSuccess(int what, String url, String result) {

            }

            @Override
            public void onFailure(int what, String url, int code,
                                  String message, Throwable throwable) {

            }
        });
        mPresenter.init(this);
    }

    @Override
    public void setTopBarViewContent(
            LinearLayout leftLinearLayout,
            LinearLayout centerLinearLayout,
            LinearLayout rightLinearLayout) {

    }

    @Override
    protected DemoPresenter createPresenter() {
        return new DemoPresenter();
    }

    @Override
    protected int rootLayoutId() {
        return R.layout.demo_activity;
    }

    @Override
    public LoopViewPager bannerViewPager() {
        return findViewById(R.id.banner_vp);
    }
}
