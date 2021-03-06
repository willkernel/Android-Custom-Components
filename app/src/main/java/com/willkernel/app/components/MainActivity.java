package com.willkernel.app.components;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.willkernel.app.components.utils.LogUtil;
import com.willkernel.app.components.view.CustomLayout;
import com.willkernel.app.components.view.lock.LockView;


public class MainActivity extends Activity {
    private final String TAG = getClass().getSimpleName();
    private LockView lockView;
    private SharedPreferences sp;
    private TextView tv;
    private Context context;
    private ImageView iv_CustomLayout;
    private boolean clicked;
    private CustomLayout customLayout;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      setContentView(new CustomView(this));

        context = MainActivity.this;

        setLayoutParams();
//        lockView();
    }

    private void setLayoutParams() {
        customLayout = (CustomLayout) findViewById(R.id.custom_Layout);
        iv_CustomLayout = (ImageView) findViewById(R.id.iv_CustomLayout);
        iv_CustomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Params", Toast.LENGTH_LONG).show();
                MarginLayoutParams params = (MarginLayoutParams) iv_CustomLayout.getLayoutParams();
                if (i > 300)
                    i = 0;
                ObjectAnimator.ofFloat(customLayout, "translationY", i, i += 100).setDuration(500).start();
                if (!clicked) {
                    params.width += 100;
                    params.leftMargin += 100;
                    iv_CustomLayout.requestLayout();
                    clicked = true;
                } else {
                    params.width -= 100;
                    params.leftMargin -= 100;
                    iv_CustomLayout.requestLayout();
                    clicked = false;
                }
            }
        });
    }


//    private void lockView() {
//        sp = getSharedPreferences("password", MODE_PRIVATE);
//
//        lockView = (LockView) findViewById(R.id.lockView);
//        lockView.setOnDrawFinishedListener(new LockView.onDrawFinishListener() {
//            @Override
//            public boolean onDrawFinished(List<Integer> passList) {
//                if (passList.size() > 3) {
//                    StringBuilder sb = new StringBuilder();
//                    for (Integer i : passList) {
//                        sb.append(i);
//                    }
//                    if (sp.getString("pwd", "").equals(sb.toString())) {
//                        Toast.makeText(MainActivity.this, "密码正确", Toast.LENGTH_LONG).show();
//                    } else {
//                        saveList(passList);
//                    }
//                    return true;
//                } else {
//                    Toast.makeText(MainActivity.this, "密码不能少于3个点", Toast.LENGTH_LONG).show();
//                    return false;
//                }
//            }
//
//            private void saveList(List<Integer> passList) {
//                if (passList != null) {
//                    StringBuilder sb = new StringBuilder();
//                    for (Integer i : passList) {
//                        sb.append(i);
//                    }
//                    SharedPreferences.Editor editor = sp.edit();
//                    editor.putString("pwd", sb.toString());
//                    editor.apply();
//                    Toast.makeText(MainActivity.this, "密码保存成功", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }

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
        LogUtil.show(TAG, "onDestroy()");
        super.onDestroy();
    }
}
