package com.zin.unicorn.module.excel.activity;

import android.widget.ListView;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseMVPActivity;
import com.zin.unicorn.module.excel.presenter.ExcelPresenter;
import com.zin.unicorn.module.excel.view.ExcelView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ZhuJinMing on 2017/7/11.
 */
public class ExcelActivity extends BaseMVPActivity<ExcelView, ExcelPresenter> implements ExcelView {

    @BindView(R.id.listView)
    ListView listView;

    List<String> nullx;

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
        mPresenter.initListView();
//        mPresenter.requestGetExcel();
        mPresenter.requestGetDatas();
    }

    @Override
    public ListView getListView() {
        return listView;
    }
}
