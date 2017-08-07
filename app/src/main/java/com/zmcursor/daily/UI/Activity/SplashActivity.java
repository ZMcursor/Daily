package com.zmcursor.daily.UI.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zmcursor.daily.Conf;
import com.zmcursor.daily.MVPModel.service.DataService;
import com.zmcursor.daily.R;
import com.zmcursor.daily.UI.ThemeManager;
import com.zmcursor.daily.Utils.Loger;
import com.zmcursor.daily.Utils.Utils;

public class SplashActivity extends Activity {

    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Loger.UI(TAG, "onCreate");
        Utils.setWindows(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //初始化
        new Thread(() -> {
            Conf.getConf().init();
            ThemeManager.getThemeManager().init();
        }).start();

        DataService.getDataService().getInitData(object -> {
            View view = findViewById(R.id.padding);
            Conf.getConf().setPadding(view.getPaddingTop(), view.getPaddingBottom());
            Loger.UI(TAG, view.getPaddingTop() + " onServiceDisconnected " + view.getPaddingBottom());
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        });
    }
}
