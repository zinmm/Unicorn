package com.zin.unicorn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static com.zin.toolutils.density.DensityUtils.dip2px;

/**
 * Created by zhujinming on 2017/8/8.
 */

public class PointView extends View {

    // 拖拽圆的圆心点
    private PointF mDragPoint;
    // 固定圆的圆心点
    private PointF mFixationPoint;
    // 拖拽圆的半径
    private int mDragRadius = 10;
    // 固定圆的半径
    private int mFixationRadius = 7;
    // 固定圆的最小半径
    private int FIXATION_RADIUS_MIN = 3;
    // 固定圆的最大半径
    private int FIXATION_RADIUS_MAX = 7;
    // 用来绘制的画笔
    private Paint mPaint;

    public PointView(Context context) {
        super(context);
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 初始化画笔
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        // 初始化一些距离
        mDragRadius = dip2px(mDragRadius);
        mFixationRadius = dip2px(mFixationRadius);
        FIXATION_RADIUS_MIN = dip2px(FIXATION_RADIUS_MIN);
        FIXATION_RADIUS_MAX = dip2px(FIXATION_RADIUS_MAX);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mDragPoint == null && mFixationPoint == null) {
            return;
        }

        // 1.绘制拖拽圆
        canvas.drawCircle(mDragPoint.x, mDragPoint.y, mDragRadius, mPaint);

        // 计算两个圆之间的距离
        int distance = /*BubbleUtils.getDistance(mDragPoint, mFixationPoint)*/80;

        // 计算固定圆的半径，距离越大圆半径越小
        mFixationRadius = FIXATION_RADIUS_MAX - distance / 14;

        if (mFixationRadius > FIXATION_RADIUS_MIN) {
            // 2.绘制固定圆
            canvas.drawCircle(mFixationPoint.x, mFixationPoint.y, mFixationRadius, mPaint);
        }
    }
}
