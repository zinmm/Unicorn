package com.zin.unicorn.module;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseActivity;
import com.zin.unicorn.base.Navigator;
import com.zin.unicorn.widget.ObservableScrollView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zin.unicorn.widget.ObservableScrollView.SCROLL_UP;

/**
 * Created by zinmm on 16/12/22.
 */
public class HomePageActivity extends BaseActivity {

    @BindView(R.id.tv_request_get)
    TextView tvRequestGet;
    @BindView(R.id.observableScrollView)
    ObservableScrollView observableScrollView;
    private ActionBar actionBar;

    @Override
    public void init() {

        actionBar = getSupportActionBar();

        observableScrollView.setScrollListener(oritention -> {
            if (oritention == SCROLL_UP) {
                if (actionBar.isShowing()) {
                    actionBar.hide();
                    System.out.println("hide");
                }
            } else {
                if (!actionBar.isShowing()) {
                    actionBar.show();
                    System.out.println("show");
                }
            }
        });
    }

    @OnClick(R.id.tv_request_get)
    public void onClick(View v) {
        Navigator.INSTANCE.navigateToProfile(mContext);
    }

    @Override
    protected int getRootLayoutId() {
        return R.layout.activity_home;
    }
}
