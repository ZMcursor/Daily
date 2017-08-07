package com.zmcursor.daily.UI.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ZMcursor on 2017/7/16 0016.
 */

public class MyImageView extends View {

    private float ratio = 1;

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width, height,
                widthMode = MeasureSpec.getMode(widthMeasureSpec),
                heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec);
            height = (int) (width * ratio + 0.5f);
        } else if (heightMode == MeasureSpec.EXACTLY) {
            height = MeasureSpec.getSize(heightMeasureSpec);
            width = (int) (height / ratio + 0.5f);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        setMeasuredDimension(width, height);
    }

    public void setImage(Bitmap bitmap) {
        int height = bitmap.getHeight(), width = bitmap.getWidth(),
                left = 0, top = 0, right = width, bottom = height;
        float mRatio = height / (float) width;
        if (mRatio != ratio) {
            if (mRatio > ratio) {
                left = 0;
                right = width;
                bottom = (int) (width * ratio + 0.5f);
                top = (height - bottom) >> 1;
            } else {
                right = (int) (height / ratio + 0.5f);
                left = (width - right) >> 1;
                top = 0;
                bottom = height;
            }
        }
        setBackground(new BitmapDrawable(getResources(), Bitmap.createBitmap(bitmap, left, top, right, bottom)));
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
        invalidate();
    }
}
