package com.zmcursor.daily.UI.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zmcursor.daily.Utils.Utils;

/**
 * Created by ZMcursor on 2017/8/2 0002.
 */

public class StatusView extends View {
    public StatusView(Context context) {
        super(context);
        setMinimumHeight(Utils.getStatusHeigth(context));
    }

    public StatusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setMinimumHeight(Utils.getStatusHeigth(context));
    }

    public StatusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setMinimumHeight(Utils.getStatusHeigth(context));
    }
}
