package com.zmcursor.daily.MVPModel.service;

import com.zmcursor.daily.Conf;
import com.zmcursor.daily.DailyAPP;
import com.zmcursor.daily.MVPModel.ContentProvider.TimelineProvider;
import com.zmcursor.daily.MVPModel.ContentProvider.TimelineProvider.OnDataGetListener;
import com.zmcursor.daily.Utils.Loger;

import java.util.concurrent.Semaphore;

public class DataService {

    private static final String TAG = "DataService";

    public static Semaphore semaphore = new Semaphore(Conf.mixConnect, true);

    private TimelineProvider timelineProvider = null;
    private DailyAPP dailyAPP;
    private static DataService dataService;

    public static void initSelf(DailyAPP dailyAPP) {
        Loger.UI(TAG, "initSelf");
        dataService = new DataService(dailyAPP);
    }

    private DataService(DailyAPP dailyAPP) {
        this.dailyAPP = dailyAPP;
    }

    public static DataService getDataService() {
        return dataService;
    }

    public void getInitData(OnDataGetListener listener) {
        Loger.UI(TAG, "getInitData");
        if (timelineProvider == null)
            timelineProvider = new TimelineProvider(dailyAPP, listener);
        else timelineProvider.refresh(listener);
    }

//
//    public void getMoreData(OnDataGetListener onDataGetListener) {
//        Loger.UI(TAG, "getMoreData");
//        timelineProvider.getMoreStory(onDataGetListener);
//    }

//    public void setOnDataGetListener(OnDataGetListener onDataGetListener) {
//        this.onDataGetListener = onDataGetListener;
//    }

    public TimelineProvider getTimelineProvider() {
        return timelineProvider;
    }
}
