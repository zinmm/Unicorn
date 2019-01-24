package com.zin.unicorn.module.demo.presenter;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.zin.unicorn.base.BasePresenter;
import com.zin.unicorn.base.LoopViewPager;
import com.zin.unicorn.module.demo.adapter.DemoAdapter;
import com.zin.unicorn.module.demo.view.DemoView;

/**
 * Create by ZhuJinMing on 2019/01/18
 */
public class DemoPresenter extends BasePresenter<DemoView> {

    public void init(Context context) {

        DemoAdapter demoAdapter = new DemoAdapter(context);

        LoopViewPager bannerViewPager = getView().bannerViewPager();
        bannerViewPager.setAdapter(demoAdapter);
        demoAdapter.notifyDataSetChanged();
    }
}
