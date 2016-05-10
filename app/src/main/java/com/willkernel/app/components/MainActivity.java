package com.willkernel.app.components;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.willkernel.app.components.utils.LogUtil;


public class MainActivity extends Activity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(new CustomView(this));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.show(TAG, "onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtil.show(TAG, "onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        LogUtil.show(TAG,"onDestroy()");
        super.onDestroy();
    }
}
