package com.zmcursor.daily.UI.View;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.zmcursor.daily.Conf;
import com.zmcursor.daily.Utils.Utils;

/**
 * Created by ZMcursor on 2017/8/2 0002.
 */

public class MyAppbar extends FrameLayout {

    public MyAppbar(Context context) {
        super(context);
        setPadding(0, Utils.getStatusHeigth(getContext()), 0, 0);
    }

    public MyAppbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setPadding(0, Conf.getConf().getStatusBarHeight(), 0, 0);
    }

    public MyAppbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setPadding(0, Conf.getConf().getStatusBarHeight(), 0, 0);
    }

    public void setBgColor(@ColorInt int color) {
        setBackgroundColor(color);
    }

    public Toolbar getToolbar() {
        return (Toolbar) getChildAt(0);
    }

}
