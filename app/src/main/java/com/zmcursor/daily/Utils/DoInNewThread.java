package com.zmcursor.daily.Utils;

import android.os.Handler;

/**
 * Created by ZMcursor on 2017/7/17 0017.
 * <p>
 * 封装了异步线程处理任务，主线程更新的方法
 */

public abstract class DoInNewThread {

    private static final String TAG = "DoInNewThread";

    private Handler handler;

    public DoInNewThread() {
        handler = new Handler();
    }

    public void run() {
        new Thread(() -> {
            Loger.Util(TAG, "run :Thread");
            Do();
            handler.post(() -> {
                handler = null;
                onDone();
            });
        }).start();
    }

    public void runWithoutPost() {
        new Thread(() -> {
            Loger.Util(TAG, "runWithoutPost");
            Do();
        }).start();
    }

    public void post() {
        Loger.Util(TAG, "runWithoutPost :post");
        handler.post(() -> {
            handler = null;
            onDone();
        });
    }

    public abstract void Do();

    public abstract void onDone();
}
