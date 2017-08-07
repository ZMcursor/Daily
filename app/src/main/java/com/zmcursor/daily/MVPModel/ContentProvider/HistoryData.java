package com.zmcursor.daily.MVPModel.ContentProvider;

import android.content.Context;
import android.graphics.Bitmap;

import com.zmcursor.daily.MVPModel.ContentParse.TimelineParse;
import com.zmcursor.daily.MVPModel.ContentProvider.TimelineProvider.OnDataGetListener;
import com.zmcursor.daily.MVPModel.DataSave.HistoryDataXML;
import com.zmcursor.daily.MVPModel.DataSave.HistoryDatabase;
import com.zmcursor.daily.MVPModel.DataSave.HistoryImage;
import com.zmcursor.daily.MVPModel.Struct.Date;
import com.zmcursor.daily.MVPModel.Struct.EStory;
import com.zmcursor.daily.MVPModel.Struct.StoriesOfDate;
import com.zmcursor.daily.MVPModel.Struct.Story;
import com.zmcursor.daily.MVPModel.Struct.TodayTopStories;
import com.zmcursor.daily.Utils.Loger;
import com.zmcursor.daily.Utils.TaskPool;
import com.zmcursor.daily.Utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by ZMcursor on 2017/7/13 0013.
 * <p>
 * 保存及获取历史数据的类，对于最新的数据保存在XML中，而历史数据保存在数据库
 * <p>
 * 文字数据采用直接覆盖的方式
 * <p>
 * 而图片数据采用比对方法保存新数据，删除旧数据
 */

public class HistoryData {

    private static final String TAG = "HistoryData";

    //    private Context context;
    private HistoryDatabase historyDatabase;
    private HistoryDataXML historyDataXML;
    private HistoryImage historyImage;
    private boolean isFull = false;
//    private ArrayList<Integer> topImageId = new ArrayList<>();

//    private TimelineProvider timelineProvider;

    public HistoryData(Context context, TimelineProvider timelineProvider) {
//        this.timelineProvider = timelineProvider;
//        this.context = context;
        historyDatabase = new HistoryDatabase(context);
        historyDataXML = new HistoryDataXML(context);
        historyImage = new HistoryImage(context);
    }

    public StoriesOfDate getStoryBefore(int date) {
        int beforeDate = Utils.dateBefore(date);
        Loger.NET(TAG, "getStoryBefore" + beforeDate);
        if (historyDataXML.isDateSaved(beforeDate)) {
            Loger.NET(TAG, "getStoryBefore, historyDatabase" + beforeDate);
            StoriesOfDate storiesOfDate = historyDatabase.getStoriesOfDate(new Date(beforeDate));
            try {
                historyImage.getImageOfDate(storiesOfDate);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return storiesOfDate;
        }
        return null;
    }

    public void getTopImages(TodayTopStories todayTopStories, OnDataGetListener listener) {
        // 获取头条图片
        Loger.NET(TAG, "getTopImages");
        listener.onDataGet(null);
//        getImages(todayTopStories.getTodayTopStories(), (TaskPool.OnTasksDoneListener) () -> {
//            listener.onDataGet(null);
////            historyImage.saveImageOfDate(storiesOfDate);
//        });
    }

    public void getTodayStoriesImages(StoriesOfDate storiesOfDate, OnDataGetListener listener) {
        //获取今日文章图片
        Loger.NET(TAG, "getTodayStoriesImages");
        getImages(storiesOfDate.getStories(), () -> {
            listener.onDataGet(null);
            historyImage.saveImageOfDate(storiesOfDate);
        });
    }

    public void getStoriesImages(StoriesOfDate storiesOfDate, OnDataGetListener listener) {
        //获取文章图片
        Loger.NET(TAG, "getStoriesImages");

        getImages(storiesOfDate.getStories(), () -> {
            listener.onDataGet(null);
            if (!isFull) historyImage.saveImageOfDate(storiesOfDate);
        });
    }

    private void getImages(ArrayList<Story> stories, TaskPool.OnTasksDoneListener listener) {
        //TODO 比对文件夹的图片，删除旧的，下载新的
        TaskPool taskPool = new TaskPool(stories.size(), listener);
        for (int i = 0; i < stories.size(); i++) {
            EStory story = stories.get(i);
            TimelineProvider.downloadImage(story.getImageUrl(), object -> {
                story.setImageBitmap((Bitmap) object);
                taskPool.removeTask();
            });
        }
    }

    public void saveTodayData(TimelineParse timelineParse) {
        //TODO  缓存数据；数据缓存到XML文件,没网时可以看
        historyDataXML.saveTodayStory(timelineParse.getDate(), timelineParse.getTopStoriesJson(), timelineParse.getStoriesJson());
        historyDataXML.recycle();
    }

    public void saveHistoryData(StoriesOfDate storiesOfDate, Date beforeDate) {
        //TODO  缓存数据；
        if (!isFull) {
            isFull = !historyDataXML.saveNewDate(storiesOfDate.getDate().getIntDate(), beforeDate.getIntDate(), this);
            if (!isFull) historyDatabase.saveStoriesOfDate(storiesOfDate);
        }
    }

    public void deleteStoriesOfDate(int date) {
        historyDatabase.deleteStoriesOfDate(date);
        historyImage.deleteImageOfDate(date);
    }

    public void deleteContent(int id) {
        historyDatabase.deleteContent(id);
    }
}
