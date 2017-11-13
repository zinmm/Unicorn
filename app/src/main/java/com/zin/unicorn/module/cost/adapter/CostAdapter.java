package com.zin.unicorn.module.cost.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseAdapter;
import com.zin.unicorn.pojo.CostPoJo;

/**
 * Created by ZhuJinMing on 2017/7/21.
 */
public class CostAdapter extends BaseAdapter<CostPoJo> {

    public CostAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateCommon(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_cost, null);
        return null;
    }

    @Override
    public void onBindCommon(RecyclerView.ViewHolder holder, CostPoJo item) {

    }
}
