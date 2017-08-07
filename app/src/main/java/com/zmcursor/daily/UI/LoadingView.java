package com.zmcursor.daily.UI;

import android.os.Handler;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by ZMcursor on 2017/7/26 0026.
 */

public class LoadingView {

    private int count = 0;

    private TextView textView;

    private String[] loadingText = {"加载中", "加载中.", "加载中..", "加载中..."};
    private String text;

    private boolean isLoading = false;
    public boolean isShowing = false;
    private Handler handler;
    private Runnable runnable;

    public LoadingView() {
        handler = new Handler();
        runnable = this::Do;
    }

    public void onShow(TextView textView) {
        this.textView = textView;
        textView.setGravity(Gravity.CENTER);
        isShowing = true;
        if (!isLoading) {
            text = "上拉加载更多";
            changeText();
        } else Do();
    }

    public void remove() {
//        count = 0;
        isShowing = false;
        textView = null;
    }

    public void startLoading() {
        isLoading = true;
        Do();
    }

    public void finishLoading() {
        count = 0;
        isLoading = false;
        text = "加载完成";
        changeText();
    }

    public void onLoadingFail() {
        count = 0;
        isLoading = false;
        text = "加载失败";
        changeText();
    }

    private void Do() {
        if (isShowing && isLoading) {
            count++;
            text = loadingText[count % 4];
            changeText();
            handler.postDelayed(runnable, 500);
        }
    }

    private void changeText() {
        if (textView != null)
            textView.setText(text);
    }
}
