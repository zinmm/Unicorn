package com.zin.unicorn.module.face.presenter;

import android.media.FaceDetector;

import com.zin.unicorn.base.BasePresenter;
import com.zin.unicorn.module.face.view.FaceDetectorView;

/**
 * Created by zhujinming on 2017/8/10.
 */

public class FaceDetectorPresenter extends BasePresenter<FaceDetectorView> {

    private FaceDetector mFaceDetector;

    public void initFaceDetector() {
        mFaceDetector = new FaceDetector(500, 500, 1);

    }
}
