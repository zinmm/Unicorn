package com.zin.unicorn.module.demo.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Create by ZhuJinMing on 2019/01/18
 */
public class MyAdapter extends PagerAdapter {

    private boolean isNotify = false;

    public MyAdapter() {
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
        return isViewFromObject(arg0, arg1);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        int count = getCount();
        int new_position = 0;
        if (count != 0)
            new_position = position % getCount();
        destroyItem(container, new_position, object);
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int count = getCount();
        int newPosition = 0;
        if (count != 0) {
            newPosition = position % getCount();
        }
        return instantiateItem(container, newPosition);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (isNotify) {
            isNotify = false;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    @Override
    public void notifyDataSetChanged() {
        isNotify = true;
        super.notifyDataSetChanged();
    }
}
