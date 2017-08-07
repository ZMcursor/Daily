package com.zmcursor.daily.MVPPresenter;

import com.zmcursor.daily.MVPModel.ContentProvider.TimelineProvider.OnDataGetListener;
import com.zmcursor.daily.UI.Activity.MainActivity;
import com.zmcursor.daily.Utils.Loger;

/**
 * Created by ZMcursor on 2017/7/1 0001.
 */

public class MainPresenter {

    private static final String TAG = "MainPresenter";

    private MainActivity mainActivity;

    public MainPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}
