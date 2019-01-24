package com.zin.unicorn.module.demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zin.unicorn.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by ZhuJinMing on 2019/01/18
 */
public class DemoAdapter extends PagerAdapter {

    private List<View> views;

    public DemoAdapter(Context context) {

        views = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            View view = View.inflate(context, R.layout.application_item, null);

            views.add(view);
        }
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position % views.size()));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = views.get(position % views.size());
//        ViewGroup parent = (ViewGroup) view.getParent();
//        //如果当前要显示的 view 有父布局先将父布局移除（view只能有一个父布局）
//        if (parent != null) {
//            parent.removeView(view);
//        }
        container.addView(view);
        return view;
    }
}
