package com.zmcursor.daily.UI.View;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.WindowInsets;

import com.zmcursor.daily.R;

/**
 * Created by ZMcursor on 2017/7/19 0019.
 */

public class MyToolbar extends Toolbar {
    public MyToolbar(Context context) {
        super(context);
        mGetHeight();
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mGetHeight();
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGetHeight();
    }

    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        return super.onApplyWindowInsets(insets);
    }



    //
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int height = (int) (getResources().getDimension(R.dimen.size_56)
//                + getResources().getDimension(getResources().getIdentifier("status_bar_height", "dimen", "android")) + 0.5f);
//        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height);
//    }

    private void mGetHeight() {
//        int height = (int) (getResources().getDimension(R.dimen.size_56)
//                + getResources().getDimension(getResources().getIdentifier("status_bar_height", "dimen", "android")) + 0.5f);
        setMinimumHeight(getResources().getDimensionPixelSize(R.dimen.size_56));
    }
}
