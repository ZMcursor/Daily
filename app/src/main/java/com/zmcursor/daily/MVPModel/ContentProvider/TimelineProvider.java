package com.zmcursor.daily.MVPModel.ContentProvider;

import android.content.Context;

import com.zmcursor.daily.Conf;
import com.zmcursor.daily.MVPModel.ContentParse.TimelineParse;
import com.zmcursor.daily.MVPModel.NetLink.RawContent;
import com.zmcursor.daily.MVPModel.Struct.Date;
import com.zmcursor.daily.MVPModel.Struct.StoriesOfDate;
import com.zmcursor.daily.MVPModel.Struct.TodayTopStories;
import com.zmcursor.daily.R;
import com.zmcursor.daily.Utils.DoInNewThread;
import com.zmcursor.daily.Utils.Loger;
import com.zmcursor.daily.Utils.TaskPool;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by ZMcursor on 2017/7/1 0001.
 * <p>
 * 提供首页数据
 */

public class TimelineProvider {

    private static final String TAG = "TimelineProvider";

    private TaskPool taskPool;

    private Context context;
    private Date lastDate;
    private ArrayList<StoriesOfDate> storiesOfDateArray = new ArrayList<>();
    private TodayTopStories todayTopStories;

    private HistoryData historyData;

    public TimelineProvider(Context context, OnDataGetListener listener) {
        this.context = context;
        historyData = new HistoryData(context, this);
        getInitContent(listener);
    }

    public ArrayList<StoriesOfDate> getStoriesOfDateArray() {
        return storiesOfDateArray;
    }

    public TodayTopStories getTodayTopStories() {
        return todayTopStories;
    }

    private void getInitContent(OnDataGetListener listener) {

        Loger.NET(TAG, "getInitContent");
        DoInNewThread doInNewThread = new DoInNewThread() {
            @Override
            public void Do() {
                getLatest(true, object -> onTaskDone());
                if (storiesOfDateArray.get(0).getStories().size() < Conf.fullScreenSizeOfStoryArray) {
                    getStoryBefore(object -> onTaskDone());
                } else onTaskDone();
            }

            @Override
            public void onDone() {
                taskPool = null;
                listener.onDataGet(null);
            }
        };

        taskPool = new TaskPool(3, doInNewThread::post);
        doInNewThread.runWithoutPost();
    }

    private void getLatest(boolean isFirst, OnDataGetListener listener) {
        Loger.NET(TAG, "getLatest");
        TimelineParse timelineParse = new TimelineParse();
        try {
            timelineParse.Parse((String) RawContent.getRawContent(context.getString(R.string.url_timeline_latest), false), true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lastDate = new Date(timelineParse.getDate());
        todayTopStories = new TodayTopStories(lastDate, timelineParse.getTopStories());

        StoriesOfDate storiesOfDate = new StoriesOfDate(lastDate, timelineParse.getStories());

        if (isFirst) storiesOfDateArray.add(storiesOfDate);
        else storiesOfDateArray.set(0, storiesOfDate);

        historyData.saveTodayData(timelineParse);
        historyData.getTopImages(todayTopStories, listener);
        historyData.getTodayStoriesImages(storiesOfDateArray.get(0), listener);
    }

    public void refresh(OnDataGetListener listener) {
        DoInNewThread doInNewThread = new DoInNewThread() {
            @Override
            public void Do() {
                getLatest(false, object -> onTaskDone());
            }

            @Override
            public void onDone() {
                taskPool = null;
                listener.onDataGet(null);
            }
        };

        taskPool = new TaskPool(1, doInNewThread::post);
        doInNewThread.runWithoutPost();
    }

    private void getStoryBefore(OnDataGetListener listener) {
        Loger.NET(TAG, "getStoryBefore");
        StoriesOfDate storiesOfDate = historyData.getStoryBefore(lastDate.getIntDate());
        if (storiesOfDate == null) {
            String url = context.getString(R.string.url_content_before) + String.valueOf(lastDate.getIntDate());
            TimelineParse timelineParse = new TimelineParse();
            try {
                timelineParse.Parse((String) RawContent.getRawContent(url, false), false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            storiesOfDate = new StoriesOfDate(new Date(timelineParse.getDate()), timelineParse.getStories());
            historyData.saveHistoryData(storiesOfDate, lastDate);
            historyData.getStoriesImages(storiesOfDate, listener);
            lastDate = storiesOfDate.getDate();
            storiesOfDateArray.add(storiesOfDate);
        } else {
            lastDate = storiesOfDate.getDate();
            storiesOfDateArray.add(storiesOfDate);
            listener.onDataGet(null);
        }
    }

    public void getMoreStory(OnDataGetListener listener) {
        Loger.NET(TAG, "getMoreStory");
        DoInNewThread doInNewThread = new DoInNewThread() {
            @Override
            public void Do() {
                getStoryBefore(object -> post());
            }

            @Override
            public void onDone() {
                listener.onDataGet(null);
            }
        };
        doInNewThread.runWithoutPost();
    }

    public static void downloadImage(String url, OnDataGetListener listener) {
        Loger.NET(TAG, "downloadImage");
        new Thread(() -> listener.onDataGet(RawContent.getRawContent(url, true))).start();
    }

    private void onTaskDone() {
        Loger.NET(TAG, "onTaskDone");
        if (taskPool != null) taskPool.removeTask();
    }

    public interface OnDataGetListener {
        void onDataGet(Object object);
    }
}