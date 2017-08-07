package com.zmcursor.daily.Utils;

/**
 * Created by ZMcursor on 2017/7/20 0020.
 */

public class TaskPool {

    private int taskNum = 0;
    private OnTasksDoneListener listener;

    public TaskPool(int taskNum, OnTasksDoneListener listener) {
        this.taskNum = taskNum;
        this.listener = listener;
    }

    public synchronized void removeTask() {
        taskNum--;
        if (taskNum <= 0) {
            listener.onTasksDone();
            listener = null;
        }
    }

    public interface OnTasksDoneListener {
        public void onTasksDone();
    }
}
