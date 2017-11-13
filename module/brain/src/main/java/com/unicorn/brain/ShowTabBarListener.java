package com.unicorn.brain;

import android.widget.LinearLayout;

/**
 * Created by ZhuJinMing on 2017/7/23.
 */
public interface ShowTabBarListener {
    /**
     * 设置 topBar 中 view 的内容
     * @param leftLinearLayout
     * @param centerLinearLayout
     * @param rightLinearLayout
     */
    void setTopBarViewContent(LinearLayout leftLinearLayout, LinearLayout centerLinearLayout, LinearLayout rightLinearLayout);
}
