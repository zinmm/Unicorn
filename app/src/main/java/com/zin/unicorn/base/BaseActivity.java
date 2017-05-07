package com.zin.unicorn.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zin.unicorn.R;
import com.zin.unicorn.util.NetWorkChangeBroadcastReceiver;
import com.zin.unicorn.util.NetworkUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zinmm on 12/14/16.
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    protected Context mContext;
    protected Context mApplicationContext;

    public boolean isConnected;

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private ProgressDialog progressDialog;
    private NetWorkChangeBroadcastReceiver netWorkChangeBroadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isTopBarTranslucent()) {
            setTopBarTranslucent();
        }

        mContext = this;
        mApplicationContext = this.getApplicationContext();
        isConnected = NetworkUtil.isNetToast();

        setContentView(getRootView());
        ButterKnife.bind(this);

        initNetWorkChange();

        init();
    }

    public abstract void init();

    private void initNetWorkChange() {

        netWorkChangeBroadcastReceiver = new NetWorkChangeBroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                isConnected(!(intent.getIntExtra(NetWorkChangeBroadcastReceiver.NET_TYPE, 0) == 0));
            }
        };

        IntentFilter filter = new IntentFilter(NetWorkChangeBroadcastReceiver.NET_CHANGE);
        registerReceiver(netWorkChangeBroadcastReceiver, filter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        isConnected = NetworkUtil.isNetToast();
    }

    public void setTopBarTranslucent() {
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public void showSnackbar(String content, boolean... isError) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.root), content, Snackbar.LENGTH_SHORT);

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView snackbarTextView = (TextView) snackbarLayout.findViewById(R.id.snackbar_text);

        if (isError.length != 0 && isError[0]) {
            snackbarLayout.setBackgroundColor(0xfff44336);
        }
        snackbarTextView.setGravity(Gravity.CENTER_HORIZONTAL);

        snackbar.show();
    }

    private View getRootView() {
        View baseView = View.inflate(mContext, R.layout.base_activity_container, null);

        ShowTabBarListener showTabBarListener = getShowTabBarListener();
        if (showTabBarListener != null) {
            inflateTopBar(baseView);
            initTopBar(showTabBarListener, baseView);
        }

        setContentViewToRootView(baseView);

        return baseView;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        View rootView = LayoutInflater.from(mContext).inflate(R.layout.base_activity_container, null, false);
        View subRootView = LayoutInflater.from(mContext).inflate(layoutResID, null, false);
        ((FrameLayout) rootView.findViewById(R.id.root)).addView(subRootView);

    }

    @Override
    protected void onDestroy() {

        hideProgressBar();

        super.onDestroy();

        mContext = null;
        mApplicationContext = null;

        unregisterReceiver(netWorkChangeBroadcastReceiver);

        coordinatorLayout = null;
        progressDialog = null;
    }

    protected ShowTabBarListener getShowTabBarListener() {
        return null;
    }

    private void inflateTopBar(View baseView) {
        ViewStub topBarViewStub = (ViewStub) baseView.findViewById(R.id.topBarViewStub);
        topBarViewStub.inflate();
    }

    private void initTopBar(ShowTabBarListener showTabBarListener, View baseView) {
        LinearLayout topBarLinearLayout = (LinearLayout) baseView.findViewById(R.id.topBarLinearLayout);
        View topbarLineView = baseView.findViewById(R.id.topbarLineView);

        LinearLayout leftLinearLayout = (LinearLayout) baseView.findViewById(R.id.leftLinearLayout);
        LinearLayout centerLinearLayout = (LinearLayout) baseView.findViewById(R.id.centerLinearLayout);
        LinearLayout rightLinearLayout = (LinearLayout) baseView.findViewById(R.id.rightLinearLayout);

        if (topbarBackground() != R.color.white) {
            topBarLinearLayout.setBackgroundColor(getResources().getColor(topbarBackground()));
        }
        topbarLineView.setVisibility(isShowTopbarLine() ? View.VISIBLE : View.GONE);

        showTabBarListener.setTopBarViewContent(leftLinearLayout, centerLinearLayout, rightLinearLayout);
    }

    private void setContentViewToRootView(View baseView) {
        FrameLayout contentFrameLayout = (FrameLayout) baseView.findViewById(R.id.root);

        int contentLayoutId = getRootLayoutId();
        View contentView = View.inflate(mContext, contentLayoutId, null);
        contentFrameLayout.addView(contentView);
    }

    protected abstract int getRootLayoutId();

    public void isConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public void refresh(View view) {

    }

    public boolean isProgressBarShowing() {
        return progressDialog != null && progressDialog.isShowing();
    }

    public void hideProgressBar() {
        if (isProgressBarShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * 显示loading
     *
     * @param isCancelable 是否不能取消
     */
    public void showProgressBar(boolean... isCancelable) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("请稍后");
            progressDialog.setIndeterminate(false);
        }

        progressDialog.setCancelable(!(isCancelable.length != 0 && isCancelable[0]));

        if (progressDialog != null && !isProgressBarShowing() && !isFinishing()) {
            progressDialog.show();
        }
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    protected
    @ColorRes
    int topbarBackground() {
        return R.color.white;
    }

    protected boolean isShowTopbarLine() {
        return true;
    }

    protected boolean isTopBarTranslucent() {
        return false;
    }
}
