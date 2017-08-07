package com.zmcursor.daily.MVPPresenter;

import com.zmcursor.daily.Conf;
import com.zmcursor.daily.MVPModel.ContentProvider.TimelineProvider;
import com.zmcursor.daily.MVPModel.ContentProvider.TimelineProvider.OnDataGetListener;
import com.zmcursor.daily.MVPModel.Struct.StoriesOfDate;
import com.zmcursor.daily.MVPModel.Struct.TodayTopStories;
import com.zmcursor.daily.UI.Activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by ZMcursor on 2017/7/21 0021.
 */

public class TimelinePresenter {

    private static final String TAG = "TimelinePresenter";

    private MainActivity mainActivity;

    private TimelineProvider provider;

    public TimelinePresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        provider = Conf.getConf().getDataService().getTimelineProvider();
    }

//    public void init() {
//        Loger.UI(TAG, "init");
//    }

    public ArrayList<StoriesOfDate> getStoriesOfDateArray() {
        return provider.getStoriesOfDateArray();
    }

    public TodayTopStories getTodayTopStories() {
        return provider.getTodayTopStories();
    }

    public void refresh(OnDataGetListener listener) {
        provider.refresh(listener);
    }

    public void getMoreData(OnDataGetListener listener) {
        provider.getMoreStory(listener);
    }
}
