package com.zin.unicorn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zin.toolutils.density.DensityUtils;
import com.zin.toolutils.image.ImageUtils;

/**
 * Created by zhujinming on 17/5/4.
 */

public class TestView extends View {
    Paint paint = new Paint();

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStrokeWidth(100);
        paint.setColor(Color.GREEN); // 设置为红色
        paint.setAntiAlias(true);

        canvas.drawColor(Color.GRAY);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 300, paint);
    }
}
