package com.zin.unicorn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zin.unicorn.base.BaseAdapter;

/**
 * Created by ZhuJinMing on 2017/7/11.
 */
public class HomeAdapter extends BaseAdapter {

    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateCommon(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindCommon(RecyclerView.ViewHolder holder, Object item) {

    }
}
