package com.zmcursor.daily.MVPModel.Struct;

import java.util.ArrayList;

/**
 * Created by ZMcursor on 2017/7/20 0020.
 */
public class TodayTopStories {

    private Date date;
    private ArrayList<TopStory> todayTopStories;

    public TodayTopStories() {
        this(null, null);
    }

    public TodayTopStories(int date, ArrayList<TopStory> todayTopStories) {
        setDate(date);
        this.todayTopStories = todayTopStories;
    }

    public TodayTopStories(Date date, ArrayList<TopStory> todayTopStories) {
        this.date = date;
        this.todayTopStories = todayTopStories;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = new Date(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<TopStory> getTodayTopStories() {
        return todayTopStories;
    }

    public void setTodayTopStories(ArrayList<TopStory> todayTopStories) {
        this.todayTopStories = todayTopStories;
    }
}