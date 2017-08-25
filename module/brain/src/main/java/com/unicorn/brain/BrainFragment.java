package com.unicorn.brain;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhujinming on 2017/7/23.
 */
public class BrainFragment extends Fragment {

    protected Context mContext;
    protected Context mApplication;
    protected Activity mActivity;
    protected View mView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.mContext = context;
        this.mActivity = (Activity) context;
        this.mApplication = context.getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        this.mContext = null;
        this.mActivity = null;
        this.mApplication = null;
    }

    public View findViewById(int resourcesId) {
        return mView.findViewById(resourcesId);
    }
}
