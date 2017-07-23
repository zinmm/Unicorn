package com.zin.unicorn.module;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

import com.zin.sideslip.SideslipLayout;
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
    @BindView(R.id.sideslipLayout)
    SideslipLayout sideslipLayout;
    @BindView(R.id.sideslipLayout1)
    SideslipLayout sideslipLayout1;
    @BindView(R.id.sideslipLayout2)
    SideslipLayout sideslipLayout2;
    @BindView(R.id.sideslipLayout3)
    SideslipLayout sideslipLayout3;
    @BindView(R.id.sideslipLayout4)
    SideslipLayout sideslipLayout4;

    @Override
    public void init() {
        http://ooej45z5i.bkt.clouddn.com/test.xlsx
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

//        startActivity(new Intent(mContext, com.zin.ra.nsgam.main.MainActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @OnClick({R.id.tv_request_get, R.id.tv_excel, R.id.tv_cost})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_request_get:
                Navigator.INSTANCE.navigateToProfile(mContext);
                break;

            case R.id.tv_excel:
                Navigator.INSTANCE.navigateToExcel(mContext);
                break;

            case R.id.tv_cost:
                Navigator.INSTANCE.navigateToCost(mContext);
                break;
        }
    }

    @Override
    protected int getRootLayoutId() {
        return R.layout.activity_home;
    }
}
