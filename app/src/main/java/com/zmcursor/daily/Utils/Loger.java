package com.zmcursor.daily.Utils;

import android.util.Log;

/**
 * Created by ZMcursor on 2017/7/19 0019.
 */

public class Loger {

    public static void UI(String TAG, String text) {
        Log.e("MyLog.UI ;" + TAG + " -> ", text);
    }

    public static void Util(String TAG, String text) {
        Log.e("MyLog.Util :" + TAG + " -> ", text);
    }

    public static void NET(String TAG, String text) {
        Log.e("MyLog.NET :" + TAG + " -> ", text);
    }

    public static void e(String TAG, String text) {
        Log.e("MyLog.e :" + TAG + " -> ", text);
    }

}
