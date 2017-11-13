package com.zin.unicorn.module.cost.presenter;

import com.zin.unicorn.base.BasePresenter;
import com.zin.unicorn.module.cost.adapter.CostAdapter;
import com.zin.unicorn.module.cost.view.CostView;

/**
 * Created by ZhuJinMing on 2017/7/11.
 */
public class CostPresenter extends BasePresenter<CostView> {

    public void initRecyclerView() {
        CostAdapter costAdapter = new CostAdapter(mContext);
        getView().getRvCost().setAdapter(costAdapter);
    }

    public void uploadFile() {

    }
}
