package com.zmcursor.daily.MVPModel.Struct;

import java.util.ArrayList;

/**
 * Created by ZMcursor on 2017/7/20 0020.
 */

public class StoriesOfDate {

    private Date date;
    private ArrayList<Story> stories;

    public StoriesOfDate() {
        this(null, null);
    }

    public StoriesOfDate(int date, ArrayList<Story> stories) {
        setDate(date);
        this.stories = stories;
    }

    public StoriesOfDate(Date date, ArrayList<Story> stories) {
        this.date = date;
        this.stories = stories;
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

    public ArrayList<Story> getStories() {
        return stories;
    }

    public void setStories(ArrayList<Story> stories) {
        this.stories = stories;
    }
}
