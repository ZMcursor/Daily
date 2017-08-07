package com.zmcursor.daily.MVPModel.DataSave;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zmcursor.daily.Conf;
import com.zmcursor.daily.MVPModel.Struct.Date;
import com.zmcursor.daily.MVPModel.Struct.StoriesOfDate;
import com.zmcursor.daily.MVPModel.Struct.Story;
import com.zmcursor.daily.MVPModel.Struct.StoryContent;

import java.util.ArrayList;

/**
 * Created by ZMcursor on 2017/7/13 0013.
 */

public class HistoryDatabase {

    private static final String TAG = "HistoryDatabase";

    private HistoryDatabaseHelper databaseHelper;
//    private Context context;

    public HistoryDatabase(Context context) {
//        this.context = context;
        databaseHelper = new HistoryDatabaseHelper(context);
    }

    public void saveStoriesOfDate(StoriesOfDate storiesOfDate) {
        String table = HistoryDatabaseHelper.getTableName(storiesOfDate.getDate().getIntDate());
        SQLiteDatabase database = databaseHelper.openDatabase();
        databaseHelper.newStoriesOfDateTable(database, table);
        Story story;
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < storiesOfDate.getStories().size(); i++) {
            story = storiesOfDate.getStories().get(i);
            contentValues.put(Conf.id, story.getId());
            contentValues.put(Conf.type, story.getType());
            contentValues.put(Conf.title, story.getTitle());
            contentValues.put(Conf.imageUrl, story.getImageUrl());
            contentValues.put(Conf.ga_prefix, story.getGa_prefix());
//            contentValues.put(Conf.isSaved, story.getId());
            contentValues.put(Conf.isRead, story.isRead() ? Conf.Boolean.I.ordinal() : Conf.Boolean.O.ordinal());
            database.insert(table, null, contentValues);
            contentValues.clear();
        }
        databaseHelper.closeDatabase();
    }

    public StoriesOfDate getStoriesOfDate(Date date) {
        SQLiteDatabase database = databaseHelper.openDatabase();
        Cursor cursor = database.query(HistoryDatabaseHelper.getTableName(date.getIntDate()), null, null, null, null, null, null);
        if (!cursor.moveToFirst()) return null;
        ArrayList<Story> arrayList = new ArrayList<>();
        Story story;
        do {
            story = new Story();
            story.setId(cursor.getInt(cursor.getColumnIndex(Conf.id)));
            story.setType(cursor.getInt(cursor.getColumnIndex(Conf.type)));
            story.setTitle(cursor.getString(cursor.getColumnIndex(Conf.title)));
            story.setImageUrl(cursor.getString(cursor.getColumnIndex(Conf.imageUrl)));
            story.setGa_prefix(cursor.getInt(cursor.getColumnIndex(Conf.ga_prefix)));
//            story.setId(cursor.getInt(cursor.getColumnIndex(Conf.isSaved)));
            story.setIsRead(cursor.getInt(cursor.getColumnIndex(Conf.isRead)) == Conf.Boolean.I.ordinal());
            arrayList.add(story);
        } while (cursor.moveToNext());
        cursor.close();
        databaseHelper.closeDatabase();
        return new StoriesOfDate(date, arrayList);
    }

    public void deleteStoriesOfDate(int date) {
        databaseHelper.openDatabase().execSQL("DROP TABLE " + HistoryDatabaseHelper.getTableName(date));
        databaseHelper.closeDatabase();
    }

    public void saveContent(StoryContent content) {

    }

    public void getContent(int id) {

    }

    public void deleteContent(int id) {

    }
}
