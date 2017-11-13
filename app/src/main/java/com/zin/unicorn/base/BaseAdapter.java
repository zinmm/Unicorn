package com.zin.unicorn.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

//    ┏┓       ┏┓
//   ┏┛┻━━━━━━━┛┻┓
//   ┃     ━     ┃
//   ┃   ┳┛ ┗┳   ┃
//   ┃           ┃
//   ┃     ┻     ┃
//   ┗━┓       ┏━┛
//     ┃       ┃   Beast god bless
//     ┃       ┃   Run of one success ！
//     ┃       ┗━━━┓
//     ┃           ┣┓
//     ┃          ┏┛
//     ┗┓┓┏━━━━┳┓┏┛
//      ┃┫┫    ┃┫┫
//      ┗┻┛    ┗┻┛
/**
 * Created by ZhuJinMing on 2017/6/30.
 */
public abstract class BaseAdapter<Z> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int HEADER = 10;
    private final static int COMMON = 15;
    private final static int FOOTER = 20;

    public int position;

    private int itemCount;

    public Context mContext;
    private List<Z> mData;

    public BaseAdapter(Context context) {
        this.mContext = context;
        this.mData = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        this.position = position;

        if (getHeaderCount() <= position) {
            return HEADER;
        } else if (getFooterCount() <= position) {
            return FOOTER;
        } else {
            return COMMON;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateCommon(parent, position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindCommon(holder, mData.get(position));
    }

    public void addHeader(View view) {

    }

    public int getHeaderCount() {
        return 0;
    }

    public int getFooterCount() {
        return 0;
    }

    public int getCommon() {
        return getItemCount() - getHeaderCount() - getFooterCount();
    }

    @Override
    public int getItemCount() {
        return getHeaderCount() + itemCount + getFooterCount();
    }

    public void addItem(Z z) {
        mData.add(z);
        this.itemCount = mData.size();
    }

    public void setData(List<Z> zs) {
        mData = zs;
        this.itemCount = mData.size();
    }

    public Z getData(int position) {

        if (position <= mData.size()) {
            return mData.get(position);
        }
        return null;
    }

    public List<Z> getData() {
        return mData;
    }

    public abstract RecyclerView.ViewHolder onCreateCommon(ViewGroup parent, int viewType);
    public abstract void onBindCommon(RecyclerView.ViewHolder holder, Z item);

}
