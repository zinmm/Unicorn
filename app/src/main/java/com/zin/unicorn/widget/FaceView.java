package com.zin.unicorn.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.zin.unicorn.R;

/**
 * Created by zhujinming on 2017/8/11.
 */
public class FaceView extends AppCompatImageView {

    public static final String TAG = "FaceView";
    /**
     * 识别图片的宽高
     */
    private int imageWidth, imageHeight;

    private FaceDetector mFaceDetector;

    /**
     * 一次可识别的最大数
     */
    private int maxFace = 3;

    private Bitmap mFaceImage;

    /**
     * 存储识别的脸
     */
    private FaceDetector.Face[] mFaces = new FaceDetector.Face[maxFace];

    /**
     * 真实检测到的人脸数
     */
    private int mFactFaces;

    private float myEyesDistance;

    public FaceView(Context context) {
        super(context);
        init();
    }

    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        BitmapFactory.Options mOptions = new BitmapFactory.Options();
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565;//一定是 565，其他识别不了。
        //拿到需要识别的图片
        mFaceImage = BitmapFactory.decodeResource(getResources(), R.drawable.img, mOptions);
        imageWidth = mFaceImage.getWidth();
        imageHeight = mFaceImage.getHeight();
        //创建FaceDetector
        mFaceDetector = new FaceDetector(imageWidth, imageHeight, maxFace);
        //开始检测，并将检测到的人脸存到mFaces数组中
        mFactFaces = mFaceDetector.findFaces(mFaceImage, mFaces);
        Log.e(TAG, "检测到人脸数:" + mFactFaces);
    }

    /**
     * 对每个人脸进行画框
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        //对每个人脸开始画框
        for (int i = 0; i < mFactFaces; i++) {
            FaceDetector.Face face = mFaces[i];
            PointF mPoint = new PointF();
            face.getMidPoint(mPoint);
            myEyesDistance = face.eyesDistance();//得到人脸中心点和眼间距离参数
            canvas.drawRect(
                    mPoint.x - myEyesDistance,
                    mPoint.y - myEyesDistance,
                    mPoint.x + myEyesDistance,
                    (float) (mPoint.y + myEyesDistance * 1.5),
                    mPaint);
        }
    }
}