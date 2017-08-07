package com.zmcursor.daily.MVPModel.DataSave;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zmcursor.daily.Conf;

/**
 * Created by ZMcursor on 2017/7/28 0028.
 */

public class HistoryDatabaseHelper extends SQLiteOpenHelper {

    private static final String DatabaseName = "HistoryData.db";

    public static final String ContentTable = "HistoryContent";
//    public static final String SavedDateTable = "SavedDate";

    public static final String KEY = "key";

//    private Context context;

    private int connect = 0;

    public HistoryDatabaseHelper(Context context) {
        this(context, 1);
    }

    public HistoryDatabaseHelper(Context context, int version) {
        this(context, DatabaseName, null, version);
    }

    public HistoryDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version, null);
    }

    public HistoryDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String CREATE_TABLE = "create table if not exists " + SavedDateTable + " ( "
//                + KEY + " integer primary key autoincrement , "
//                + Conf.date + " text , " + " )";
//        db.execSQL(CREATE_TABLE);

//        CREATE_TABLE = "create table if not exists " + ContentTable + " ( "
//                + Conf.id + " integer primary key , "
//                + Conf.type + " integer , "
//                + Conf.title + " text , "
//                + Conf.imageUrl + " text , "
//                + Conf.share_url + " text , "
//                + Conf.share_url + " text , "
//                + Conf.ga_prefix + " integer , "
//                + Conf.css + " text , "
//                + Conf.body + " text , " + " )";
//        db.execSQL(CREATE_TABLE);
    }

    public synchronized SQLiteDatabase openDatabase() {
        connect++;
        return getWritableDatabase();
    }

    public synchronized void closeDatabase() {
        connect--;
        if (connect == 0) {
            close();
        }
    }

    public void newStoriesOfDateTable(SQLiteDatabase db, String table) {
        String CREATE_TABLE = "create table if not exists " + table + " ( "
                + KEY + " integer primary key autoincrement , "
                + Conf.id + " integer , "
                + Conf.type + " integer , "
                + Conf.title + " text , "
                + Conf.imageUrl + " text , "
                + Conf.ga_prefix + " integer , "
//                + Conf.isSaved + " integer , "
                + Conf.isRead + " integer " + " )";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static String getTableName(int date) {
        return "DateTable" + date;
    }
}
