package com.zmcursor.daily.Utils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ZMcursor on 2017/7/24 0024.
 */

public class MySemaphore {

    private static final String TAG = "MySemaphore";

    private int permits;
    private boolean isSuspend = false;
    private Queue<Thread> threadQueue = new LinkedList<>();

    public MySemaphore(int permits) {
        this.permits = permits;
    }


    private synchronized boolean canAcquire() {

        Loger.NET(TAG, "canAcquire" + permits);

        if (isSuspend || permits <= 0) {
            threadQueue.offer(Thread.currentThread());
//            Thread.currentThread().suspend();
//            wait();
            return false;
        } else {
            permits--;
            return true;
        }
    }

    public void acquire() {
        if (!canAcquire()) Thread.currentThread().suspend();
    }

    public synchronized void release() {
        Loger.NET(TAG, "release" + permits);
        permits++;
        wake();
    }

    private synchronized void wake() {
        Loger.NET(TAG, "wake");
        while (!isSuspend && permits > 0) {
            Thread thread = threadQueue.poll();
            if (thread != null) {
//                thread.notify();
                Loger.NET(TAG, "wake   resume");
                thread.resume();
                Loger.NET(TAG, "wake   resume");
                permits--;
            } else break;
        }
    }

    public void suspend() {
        isSuspend = true;
    }

    public void resume() {
        isSuspend = false;
        new Thread(this::wake).start();
    }
}
