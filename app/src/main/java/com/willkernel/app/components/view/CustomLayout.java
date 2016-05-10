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

import com.willkernel.app.components.utils.LogUtil;

/**
 * Created by willkernel on 2016/5/10.
 *
 */
@SuppressWarnings("unused")
public class CustomLayout extends LinearLayout {
    private final String TAG = getClass().getSimpleName();

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
        super.onLayout(changed, l, t, r, b);
    }
}
