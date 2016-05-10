package com.willkernel.app.components.utils;

import android.util.Log;


@SuppressWarnings("PointlessBooleanExpression")
public class LogUtil {
    public static final boolean isShowLog = true;

    public static void show(String TAG, String msg) {
        if (!isShowLog) {
            return;
        }
        show(TAG, msg, Log.ERROR);
    }

    public static void show(String TAG, String msg, int level) {
        if (!isShowLog) {
            return;
        }
        switch (level) {
            case Log.VERBOSE:
                Log.v(TAG, msg);
                break;
            case Log.DEBUG:
                Log.d(TAG, msg);
                break;
            case Log.INFO:
                Log.i(TAG, msg);
                break;
            case Log.WARN:
                Log.w(TAG, msg);
                break;
            case Log.ERROR:
                Log.e(TAG, msg);
                break;
            default:
                Log.i(TAG, msg);
                break;
        }
    }
}
