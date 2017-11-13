package com.zin.unicorn.pojo.tank;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ZhuJinMing on 2017/8/21.
 */
public class Action {
    public static final int MOVE_LEFT = 0;
    public static final int MOVE_TOP = 1;
    public static final int MOVE_RIGHT = 2;
    public static final int MOVE_BOTTOM = 3;

    @IntDef({MOVE_LEFT, MOVE_TOP, MOVE_RIGHT, MOVE_BOTTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Move{}
}
