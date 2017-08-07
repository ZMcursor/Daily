package com.zmcursor.daily.UI.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

import com.zmcursor.daily.Conf;
import com.zmcursor.daily.Utils.Loger;

/**
 * Created by ZMcursor on 2017/7/24 0024.
 */

public class RootView extends ViewGroup {

    private static final String TAG = "MainRootView";

    public RootView(Context context) {
        super(context);
//        setChildrenDrawingOrderEnabled(true);
    }

    public RootView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setChildrenDrawingOrderEnabled(true);
    }

    public RootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Loger.UI(TAG, getPaddingTop() + "onMeasure " + (System.currentTimeMillis() - Conf.time));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Loger.UI(TAG, getPaddingTop() + "onMeasure " + (System.currentTimeMillis() - Conf.time));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Loger.UI(TAG, getPaddingTop() + "onLayout " + (System.currentTimeMillis() - Conf.time));
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(left, top, right, bottom);
        }
        Loger.UI(TAG, getPaddingTop() + "onLayout " + (System.currentTimeMillis() - Conf.time));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Loger.UI(TAG, getPaddingTop() + "dispatchDraw " + (System.currentTimeMillis() - Conf.time));
        super.dispatchDraw(canvas);
        Loger.UI(TAG, getPaddingTop() + "dispatchDraw " + (System.currentTimeMillis() - Conf.time));
    }

    @Override
    protected void onAttachedToWindow() {
        Loger.UI(TAG, getPaddingTop() + "onAttachedToWindow " + (System.currentTimeMillis() - Conf.time));
        super.onAttachedToWindow();
        Loger.UI(TAG, getPaddingTop() + "onAttachedToWindow " + (System.currentTimeMillis() - Conf.time));
    }

    @Override
    public WindowInsets dispatchApplyWindowInsets(WindowInsets insets) {
        Loger.UI(TAG, getPaddingTop() + "dispatchApplyWindowInsets " + (System.currentTimeMillis() - Conf.time));
        return super.dispatchApplyWindowInsets(insets);
    }

    @Override
    protected boolean fitSystemWindows(Rect insets) {
        Loger.UI(TAG, getPaddingTop() + "fitSystemWindows " + (System.currentTimeMillis() - Conf.time));
        return super.fitSystemWindows(insets);
    }

    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        Loger.UI(TAG, getPaddingTop() + "onApplyWindowInsets " + (System.currentTimeMillis() - Conf.time));
        return super.onApplyWindowInsets(insets);
    }
//
//    @Override
//    protected int getChildDrawingOrder(int childCount, int i) {
//        return childCount - i - 1;
//    }

    public void removeSplash() {

        TranslateAnimation tAnim = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -1,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0);
        AlphaAnimation aAnim = new AlphaAnimation(1f, 0.8f);

        Loger.UI("removeSplash", "removeSplash");

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(tAnim);
        animationSet.addAnimation(aAnim);
//        animationSet.setInterpolator(new DecelerateInterpolator(2));
        animationSet.setDuration(2000);
        animationSet.setFillAfter(true);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                removeView(getChildAt(0));
                removeViewAt(0);
//                splashView = null;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

//        getChildAt(0).setAnimation(animationSet);
//        splashView.startAnimation(tAnim);
//        splashView.startAnimation(animationSet);
        getChildAt(0).startAnimation(animationSet);
//        animationSet.start();
    }
}
