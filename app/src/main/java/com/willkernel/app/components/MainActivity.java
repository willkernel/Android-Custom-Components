package com.willkernel.app.components;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import com.willkernel.app.components.utils.LogUtil;
import com.willkernel.app.components.view.lock.LockView;

import java.util.List;


public class MainActivity extends Activity {
    private final String TAG = getClass().getSimpleName();
    private LockView lockView;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(new CustomView(this));
        sp = getSharedPreferences("password", MODE_PRIVATE);

        lockView = (LockView) findViewById(R.id.lockView);
        lockView.setOnDrawFinishedListener(new LockView.onDrawFinishListener() {
            @Override
            public boolean onDrawFinished(List<Integer> passList) {
                if (passList.size() > 3) {
                    StringBuilder sb = new StringBuilder();
                    for (Integer i : passList) {
                        sb.append(i);
                    }
                    if (sp.getString("pwd", "").equals(sb.toString())) {
                        Toast.makeText(MainActivity.this, "密码正确", Toast.LENGTH_LONG).show();
                    } else {
                        saveList(passList);
                    }
                    return true;
                } else {
                    Toast.makeText(MainActivity.this, "密码不能少于3个点", Toast.LENGTH_LONG).show();
                    return false;
                }
            }

            private void saveList(List<Integer> passList) {
                if (passList != null) {
                    StringBuilder sb = new StringBuilder();
                    for (Integer i : passList) {
                        sb.append(i);
                    }
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("pwd", sb.toString());
                    editor.apply();
                    Toast.makeText(MainActivity.this, "密码保存成功", Toast.LENGTH_LONG).show();
                }
            }
        });
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
        LogUtil.show(TAG, "onDestroy()");
        super.onDestroy();
    }
}
