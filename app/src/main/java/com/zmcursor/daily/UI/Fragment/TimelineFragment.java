package com.zmcursor.daily.UI.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.zmcursor.daily.MVPPresenter.TimelinePresenter;
import com.zmcursor.daily.R;
import com.zmcursor.daily.UI.Activity.MainActivity;
import com.zmcursor.daily.UI.ThemeManager;
import com.zmcursor.daily.UI.View.LoadingRecyclerView;
import com.zmcursor.daily.UI.View.MyItemDecoration;
import com.zmcursor.daily.Utils.Loger;
import com.zmcursor.daily.Utils.Utils;

/**
 * Created by ZMcursor on 2017/7/4 0004.
 */

public class TimelineFragment extends BaseFragment implements LoadingRecyclerView.ListListener {

    private static final String TAG = "TimelineFragment";

    private LoadingRecyclerView storyList;
//    private ImageSwitcher headImage;

    private MainActivity mainActivity;

    private TimelinePresenter presenter;

    public TimelineFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        presenter = new TimelinePresenter(mainActivity);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Loger.UI(TAG, "onViewCreated");
        storyList = (LoadingRecyclerView) view.findViewById(R.id.stories_list);

        Utils.setNavigationBarPadding(storyList);

        storyList.setListListener(this);

        storyList.setOnItemClickListener((item, date, story) -> mainActivity.startReading(story.getId()));

        storyList.onRefreshFinish(true, presenter.getStoriesOfDateArray());

        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.list_refresh);

        refreshLayout.setColorSchemeColors(ThemeManager.getThemeManager().getColorPrimary(),
                ThemeManager.getThemeManager().getColorPrimaryDark(),
                ThemeManager.getThemeManager().getColorAccent());

        refreshLayout.setOnRefreshListener(() -> presenter.refresh(object -> {
            storyList.onRefreshFinish(true, presenter.getStoriesOfDateArray());
            refreshLayout.setRefreshing(false);
        }));

        if (ThemeManager.getThemeManager().getStyle() == ThemeManager.style_chip)
            storyList.addItemDecoration(new MyItemDecoration());

//        headImage = (ImageSwitcher) getRootView().findViewById(R.id.head_image);

//        headImage.setFactory(new ViewSwitcher.ViewFactory() {
//
//            int postion = 0;
//
//            @Override
//            public View makeView() {
//                TopImageView imageView = new TopImageView(getContext());
//                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ImageSwitcher.LayoutParams.MATCH_PARENT, ImageSwitcher.LayoutParams.WRAP_CONTENT));
////                imageView.setAdjustViewBounds(true);
////                imageView.setImageURI(Uri.parse(timelineProvider.getTopStoryArray().get(postion).getImageString()));
//                imageView.setImageDrawable(imageView.getResources().getDrawable(R.drawable.v2));
//                return imageView;
//            }
//        });
    }

    @Override
    public int getLayout() {
        return ThemeManager.getThemeManager().getTimeline_layout();
    }

    @Override
    public void onScrolling() {
        Loger.UI(TAG, "onScrolling");
    }

    @Override
    public boolean onTopTryTwice() {
//        presenter.refresh(object -> storyList.onRefreshFinish(true, presenter.getStoriesOfDateArray()));
        Loger.UI(TAG, "onTopTryTwice");
        return true;
    }

    @Override
    public boolean onBottomTryTwice() {
        Loger.UI(TAG, "onBottomTryTwice");
        presenter.getMoreData(object -> storyList.onLoadingFinish(true, presenter.getStoriesOfDateArray()));
        return true;
    }
}
