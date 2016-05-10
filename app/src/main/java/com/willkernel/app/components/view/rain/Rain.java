package com.willkernel.app.components.view.rain;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by willkernel
 * on 2016/5/10.
 */
public class Rain {
    private Random random;
    private int width;
    private int height;

    private float startX;
    private float startY;
    private float stopX;
    private float stopY;
    private float fallSpd;
    private Paint paint = new Paint();
    private int size;
    private int sizeX;
    private int sizeY;
    private boolean randColor = false;
    private int color;

    public Rain(int width, int height, int size, int color, boolean randColor, int rainWidth) {
        this.randColor = randColor;
        this.color = color;
        this.size = size;
        this.width = width;
        this.height = height;
        paint.setColor(color);
        paint.setStrokeWidth(rainWidth);
        random = new Random();
        init();
    }

    private void init() {
        fallSpd = 0.1f + random.nextFloat();
        sizeX = 1 + random.nextInt(size / 2);
        sizeY = 10 + random.nextInt(size);
        startX = random.nextInt(width);
        startY = random.nextInt(height);
        stopX = startX + sizeX;
        stopY = startY + sizeY;
        if (randColor) {
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);

            paint.setARGB(255, r, g, b);
        } else {
            paint.setColor(color);
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    public void fall() {
        startX += sizeX * fallSpd;
        stopX += sizeY * fallSpd;
        startY += sizeX * fallSpd;
        stopY += sizeY * fallSpd;
        if (startY > height) {
            init();
        }
    }

}
