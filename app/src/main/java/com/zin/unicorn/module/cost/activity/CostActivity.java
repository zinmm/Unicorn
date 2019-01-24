package com.zin.unicorn.module.cost.activity;

import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseMVPActivity;
import com.zin.unicorn.module.cost.presenter.CostPresenter;
import com.zin.unicorn.module.cost.view.CostView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ZhuJinMing on 2017/7/21.
 */
public class CostActivity extends BaseMVPActivity<CostView, CostPresenter> implements CostView {

    @BindView(R.id.rv_cost)
    RecyclerView rvCost;
    @BindView(R.id.btn_post)
    Button btnPost;

    @Override
    protected void initData() {
        mPresenter.initRecyclerView();
    }

    @Override
    protected CostPresenter createPresenter() {
        return new CostPresenter();
    }

    @Override
    protected int rootLayoutId() {
        return R.layout.cost_activity;
    }

    @OnClick(R.id.btn_post)
    public void onViewClicked() {
        mPresenter.uploadFile();
    }

    @Override
    public RecyclerView getRvCost() {
        return rvCost;
    }

    @Override
    public Button getBtnPost() {
        return btnPost;
    }
}
