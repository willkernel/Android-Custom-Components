package com.willkernel.app.components.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.willkernel.app.components.utils.LogUtil;
import com.willkernel.app.components.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by willkernel on 2016/5/10.
 */
@SuppressWarnings("unused")
public class CustomView extends View {
    private final String TAG = getClass().getSimpleName();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect rect = new Rect(100, 120, 200, 200);
    private RectF rectF = new RectF(100, 250, 200, 350);
    private RectF rectF1 = new RectF(250, 250, 350, 350);
    private Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    private int tranSpd;
    private float sweepAng;
    private int circleColor;
    private float radius;
    private float rx = -radius;
    private ExecutorService es = Executors.newFixedThreadPool(1);

    public CustomView(Context context) {
        super(context);
        LogUtil.show(TAG, "super(context)");
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogUtil.show(TAG, "super(context, attrs)");
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        tranSpd = typedArray.getInt(R.styleable.CustomView_tranSpd, 5);
        sweepAng = typedArray.getInt(R.styleable.CustomView_sweepAng, 0);
        circleColor = typedArray.getInt(R.styleable.CustomView_circleColor, Color.YELLOW);
        radius = typedArray.getInt(R.styleable.CustomView_radius, 50);

        typedArray.recycle();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtil.show(TAG, "super(context, attrs, defStyleAttr)");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LogUtil.show(TAG, "super(context, attrs, defStyleAttr, defStyleRes)");
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        LogUtil.show(TAG, "onDraw()", Log.INFO);
        drawSub(canvas);

        es.execute(new Runnable() {
            @Override
            public void run() {
                logic();
                postInvalidate();
            }
        });

    }

    private void logic() {
        if (rx >= getWidth() + radius)
            rx = -radius;
        rx += tranSpd;
        if (sweepAng > 360)
            sweepAng = 0;
        sweepAng++;
    }

    private void drawSub(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setTextSize(32);
        canvas.drawText(TAG, 50, 50, paint);

        paint.setColor(Color.BLUE);
        canvas.drawLine(50, 100, 200, 100, paint);

        paint.setColor(Color.BLUE);
        canvas.drawRect(rect, paint);

        paint.setColor(Color.CYAN);
        canvas.drawRoundRect(rectF, 10, 10, paint);

        circlePaint.setColor(circleColor);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(6);
        canvas.drawCircle(rx, 450, radius, circlePaint);

        canvas.drawArc(rectF1, 0, sweepAng, true, circlePaint);

        canvas.drawBitmap(bitmap, 250, 50, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.show(TAG, "onMeasure()  " + "widthMeasureSpec= " + widthMeasureSpec + " heightMeasureSpec=" + heightMeasureSpec, Log.INFO);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtil.show(TAG, "onKeyDown()", Log.INFO);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        LogUtil.show(TAG, "onFocusChanged() " + " gainFocus=" + gainFocus + " direction=" + direction + " previouslyFocusedRect=" + previouslyFocusedRect, Log.INFO);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.show(TAG, "onTouchEvent()", Log.INFO);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        LogUtil.show(TAG, "onDragEvent()", Log.INFO);
        return super.onDragEvent(event);
    }

    @Override
    public void onHoverChanged(boolean hovered) {
        LogUtil.show(TAG, "onHoverChanged()", Log.INFO);
        super.onHoverChanged(hovered);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LogUtil.show(TAG, "changed=" + changed + " l=" + l + " t=" + t + " r=" + r + " b=" + b);
    }

    @Override
    protected void onDetachedFromWindow() {
        LogUtil.show(TAG,"onDetachedFromWin");
        super.onDetachedFromWindow();
    }

}
