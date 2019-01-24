package com.zin.unicorn.module.cost.activity;

import android.widget.Button;
import android.widget.EditText;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseMVPActivity;
import com.zin.unicorn.module.cost.presenter.CostUploadPresenter;
import com.zin.unicorn.module.cost.view.CostUploadView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ZhuJinMing on 2017/7/21.
 */
public class CostUploadActivity extends BaseMVPActivity<CostUploadView, CostUploadPresenter> implements CostUploadView {

    @BindView(R.id.et_mark)
    EditText etMark;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.btn_post)
    Button btnPost;

    @Override
    protected void initData() {

    }

    @Override
    protected CostUploadPresenter createPresenter() {
        return new CostUploadPresenter();
    }

    @Override
    protected int rootLayoutId() {
        return R.layout.cost_upload_activity;
    }

    @OnClick(R.id.btn_post)
    public void onViewClicked() {
        mPresenter.uploadFile();
    }

    @Override
    public EditText getMarkEditText() {
        return etMark;
    }

    @Override
    public EditText getNameEditText() {
        return etName;
    }

    @Override
    public EditText getMoneyEditText() {
        return etMoney;
    }

    @Override
    public Button getPostButton() {
        return btnPost;
    }
}
