package com.zmcursor.daily.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.zmcursor.daily.Conf;
import com.zmcursor.daily.R;
import com.zmcursor.daily.UI.Fragment.TimelineFragment;
import com.zmcursor.daily.UI.ThemeManager;
import com.zmcursor.daily.Utils.Loger;
import com.zmcursor.daily.Utils.Utils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TimelineFragment timelineFragment;

    private FrameLayout appbar;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Loger.UI(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(ThemeManager.getThemeManager().getMain_layout());
        initView();
        timelineFragment = new TimelineFragment(this);
        getFragmentManager().beginTransaction().replace(R.id.story_list_container, timelineFragment).commit();
    }

    @Override
    protected void onResume() {
        Loger.UI(TAG, "onResume");
        super.onResume();
        Loger.UI(TAG, "onResume");
    }

    private void initView() {
        appbar = (FrameLayout) findViewById(R.id.appbar);
        appbar.setBackgroundColor(ThemeManager.getThemeManager().getColorPrimary());
        Utils.setStatusBarPadding(appbar);
        ThemeManager.getThemeManager().addView(appbar, (colorPrimary, colorPrimaryDark, colorAccent) -> appbar.setBackgroundColor(colorPrimary));

        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mDrawerToggle = new DrawerLayout.DrawerListener()
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    public void startReading(int id) {
        Intent intent = new Intent(this, ReadActivity.class);
//                Intent intent = new Intent(mainActivity, TestActivity.class);
        intent.putExtra(Conf.id, id);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Conf.getConf().getDataService().stopSelf();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.more_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.btn_night_mode:
                break;
            case R.id.btn_setting:
                break;
        }
        return true;
    }
}
