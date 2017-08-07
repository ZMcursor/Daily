package com.zmcursor.daily.UI.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.zmcursor.daily.MVPModel.Struct.Date;
import com.zmcursor.daily.MVPModel.Struct.StoriesOfDate;
import com.zmcursor.daily.MVPModel.Struct.Story;
import com.zmcursor.daily.UI.LoadingView;
import com.zmcursor.daily.UI.StoryListAdapter;
import com.zmcursor.daily.Utils.Loger;

import java.util.ArrayList;

/**
 * Created by ZMcursor on 2017/7/26 0026.
 */

public class LoadingRecyclerView extends RecyclerView {


    private LinearLayoutManager layoutManager;
    private ListListener listListener = null;
    private StoryListAdapter adapter;
    private LoadingView loadingView;

    private boolean isLoading = false;
//    private boolean isRefresh = false;

    public LoadingRecyclerView(Context context) {
        super(context);
        init();
    }

    public LoadingRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        loadingView = new LoadingView();
        adapter = new StoryListAdapter(getContext(), loadingView);
        setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getContext());
        setLayoutManager(layoutManager);
        addOnScrollListener(new LoadMoreScrollListener());
    }

    public void setListListener(ListListener listener) {
        this.listListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        adapter.setOnItemClickListener(onItemClickListener);
    }

    public void onRefreshFinish(boolean isSuccess, ArrayList<StoriesOfDate> storiesOfDateArray) {
//        isRefresh = false;
        if (isSuccess) adapter.onRefreshFinish(storiesOfDateArray);
    }

    public void onLoadingFinish(boolean isSuccess, ArrayList<StoriesOfDate> storiesOfDateArray) {
        isLoading = false;
        if (isSuccess) {
            loadingView.finishLoading();
            adapter.onLoadingFinish(storiesOfDateArray);
            loadingView.remove();
        } else loadingView.onLoadingFail();

    }


    private class LoadMoreScrollListener extends OnScrollListener {

        private boolean isScrolling = false;

        private boolean isScrollToBottom = false;
//        private boolean isScrollToTop = false;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            Loger.UI("RecyclerView", "onScrollStateChanged");

            if (listListener != null && newState == SCROLL_STATE_IDLE) {
                isScrolling = false;
                listListener.onStop();

//                int position, padding, edge, offset;

                //判断是否两次下拉 并刷新
//                if (isRefresh) return;
//                position = layoutManager.findFirstVisibleItemPosition();
//                if (position == 0) {
//                    edge = recyclerView.getChildAt(0).getTop();
//                    padding = recyclerView.getPaddingTop();
//                    offset = (int) (recyclerView.getChildAt(0).getHeight() * 0.25f);
//
//                    if (abs(edge - padding) < offset) {
//                        if (isScrollToTop) {
//                            if (listListener.onTopTryTwice()) {
//                                isScrollToTop = false;
//                                isRefresh = true;
//                            }
//                        } else isScrollToTop = false;
//                    }
//                }

                //判断是否两次上拉 并刷新
                if (isLoading || !loadingView.isShowing) return;
                if (isScrollToBottom && listListener.onBottomTryTwice()) {
                    loadingView.startLoading();
                    isScrollToBottom = false;
                    isLoading = true;
                } else isScrollToBottom = true;
//                position = layoutManager.findLastVisibleItemPosition();
//                if (position + 1 == layoutManager.getItemCount()) {
//                    edge = recyclerView.getChildAt(recyclerView.getChildCount() - 1).getBottom();
//                    padding = recyclerView.getPaddingBottom();
//                    offset = (int) (recyclerView.getChildAt(recyclerView.getChildCount() - 1).getHeight() * 0.25f);
//
//                    if (abs(edge + padding - recyclerView.getHeight()) < offset) {
//                        if (isScrollToBottom) {
//                            if (listListener.onBottomTryTwice()) {
//                                loadingView.startLoading();
//                                isScrollToBottom = false;
//                                isLoading = true;
//                            }
//                        } else isScrollToBottom = true;
//                    }
//                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!isScrolling) {
                isScrolling = true;
                listListener.onScrolling();
            }
            if (loadingView.isShowing && layoutManager.findLastVisibleItemPosition() + 1 < layoutManager.getItemCount()) {
                loadingView.remove();
                isScrollToBottom = false;
            }
            Loger.UI("RecyclerView", "onScrolled");
        }
    }

    public interface ListListener {
        void onScrolling();

        boolean onTopTryTwice();

        boolean onBottomTryTwice();

        void onStop();
    }

    public interface OnItemClickListener {
        void onItemClick(View item, Date date, Story story);
    }
}
