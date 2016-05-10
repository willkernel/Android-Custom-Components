package com.willkernel.app.components.view.volume;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import com.willkernel.app.components.R;
import com.willkernel.app.components.view.BasicView;

import java.util.ArrayList;

/**
 * Created by willkernel
 * on 2016/5/10.
 */
public class VolumeView extends BasicView {
    private ArrayList<Volume> volumes;
    private int volumeNum = 10;
    private int itemWidth = 20;
    private int musicColor;
    private boolean randColor;
    private int musicType;

    public VolumeView(Context context) {
        super(context);
    }

    public VolumeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.VolumeView);

        volumeNum = ta.getInteger(R.styleable.VolumeView_volumeNum, 10);
        itemWidth = ta.getDimensionPixelSize(R.styleable.VolumeView_itemWidth, 20);
        musicColor = ta.getColor(R.styleable.VolumeView_musicColor, Color.RED);
        randColor = ta.getBoolean(R.styleable.VolumeView_randVolumeColor, false);
        musicType = ta.getInteger(R.styleable.VolumeView_musicType, 3);

        ta.recycle();
    }

    @Override
    protected void init() {
        volumes = new ArrayList<>();
        delay = 200;
        for (int i = 0; i < volumeNum; i++) {
            volumes.add(new Volume(getHeight(), i * getWidth() / volumeNum, itemWidth, musicColor, randColor, musicType));
        }
    }

    @Override
    public void drawSub(Canvas canvas) {
        for (Volume v : volumes) {
            v.draw(canvas);
        }
    }

    @Override
    public void logic() {
        for (Volume v : volumes) {
            v.move();
        }
    }
}
