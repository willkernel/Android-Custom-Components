package com.willkernel.app.components.view.lock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import com.willkernel.app.components.R;
import com.willkernel.app.components.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willkernel
 * on 2016/5/11.
 */
@SuppressWarnings("Convert2Diamond")
public class LockView extends View {
    private Point[][] points = new Point[3][3];
    private boolean init = false;
    private Bitmap bmNormal, bmPress, bmError;
    private int bitmapR;
    private Paint paint;
    private Paint pressPaint;
    private Paint errorPaint;

    private float mouseX, mouseY;
    private boolean isDraw;
    private ArrayList<Point> pointList = new ArrayList<Point>();
    private ArrayList<Integer> passList = new ArrayList<Integer>();

    private onDrawFinishListener listener;
    private String TAG = getClass().getSimpleName();

    public LockView(Context context) {
        super(context);
    }

    public LockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!init) {
            init();
        }
        drawPoints(canvas);
        if (pointList.size() > 0) {
            Point a = pointList.get(0);
            for (int i = 0; i < pointList.size(); i++) {
                Point b = pointList.get(i);
                drawLine(canvas, a, b);
                a = b;
            }
            if (isDraw) {
                drawLine(canvas, a, new Point(mouseX, mouseY));
            }
        }
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pressPaint.setColor(Color.YELLOW);
        pressPaint.setStrokeWidth(5);
        errorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        errorPaint.setColor(Color.RED);
        errorPaint.setStrokeWidth(5);

        int width = getWidth();
        int height = getHeight();
        int offSet = Math.abs(width - height) / 2;
        int offSetX, offSetY;
        int space;
        if (width > height) {
            offSetX = offSet;
            offSetY = 0;
            space = height / 4;
        } else {
            offSetX = 0;
            offSetY = offSet;
            space = width / 4;
        }
        points[0][0] = new Point(space + offSetX, space + offSetY);
        points[0][1] = new Point(2 * space + offSetX, space + offSetY);
        points[0][2] = new Point(3 * space + offSetX, space + offSetY);

        points[1][0] = new Point(space + offSetX, space * 2 + offSetY);
        points[1][1] = new Point(2 * space + offSetX, space * 2 + offSetY);
        points[1][2] = new Point(3 * space + offSetX, space * 2 + offSetY);

        points[2][0] = new Point(space + offSetX, space * 3 + offSetY);
        points[2][1] = new Point(2 * space + offSetX, space * 3 + offSetY);
        points[2][2] = new Point(3 * space + offSetX, space * 3 + offSetY);

        bmNormal = BitmapFactory.decodeResource(getResources(), R.mipmap.normal);
        bmPress = BitmapFactory.decodeResource(getResources(), R.mipmap.press);
        bmError = BitmapFactory.decodeResource(getResources(), R.mipmap.error);
        bitmapR = bmNormal.getHeight() / 2;

        init = true;
    }

    private void drawPoints(Canvas canvas) {
        for (Point[] point : points) {
            for (Point aPoint : point) {
                if (aPoint.state == Point.STATE_NORMAL) {
                    canvas.drawBitmap(bmNormal, aPoint.x - bitmapR, aPoint.y - bitmapR, paint);
                } else if (aPoint.state == Point.STATE_PRESS) {
                    canvas.drawBitmap(bmPress, aPoint.x - bitmapR, aPoint.y - bitmapR, paint);
                } else {
                    canvas.drawBitmap(bmError, aPoint.x - bitmapR, aPoint.y - bitmapR, paint);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);
        velocityTracker.computeCurrentVelocity(1000);
        float xVelocity = velocityTracker.getXVelocity();
        float yVelocity = velocityTracker.getYVelocity();
        LogUtil.show(TAG, "xV=" + xVelocity + " yV=" + yVelocity);
        velocityTracker.clear();
        velocityTracker.recycle();
        mouseX = event.getX();
        mouseY = event.getY();
        int[] p = getSelPoint();
        int i, j;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                resetPoints();
                if (p != null) {
                    isDraw = true;
                    i = p[0];
                    j = p[1];
                    points[i][j].state = Point.STATE_PRESS;
                    pointList.add(points[i][j]);
                    passList.add(i * 3 + j);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDraw) {
                    if (p != null) {
                        i = p[0];
                        j = p[1];
                        if (!pointList.contains(points[i][j])) {
                            points[i][j].state = Point.STATE_PRESS;
                            pointList.add(points[i][j]);
                            passList.add(i * 3 + j);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                boolean valid = false;
                if (listener != null && isDraw) {
                    valid = listener.onDrawFinished(passList);
                }
                if (!valid) {
                    for (Point point : pointList) {
                        point.state = Point.STATE_ERROR;
                    }
                }
                isDraw = false;
                break;
        }
        LogUtil.show(TAG, "pointList=" + pointList.toString() + " passList=" + passList.toString());
        postInvalidate();
        return true;
    }

    private void drawLine(Canvas canvas, Point a, Point b) {
        if (a.state == Point.STATE_PRESS) {
            canvas.drawLine(a.x, a.y, b.x, b.y, pressPaint);
        } else if (a.state == Point.STATE_ERROR) {
            canvas.drawLine(a.x, a.y, b.x, b.y, errorPaint);
        }
    }

    public void resetPoints() {
        pointList.clear();
        passList.clear();
        for (Point[] point : points) {
            for (Point aPoint : point) {
                aPoint.state = Point.STATE_NORMAL;
            }
        }
        postInvalidate();
    }

    private int[] getSelPoint() {
        Point pointMouse = new Point(mouseX, mouseY);
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                if (points[i][j].distance(pointMouse) < bitmapR) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public interface onDrawFinishListener {
        boolean onDrawFinished(List<Integer> passList);
    }

    public void setOnDrawFinishedListener(onDrawFinishListener listener) {
        this.listener = listener;
    }
}
