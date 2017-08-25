package com.unicorn.brain;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zin.toolutils.LogOrToastUtil;
import com.zin.toolutils.network.NetWorkChangeBroadcastReceiver;
import com.zin.toolutils.network.NetworkUtil;

import butterknife.ButterKnife;

/**
 * Created by zhujinming on 2017/7/23.
 */
public abstract class BrainActivity extends RxAppCompatActivity {

    protected Context mContext;
    protected Context mApplication;
    protected Activity mActivity;

    public boolean isConnected;

    private ProgressDialog progressDialog;
    private NetWorkChangeBroadcastReceiver netWorkChangeBroadcastReceiver;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mContext = this;
        this.mActivity = this;
        this.mApplication = getApplicationContext();

        this.isConnected = NetworkUtil.isNetToast();

        if (isTopBarTranslucent()) {
            setTopBarTranslucent();
        }

        setContentView(getRootView());

        ButterKnife.bind(this);

        initNetWorkChange();

        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.mContext = null;
        this.mActivity = null;
        this.mApplication = null;

        unregisterReceiver(netWorkChangeBroadcastReceiver);
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

    public void isConnected(boolean isConnected) {
        LogOrToastUtil.getInstance().perform(this, isConnected + "");
        this.isConnected = isConnected;
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

        progressDialog.setCancelable(isCancelable.length != 0 && isCancelable[0]);

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

    protected ShowTabBarListener getShowTabBarListener() {
        return null;
    }

    protected abstract int getRootLayoutId();

    protected abstract void init();

    public boolean isTopBarTranslucent() {
        return false;
    }
}
