package com.zin.unicorn.module.console.view;

import android.view.SurfaceView;
import android.widget.Button;

/**
 * Created by zhujinming on 2017/8/18.
 */
public interface ConsoleView {

    SurfaceView getDisplaySurfaceView();

    Button getLeftButton();
    Button getTopButton();
    Button getRightButton();
    Button getBottomButton();

    Button getDButton();
    Button getCButton();
    Button getBButton();
    Button getAButton();
}
