package com.zin.unicorn.module.face;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseMVPActivity;
import com.zin.unicorn.module.face.presenter.FaceDetectorPresenter;
import com.zin.unicorn.module.face.view.FaceDetectorView;
import com.zin.unicorn.widget.FaceView;

import butterknife.BindView;

/**
 * Created by zhujinming on 2017/8/10.
 */

public class FaceDetectorActivity extends
        BaseMVPActivity<FaceDetectorView, FaceDetectorPresenter> implements
        FaceDetectorView {

    @BindView(R.id.sv_face)
    FaceView mSvFace;

    @Override
    protected void initData() {
        mPresenter.initFaceDetector();
    }

    @Override
    protected FaceDetectorPresenter createPresenter() {
        return new FaceDetectorPresenter();
    }

    @Override
    protected int rootLayoutId() {
        return R.layout.activity_face;
    }


    @Override
    public FaceView getSvFace() {
        return mSvFace;
    }
}
