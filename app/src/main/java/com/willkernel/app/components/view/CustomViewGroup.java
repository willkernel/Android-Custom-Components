package com.willkernel.app.components.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.willkernel.app.components.utils.LogUtil;

/**
 * Created by willkernel
 * on 2016/5/10.
 */
@SuppressWarnings("unused")
public class CustomViewGroup extends ViewGroup {
    private final String TAG = getClass().getSimpleName();
    private Scroller scroller;
    private GestureDetector gestureDetector;

    public CustomViewGroup(Context context) {
        super(context);
        LogUtil.show(TAG, "super(context)");
        init(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogUtil.show(TAG, "super(context, attrs)");
        init(context);
    }

    private void init(Context context) {
        scroller = new Scroller(context);

        gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
        //解决长按屏幕后无法拖动的现象
        gestureDetector.setIsLongpressEnabled(false);
        gestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                //与onDoubleTap不能同时触发
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtil.show(TAG, "super(context, attrs, defStyleAttr)");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr,
                           int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LogUtil.show(TAG, "super(context, attrs, defStyleAttr, defStyleRes)");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.show(TAG, "onDraw()", Log.INFO);
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
    protected void onFocusChanged(boolean gainFocus, int direction, Rect
            previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        LogUtil.show(TAG, "onFocusChanged() " + " gainFocus=" + gainFocus + " direction=" + direction + " previouslyFocusedRect=" + previouslyFocusedRect, Log.INFO);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.show(TAG, "onTouchEvent()", Log.INFO);
        return gestureDetector.onTouchEvent(event);
//        return super.onTouchEvent(event);
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

    private void smoothScrollTo(int desX, int desY) {
        int scrollX = getScrollX();
        int delta = desX - scrollX;
        scroller.startScroll(scrollX, 0, desX, desY, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }
}
