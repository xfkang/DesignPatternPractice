package com.itbird.design.strategy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.itbird.design.R;

/**
 * 自定义view
 * Created by itbird on 2022/6/28
 */
public class MyView extends View {
    private static final String TAG = MyView.class.getSimpleName();
    /**
     * 画笔
     */
    Paint paint;
    /**
     * 圆半径
     */
    public static final float RADIUS = 50f;
    /**
     * 当前坐标点、开始坐标点、结束坐标点
     */
    PointF currentPoint = null;
    PointF startPoint;
    PointF endPoint;

    public MyView(Context context) {
        super(context);
        init(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public PointF getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(PointF startPoint) {
        this.startPoint = startPoint;
        this.currentPoint = startPoint;
    }

    public PointF getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(PointF endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs) {
        if (paint == null) {
            paint = new Paint();
            paint.setColor(getResources().getColor(android.R.color.holo_red_dark));
        }
        if (startPoint == null) {
            startPoint = new PointF();
        }
        if (endPoint == null) {
            endPoint = new PointF();
        }
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
            startPoint.x = typedArray.getFloat(R.styleable.MyView_startx, RADIUS);
            startPoint.y = typedArray.getFloat(R.styleable.MyView_starty, RADIUS);
            startPoint.x = typedArray.getFloat(R.styleable.MyView_endx, getWidth() - RADIUS);
            startPoint.y = typedArray.getFloat(R.styleable.MyView_endy, getHeight() - RADIUS);
            typedArray.recycle();
            Log.d(TAG, "obtainStyledAttributes");
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.x;
        float y = currentPoint.y;
        canvas.drawCircle(x, y, RADIUS, paint);
    }

    public void updatePoint(PointF pointF) {
        currentPoint = pointF;
        invalidate();
    }
}
