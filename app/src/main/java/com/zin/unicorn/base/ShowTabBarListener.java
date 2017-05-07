package com.zin.unicorn.base;

import android.widget.LinearLayout;

/**
 * Created by zinmm on 16/07/11.
 * 专门为了显示topBar而设计的监听
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
