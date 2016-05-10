package com.willkernel.app.components.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by willkernel
 * on 2016/5/10.
 */
@SuppressWarnings("unused")
public abstract class BasicView extends View {
    private boolean running = true;
    private MyThread thread;
    protected long delay = 100;
    public BasicView(Context context) {
        super(context);
    }

    public BasicView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BasicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BasicView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (thread == null) {
            thread = new MyThread();
            thread.start();
        } else {
            drawSub(canvas);
        }

    }

    /**
     * 获取View的高度在onMeasure之后，onDraw()方法调用在onLayout()之后
     */
    protected abstract void init();

    public abstract void drawSub(Canvas canvas);

    public abstract void logic();

    @Override
    protected void onDetachedFromWindow() {
        running = false;
        super.onDetachedFromWindow();
    }

    private class MyThread extends Thread {
        private long workTime;
        @Override
        public void run() {
            init();
            while (running) {
                workTime = System.currentTimeMillis();
                logic();
                postInvalidate();
                workTime = System.currentTimeMillis() - workTime;
                try {
                    if (workTime < delay) {
                        Thread.sleep(delay - workTime);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
