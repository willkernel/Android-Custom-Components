package com.willkernel.app.components.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;
import com.willkernel.app.components.utils.LogUtil;

/**
 * Created by willkernel
 * on 2016/5/10.
 */
@SuppressWarnings("unused")
public class CustomLayout extends LinearLayout {
    private final String TAG = getClass().getSimpleName();
    private float mLastX, mLastY;

    public CustomLayout(Context context) {
        super(context);
        LogUtil.show(TAG, "super(context)");
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogUtil.show(TAG, "super(context, attrs)");
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtil.show(TAG, "super(context, attrs, defStyleAttr)");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LogUtil.show(TAG, "super(context, attrs, defStyleAttr, defStyleRes)");
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
        float x = event.getRawX();
        float y = event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float dX = x - mLastX;
                float dY = y - mLastY;
                float translationX = ViewHelper.getTranslationX(this) + dX;
                float translationY = ViewHelper.getTranslationY(this) + dY;
                ViewHelper.setTranslationX(this, translationX);
                ViewHelper.setTranslationY(this, translationY);
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
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
        super.onLayout(changed, l, t, r, b);
    }
}
