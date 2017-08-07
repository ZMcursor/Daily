package com.zmcursor.daily.UI.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by ZMcursor on 2017/7/19 0019.
 */

public class TopImageView extends android.support.v7.widget.AppCompatImageView {
    public TopImageView(Context context) {
        super(context);
        setScaleType(ScaleType.CENTER_CROP);
    }

    public TopImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setScaleType(ScaleType.CENTER_CROP);
    }

    public TopImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.CENTER_CROP);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) ((float) (width * 2) / 3f + 0.5f);
        setMeasuredDimension(width, height);
    }
}
