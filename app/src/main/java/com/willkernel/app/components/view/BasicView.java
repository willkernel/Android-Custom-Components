package com.willkernel.app.components.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by willkernel on 2016/5/10.
 */
public abstract class BasicView extends View {
    private ExecutorService es = Executors.newFixedThreadPool(1);

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
        init();
        drawSub(canvas);
        es.execute(new Runnable() {
            @Override
            public void run() {
                logic();
                postInvalidate();
            }
        });
    }

    /**
     * 获取View的高度在onMeasure之后，onDraw()方法调用在onLayout()之后
     */
    protected abstract void init();

    public abstract void drawSub(Canvas canvas) ;

    public abstract void logic() ;

}
