package com.zmcursor.daily.MVPModel.DataSave;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.zmcursor.daily.Conf;
import com.zmcursor.daily.MVPModel.ContentProvider.HistoryData;

/**
 * Created by ZMcursor on 2017/7/20 0020.
 */

public class HistoryDataXML {

    public static final String savedData = "SavedData";
    public static final String savedDate = "SavedDate";
    public static final String savedContent = "savedContent";
    public static final String latestTopStories = "topStoriesJson";
    public static final String latestStories = "storiesJson";

    private Context context;
    private SharedPreferences sp;

    public HistoryDataXML(Context context) {
        this.context = context;
        sp = null;
    }

    public void saveTodayStory(int date, String topStoriesJson, String StoriesJson) {
        Editor editor = getSharedPreferences().edit();
        editor.putInt(Conf.date, date);
        editor.putString(latestTopStories, topStoriesJson);
        editor.putString(latestStories, StoriesJson);
        editor.apply();
    }

    public int getLatestDate() {
        return getSharedPreferences().getInt(Conf.date, 0);
    }

    public String getTodayTopStories() {
        return getSharedPreferences().getString(latestTopStories, null);
    }

    public String getTodayStories() {
        return getSharedPreferences().getString(latestStories, null);
    }

    private SharedPreferences getSharedPreferences() {
        if (sp == null) sp = context.getSharedPreferences(savedData, Context.MODE_PRIVATE);
        return sp;
    }

    public boolean isDateSaved(int date) {
        return haveNum(getSharedPreferences().getString(savedDate, null), date);
    }

    public boolean isContentSaved(int id) {
        return haveNum(getSharedPreferences().getString(savedContent, null), id);
    }

    public boolean saveNewDate(int date, int beforeDate, HistoryData historyData) {
        boolean insert = false;
        String dates = getSharedPreferences().getString(savedDate, null);
        if (dates == null) {
            dates = String.valueOf(date) + ',';
            insert = true;
        } else {
            int l = 0, r = 0, count = 0;

            if (beforeDate == getLatestDate()) {
                dates = date + ',' + dates;
                insert = true;
            }

            while (r < dates.length()) {
                while (dates.charAt(r) != ',') r++;
                count++;
                if (count >= Conf.saveDateNum) {
                    count = l = ++r;
                    while (r < dates.length()) {
                        while (dates.charAt(r) != ',') r++;
                        historyData.deleteStoriesOfDate(Integer.parseInt(dates.substring(l, r)));
                        l = ++r;
                    }
                    dates = dates.substring(0, count);
                    break;
                }
                r++;
                if (!insert && beforeDate == Integer.parseInt(dates.substring(l, r - 1))) {
                    dates = dates.substring(0, r) + date + ',' + dates.substring(r, dates.length());
                    insert = true;
                }
                l = r;
            }
        }
        getSharedPreferences().edit().putString(savedDate, dates).apply();
        return insert;
    }

    public void saveNewContent(int id, HistoryData historyData) {
        String contents = getSharedPreferences().getString(savedContent, null);
        if (contents == null) contents = String.valueOf(id) + ',';
        else {
            contents = String.valueOf(id) + ',' + contents;
            int i = 0, count = 0;
            while (i < contents.length()) {
                while (contents.charAt(i) != ',') i++;
                count++;
                i++;
                if (count >= Conf.saveContentNum) {
                    int l = i;
                    while (i < contents.length()) {
                        while (contents.charAt(i) != ',') i++;
                        historyData.deleteContent(Integer.parseInt(contents.substring(l, i)));
                        i++;
                    }
                    contents = contents.substring(0, i);
                    break;
                }
            }
        }
        getSharedPreferences().edit().putString(savedContent, contents).apply();
    }

    private boolean haveNum(String numes, int num) {
        if (numes == null) return false;
        int l = 0, r = 0;
        while (r < numes.length()) {
            while (numes.charAt(r) != ',') r++;
            if (num == Integer.parseInt(numes.substring(l, r))) return true;
            l = ++r;
        }
        return false;
    }

    public void recycle() {
        sp = null;
    }
}
