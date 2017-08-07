package com.zmcursor.daily;

import android.app.Application;

import com.zmcursor.daily.MVPModel.service.DataService;
import com.zmcursor.daily.UI.ThemeManager;
import com.zmcursor.daily.Utils.Loger;

/**
 * Created by ZMcursor on 2017/7/17 0017.
 */

public class DailyAPP extends Application {

    private static final String TAG = "DailyAPP";

    @Override
    public void onCreate() {
        Loger.UI(TAG, "onCreate");

        Conf.time = System.currentTimeMillis();

        super.onCreate();
        Conf.initSelf(this);
        ThemeManager.initSelf(this);
        DataService.initSelf(this);
    }

}
