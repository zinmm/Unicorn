package com.zin.unicorn.module.excel.activity;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseMVPActivity;
import com.zin.unicorn.module.excel.presenter.ExcelPresenter;
import com.zin.unicorn.module.excel.view.ExcelView;

/**
 * Created by zhujinming on 2017/7/11.
 */
public class ExcelActivity extends BaseMVPActivity<ExcelView, ExcelPresenter> implements ExcelView {

    @Override
    protected ExcelPresenter createPresenter() {
        return new ExcelPresenter();
    }

    @Override
    protected int rootLayoutId() {
        return R.layout.activity_excel;
    }

    @Override
    protected void initData() {
        mPresenter.requestGetExcel();
    }
}
