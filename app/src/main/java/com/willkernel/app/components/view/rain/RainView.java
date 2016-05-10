package com.willkernel.app.components.view.rain;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.willkernel.app.components.R;
import com.willkernel.app.components.view.BasicView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willkernel
 * on 2016/5/10.
 */
public class RainView extends BasicView {
    private int rainNum;
    private int rainWidth;
    private int size;
    private int rainColor;
    private boolean randColor;
    private List<Rain> rains;

    public RainView(Context context) {
        super(context);
    }

    public RainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RainView);

        rainNum = ta.getInteger(R.styleable.RainView_rainNum, 80);
        size = ta.getInteger(R.styleable.RainView_size, 10);
        rainColor = ta.getInteger(R.styleable.RainView_rainColor, 0xffffffff);
        randColor = ta.getBoolean(R.styleable.RainView_randColor, false);
        rainWidth = ta.getInt(R.styleable.RainView_rainWidth, 3);
        ta.recycle();
    }

    @Override
    protected void init() {
        rains = new ArrayList<>();
        for (int i = 0; i < rainNum; i++) {
            Rain rain = new Rain(getWidth(), getHeight(), size, rainColor, randColor, rainWidth);
            rains.add(rain);
        }
    }


    @Override
    public void drawSub(Canvas canvas) {
        for (Rain r : rains) {
            r.draw(canvas);
        }
    }

    @Override
    public void logic() {
        for (Rain r : rains) {
            r.fall();
        }
    }

}
