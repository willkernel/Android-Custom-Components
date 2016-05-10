package com.willkernel.app.components.view.volume;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.willkernel.app.components.view.IAnimation;

import java.util.Random;

/**
 * Created by willkernel
 * on 2016/5/10.
 */
public class Volume implements IAnimation {
    private Paint paint = new Paint();
    private Random random = new Random();
    private int maxHeight;
    private int height;
    private int x;
    private int itemWidth;

    private int rectNum;
    private int maxRectNum;
    private int space = 3;

    private int centerY;

    private int musicColor;
    private boolean randColor;

    private final int RECT = 1;
    private final int MRECT = 2;
    private final int MMRECT = 3;
    private int musicType = MMRECT;

    public Volume(int height) {
        this.maxHeight = height;
        paint.setColor(Color.BLUE);
    }

    public Volume(int height, int x, int itemWidth) {
        this.maxHeight = height;
        this.x = x;
        this.itemWidth = itemWidth;
        paint.setColor(Color.BLUE);
        init();
    }

    public Volume(int maxHeight, int x, int itemWidth,  int musicColor, boolean randColor,int musicType) {
        this.maxHeight = maxHeight;
        this.x = x;
        this.itemWidth = itemWidth;
        this.musicType = musicType;
        this.randColor = randColor;
        this.musicColor = musicColor;

        init();
    }

    private void init() {
        if(randColor){
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            paint.setARGB(255, r, g, b);
        }else{
            paint.setColor(musicColor);
        }
        switch (musicType) {
            case MRECT:
                maxRectNum = maxHeight / (itemWidth + space);
                break;
            case MMRECT:
                centerY = maxHeight / 2;
                maxRectNum = centerY / (itemWidth + space);
                break;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        switch (musicType) {
            case RECT:
                canvas.drawRect(x, height, x + itemWidth, maxHeight, paint);
                break;
            case MRECT:
                int num = maxRectNum - rectNum;
                for (int i = 0; i < rectNum; i++) {
                    canvas.drawRect(x, (num + i) * (itemWidth + space), x + itemWidth, (num + i + 1) * (itemWidth + space) - space, paint);
                }
                break;
            case MMRECT:
                paint.setAlpha(255);
                int num1 = maxRectNum - rectNum;
                for (int i = 0; i < rectNum; i++) {
                    canvas.drawRect(x, (num1 + i) * (itemWidth + space), x + itemWidth, (num1 + i + 1) * (itemWidth + space) - space, paint);
                }
                paint.setAlpha(100);
                for (int i = 0; i < rectNum; i++) {
                    canvas.drawRect(x, (maxRectNum + i) * (itemWidth + space), x + itemWidth, (maxRectNum + i + 1) * (itemWidth + space) - space, paint);
                }
                break;
        }

    }

    @Override
    public void move() {
        switch (musicType) {
            case RECT:
                height = random.nextInt(maxHeight);
                break;
            case MRECT:
            case MMRECT:
                height = random.nextInt(maxHeight);
                rectNum = 1+random.nextInt(maxRectNum);
                break;
        }

    }
}
