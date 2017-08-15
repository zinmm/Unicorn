package com.unicorn.brain;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zin.toolutils.network.NetworkUtil;

import butterknife.ButterKnife;

/**
 * Created by zhujinming on 2017/7/23.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected Context mApplication;
    protected Activity mActivity;

    public boolean isConnected;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mContext = this;
        this.mActivity = this;
        this.mApplication = getApplicationContext();

        setContentView(getRootView());

        ButterKnife.bind(this);

        init();

        isConnected = NetworkUtil.isNetToast(mApplication);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.mContext = null;
        this.mActivity = null;
        this.mApplication = null;
    }

    private View getRootView() {
        View rootView = View.inflate(mContext, R.layout.base_root_container, null);

        ShowTabBarListener showTabBarListener = getShowTabBarListener();
        if (showTabBarListener != null) {
            inflateTopBar(rootView);
            initTopBar(showTabBarListener, rootView);
        }

        setContentViewToRootView(rootView);

        return rootView;
    }

    public void showSnackbar(String content, boolean... isError) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.fl_root), content, Snackbar.LENGTH_SHORT);

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView snackbarTextView = (TextView) snackbarLayout.findViewById(R.id.snackbar_text);

        if (isError.length != 0 && isError[0]) {
            snackbarLayout.setBackgroundColor(0xfff44336);
        }
        snackbarTextView.setGravity(Gravity.CENTER_HORIZONTAL);

        snackbar.show();
    }

    private void setContentViewToRootView(View rootView) {
        FrameLayout contentFrameLayout = rootView.findViewById(R.id.fl_root);

        int rootLayoutId = getRootLayoutId();
        if (getRootLayoutId() == 0) {
            rootLayoutId = R.layout.base_error;
        }
        View contentView = View.inflate(mContext, rootLayoutId, null);
        contentFrameLayout.addView(contentView);
    }

    private void initTopBar(ShowTabBarListener showTabBarListener, View rootView) {

        LinearLayout leftLinearLayout = rootView.findViewById(R.id.ll_left);
        LinearLayout centerLinearLayout = rootView.findViewById(R.id.ll_center);
        LinearLayout rightLinearLayout = rootView.findViewById(R.id.ll_right);

        showTabBarListener.setTopBarViewContent(leftLinearLayout, centerLinearLayout, rightLinearLayout);
    }

    protected void inflateTopBar(View rootView) {
        ViewStub viewStub = rootView.findViewById(R.id.vs_base_topic_bar);
        viewStub.inflate();
    }

    protected ShowTabBarListener getShowTabBarListener() {
        return null;
    }

    protected abstract int getRootLayoutId();

    protected abstract void init();

}
