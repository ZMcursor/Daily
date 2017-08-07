package com.zmcursor.daily.UI.View;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zmcursor.daily.R;
import com.zmcursor.daily.UI.StoryListAdapter;

/**
 * Created by ZMcursor on 2017/7/25 0025.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        StoryListAdapter adapter = (StoryListAdapter) parent.getAdapter();

        if (adapter.getItemViewType(position) != StoryListAdapter.ITEM_TYPE.ITEM_DATE.ordinal()) {
            if (adapter.getItemViewType(position + 1) == StoryListAdapter.ITEM_TYPE.ITEM_DATE.ordinal()) {
                outRect.bottom = parent.getResources().getDimensionPixelSize(R.dimen.Spacing_8);
            } else {
                outRect.bottom = parent.getResources().getDimensionPixelSize(R.dimen.divider);
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Paint paint = new Paint();
        paint.setColor(parent.getResources().getColor(R.color.d_gray));

        View child;
        int position;
        StoryListAdapter adapter = (StoryListAdapter) parent.getAdapter();

        int left = 0, right, top, bottom;

        int divider = parent.getResources().getDimensionPixelSize(R.dimen.divider);

        for (int i = 0; i < parent.getChildCount(); i++) {
            child = parent.getChildAt(i);
            position = parent.getChildAdapterPosition(child);
            if (adapter.getItemViewType(position) != StoryListAdapter.ITEM_TYPE.ITEM_DATE.ordinal()) {
                if (adapter.getItemViewType(position + 1) == StoryListAdapter.ITEM_TYPE.ITEM_DATE.ordinal()) {
                    right = parent.getWidth();
                } else {
                    right = parent.getWidth() - child.getHeight();
                }
                top = child.getBottom();
                bottom = top + divider;
                c.drawRect(left, top, right, bottom, paint);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
