package com.zmcursor.daily;

import android.content.Context;

import com.zmcursor.daily.MVPModel.service.DataService;
import com.zmcursor.daily.Utils.Loger;
import com.zmcursor.daily.Utils.Utils;

/**
 * Created by ZMcursor on 2017/7/1 0001.
 */

public class Conf {

    private static final String TAG = "Conf";

    public enum Boolean {O, I, U, M}

    public static final int saveDateNum = 7;
    public static final int saveContentNum = 10;

    public static final String date = "date";
    public static final String stories = "stories";
    public static final String top_stories = "top_stories";

    public static final String imageUrl = "imageUrl";
    public static final String image = "image";
    public static final String images = "images";
    public static final String type = "type";
    public static final String id = "id";
    public static final String ga_prefix = "ga_prefix";
    public static final String title = "title";
    public static final String isSaved = "isSaved";
    public static final String isRead = "isRead";

    public static final String body = "body";
    public static final String image_source = "image_source";
    public static final String share_url = "share_url";
    public static final String js = "js";
    public static final String css = "css";

    public static final int fullScreenSizeOfStoryArray = 10;
    public static final int mixSizeOfStoryArray = 50;

    public static final int mixConnect = 5;

    public static long time;


    private DailyAPP dailyAPP;
    private static Conf conf;

    private int statusBarHeight;
    private int navigationBarHeight;

    private Conf(DailyAPP dailyAPP) {
        this.dailyAPP = dailyAPP;
    }

    public static void initSelf(DailyAPP dailyAPP) {
        Loger.Util(TAG, "initConf");
        conf = new Conf(dailyAPP);
    }

    public static Conf getConf() {
        return conf;
    }

    public Context getContext() {
        return dailyAPP;
    }

    //读取设置
    public void init() {
        Loger.Util(TAG, "init");
    }

    public void setPadding(int statusBarHeight, int navigationBarHeight) {
        this.statusBarHeight = statusBarHeight;
        this.navigationBarHeight = navigationBarHeight;
    }

    public int getStatusBarHeight() {
        return statusBarHeight;
    }

    public int getNavigationBarHeight() {
        return navigationBarHeight;
    }
}
