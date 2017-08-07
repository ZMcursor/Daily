package com.zmcursor.daily.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.zmcursor.daily.DailyAPP;
import com.zmcursor.daily.R;
import com.zmcursor.daily.Utils.Loger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZMcursor on 2017/7/19 0019.
 */

public class ThemeManager {

    private static final String TAG = "ThemeManager";

    private static final String theme_pref = "theme_pref";
    private static final String pref_style = "theme_style";
    private static final String pref_colorPrimary = "colorPrimary";
    private static final String pref_colorPrimaryDark = "colorPrimaryDark";
    private static final String pref_colorAccent = "colorAccent";

    private int style;
    public static final int style_card = 100;
    public static final int style_chip = 200;

    private DailyAPP dailyAPP;
    private static ThemeManager themeManager;

    private int main_layout;
    private int read_layout;
    private int timeline_layout;
    private int story_item;

    private int colorPrimary;
    private int colorPrimaryDark;
    private int colorAccent;

    private Map<Object, OnThemeChangeListener> uiMap;
    private Map<View, OnColorChangeListener> viewMap;

    private ThemeManager(DailyAPP dailyAPP) {
        this.dailyAPP = dailyAPP;
    }

    public static void initSelf(DailyAPP dailyAPP) {
        themeManager = new ThemeManager(dailyAPP);
    }

    public static ThemeManager getThemeManager() {
        return themeManager;
    }

    //根据设置读取主题资源
    public void init() {
        Loger.Util(TAG, "init");

        uiMap = new HashMap<>();
        viewMap = new HashMap<>();

        getThemePref();
    }

    public int getMain_layout() {
        return main_layout;
    }

    public int getRead_layout() {
        return read_layout;
    }

    public int getTimeline_layout() {
        return timeline_layout;
    }

    public int getStory_item() {
        return story_item;
    }

    public int getStyle() {
        return style;
    }

    public int getColorPrimary() {
        return colorPrimary;
    }

    public int getColorPrimaryDark() {
        return colorPrimaryDark;
    }

    public int getColorAccent() {
        return colorAccent;
    }

    public void addView(View view, OnColorChangeListener listener) {
        viewMap.put(view, listener);
    }

    public void removeView(View view) {
        viewMap.remove(view);
    }

    public void changeColor(int colorPrimary, int colorPrimaryDark, int colorAccent) {
        this.colorPrimary = colorPrimary;
        this.colorPrimaryDark = colorPrimaryDark;
        this.colorAccent = colorAccent;
        for (OnColorChangeListener listener : viewMap.values()) {
            listener.onColorChange(colorPrimary, colorPrimaryDark, colorAccent);
        }
        saveThemePref();
    }

    public void addUi(Object ui, OnThemeChangeListener listener) {
        uiMap.put(ui, listener);
    }

    public void removeUi(Object ui) {
        uiMap.remove(ui);
    }

    public void changeTheme(int style) {
        setTheme(style);
        for (OnThemeChangeListener listener : uiMap.values()) {
            listener.onThemeChange(style);
        }
        saveThemePref();
    }

    private void setTheme(int style) {
        if (style == style_card) {
            main_layout = R.layout.activity_main_card;
            read_layout = R.layout.read_layout_card;
            timeline_layout = R.layout.timeline_layout;
            story_item = R.layout.story_item_card;
        } else if (style == style_chip) {
            main_layout = R.layout.activity_main;
            read_layout = R.layout.read_layout;
            timeline_layout = R.layout.timeline_layout;
            story_item = R.layout.story_item;
        }
    }

    private void getThemePref() {
        SharedPreferences sp = dailyAPP.getSharedPreferences(theme_pref, Context.MODE_PRIVATE);
        style = sp.getInt(pref_style, 0);
        colorPrimary = dailyAPP.getResources().getColor(R.color.colorPrimary);
        colorPrimaryDark = dailyAPP.getResources().getColor(R.color.colorPrimaryDark);
        colorAccent = dailyAPP.getResources().getColor(R.color.colorAccent);
        if (style == 0) {
            style = style_card;
            saveThemePref();
        } else {
            colorPrimary = sp.getInt(pref_colorPrimary, colorPrimary);
            colorPrimaryDark = sp.getInt(pref_colorPrimaryDark, colorPrimaryDark);
            colorAccent = sp.getInt(pref_colorAccent, colorAccent);
        }
        setTheme(style);
    }

    private void saveThemePref() {
        SharedPreferences.Editor editor = dailyAPP.getSharedPreferences(theme_pref, Context.MODE_PRIVATE).edit();
        editor.putInt(pref_style, style);
        editor.putInt(pref_colorPrimary, colorPrimary);
        editor.putInt(pref_colorPrimaryDark, colorPrimaryDark);
        editor.putInt(pref_colorAccent, colorAccent);
        editor.apply();
    }

    public interface OnThemeChangeListener {
        public void onThemeChange(int style);
    }

    public interface OnColorChangeListener {
        public void onColorChange(int colorPrimary, int colorPrimaryDark, int colorAccent);
    }
}
