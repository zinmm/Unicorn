package com.zin.unicorn.module.home.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zin.toolutils.ToastUtils;
import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseMVPActivity;
import com.zin.unicorn.module.home.presenter.HomePresenter;
import com.zin.unicorn.module.home.view.HomeView;
import com.zin.unicorn.widget.ObservableScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zin.unicorn.widget.ObservableScrollView.SCROLL_UP;

/**
 * Created by zinmm on 16/12/22.
 */
public class HomePageActivity extends BaseMVPActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.observableScrollView)
    ObservableScrollView observableScrollView;
    @BindView(R.id.all_package)
    TextView allPackage;
    private ActionBar actionBar;
    @BindView(R.id.checkbox)
    CheckBox checkBox;

//    @OnClick({R.id.tv_request_get, R.id.tv_excel, R.id.tv_cost, R.id.tv_face})
//    public void onClick(View v) {
//
//        switch (v.getId()) {
//
//            case R.id.tv_request_get:
//                Navigator.INSTANCE.navigateToProfile(mContext);
//                break;
//
//            case R.id.tv_excel:
//                Navigator.INSTANCE.navigateToExcel(mContext);
//                break;
//
//            case R.id.tv_cost:
//                Navigator.INSTANCE.navigateToCost(mContext);
//                break;
//
//            case R.id.tv_face:
//                Navigator.INSTANCE.navigateToFace(mContext);
//                break;
//        }
//    }

    @Override
    protected void initData() {

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
        checkBox.setChecked(true);
        observableScrollView.setClickable(false);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> ToastUtils.showToast(mContext, isChecked + ""));
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int rootLayoutId() {
        return R.layout.activity_home;
    }

    @OnClick(R.id.all_package)
    public void onViewClicked() {
        mPresenter.startAllApp();
    }

    @Override
    public TextView allPackage() {
        return allPackage;
    }
}
