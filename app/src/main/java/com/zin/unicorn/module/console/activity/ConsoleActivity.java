package com.zin.unicorn.module.console.activity;

import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseMVPActivity;
import com.zin.unicorn.module.console.presenter.ConsolePresenter;
import com.zin.unicorn.module.console.view.ConsoleView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhujinming on 2017/8/18.
 */
public class ConsoleActivity extends
        BaseMVPActivity<ConsoleView, ConsolePresenter> implements ConsoleView {

    @BindView(R.id.sv_display)
    SurfaceView svDisplay;
    @BindView(R.id.btn_left)
    Button btnLeft;
    @BindView(R.id.btn_top)
    Button btnTop;
    @BindView(R.id.btn_right)
    Button btnRight;
    @BindView(R.id.btn_bottom)
    Button btnBottom;
    @BindView(R.id.btn_d)
    Button btnD;
    @BindView(R.id.btn_c)
    Button btnC;
    @BindView(R.id.btn_b)
    Button btnB;
    @BindView(R.id.btn_a)
    Button btnA;

    @Override
    protected void initData() {
        mPresenter.initSurfaceView();
    }

    @OnClick({R.id.btn_left, R.id.btn_top, R.id.btn_right,
            R.id.btn_bottom, R.id.btn_d, R.id.btn_c,
            R.id.btn_b, R.id.btn_a})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.btn_left:
                mPresenter.left();
                break;

            case R.id.btn_top:
                mPresenter.top();
                break;

            case R.id.btn_right:
                mPresenter.right();
                break;

            case R.id.btn_bottom:
                mPresenter.bottom();
                break;

            case R.id.btn_d:
                mPresenter.d();
                break;

            case R.id.btn_c:
                mPresenter.c();
                break;

            case R.id.btn_b:
                mPresenter.b();
                break;

            case R.id.btn_a:
                mPresenter.a();
                break;

            default:
                break;
        }
    }

    @Override
    protected ConsolePresenter createPresenter() {
        return new ConsolePresenter();
    }

    @Override
    protected int rootLayoutId() {
        return R.layout.activity_console;
    }

    @Override
    public SurfaceView getDisplaySurfaceView() {
        return svDisplay;
    }

    @Override
    public Button getLeftButton() {
        return btnLeft;
    }

    @Override
    public Button getTopButton() {
        return btnTop;
    }

    @Override
    public Button getRightButton() {
        return btnRight;
    }

    @Override
    public Button getBottomButton() {
        return btnBottom;
    }

    @Override
    public Button getDButton() {
        return btnD;
    }

    @Override
    public Button getCButton() {
        return btnC;
    }

    @Override
    public Button getBButton() {
        return btnB;
    }

    @Override
    public Button getAButton() {
        return btnA;
    }
}
