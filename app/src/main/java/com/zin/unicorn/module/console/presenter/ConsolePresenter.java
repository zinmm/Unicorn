package com.zin.unicorn.module.console.presenter;

import com.zin.unicorn.base.BasePresenter;
import com.zin.unicorn.module.console.view.ConsoleView;
import com.zin.unicorn.pojo.tank.TankPoJo;

/**
 * Created by ZhuJinMing on 2017/8/18.
 */
public class ConsolePresenter extends BasePresenter<ConsoleView> {

    private static final int MOVE_LEFT = 0;
    private static final int MOVE_TOP = 1;
    private static final int MOVE_RIGHT = 2;
    private static final int MOVE_BOTTOM = 3;

    private static final int FUNCTION_A = 0;
    private static final int FUNCTION_B = 1;
    private static final int FUNCTION_C = 2;
    private static final int FUNCTION_D = 3;

    private TankPoJo tankPoJo = new TankPoJo();

    public void initSurfaceView() {
    }

    private void function(int function) {

        switch (function) {

            case FUNCTION_A:

                break;

            case FUNCTION_B:

                break;

            case FUNCTION_C:

                break;

            case FUNCTION_D:

                break;
        }
    }

    private void moveTank(int orientation) {

        switch (orientation) {

            case MOVE_LEFT:

                break;

            case MOVE_TOP:

                break;

            case MOVE_RIGHT:

                break;

            case MOVE_BOTTOM:

                break;
        }
    }

    public void left() {
        moveTank(MOVE_LEFT);
    }

    public void right() {
        moveTank(MOVE_RIGHT);
    }

    public void top() {
        moveTank(MOVE_TOP);
    }

    public void bottom() {
        moveTank(MOVE_BOTTOM);
    }

    public void a() {
        function(FUNCTION_A);
    }

    public void b() {
        function(FUNCTION_B);
    }

    public void c() {
        function(FUNCTION_C);
    }

    public void d() {
        function(FUNCTION_D);
    }
}
