package com.zmcursor.daily.MVPModel.DataSave;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zmcursor.daily.MVPModel.Struct.StoriesOfDate;
import com.zmcursor.daily.MVPModel.Struct.TodayTopStories;
import com.zmcursor.daily.MVPModel.service.DataService;
import com.zmcursor.daily.Utils.Loger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ZMcursor on 2017/7/31 0031.
 */

public class HistoryImage {

    private static final String TAG = "HistoryImage";

    private Context context;

    public HistoryImage(Context context) {
        this.context = context;
    }

    public void saveImageOfDate(StoriesOfDate storiesOfDate) {
        File imageFile = new File(context.getFilesDir(), getFileNameOfDate(storiesOfDate.getDate().getIntDate()));
        if (imageFile.mkdir()) {
            Loger.Util(TAG, "saveImageOfDate " + imageFile.getPath());
            File image;
            for (int i = 0; i < storiesOfDate.getStories().size(); i++) {
                image = new File(imageFile, String.valueOf(storiesOfDate.getStories().get(i).getId()));
                Loger.Util(TAG, "saveImageOfDate " + image.getPath());
                saveBitmap(image, storiesOfDate.getStories().get(i).getImageBitmap());
            }
        }
    }

    public void saveTopImage(TodayTopStories todayTopStories) {
        File imageFile = new File(context.getFilesDir(), "top_stories/");
        if (imageFile.exists() || imageFile.mkdir()) {
            Loger.Util(TAG, "saveImageOfDate " + imageFile.getPath());
            File image;
            for (int i = 0; i < todayTopStories.getTodayTopStories().size(); i++) {
                image = new File(imageFile, String.valueOf(todayTopStories.getTodayTopStories().get(i).getId()));
                Loger.Util(TAG, "saveImageOfDate " + image.getPath());
                saveBitmap(image, todayTopStories.getTodayTopStories().get(i).getImageBitmap());
            }
        }
    }

    public void getImageOfDate(StoriesOfDate storiesOfDate) throws FileNotFoundException {
        File imageFile = new File(context.getFilesDir(), getFileNameOfDate(storiesOfDate.getDate().getIntDate()));
        if (imageFile.exists()) {
            Bitmap bitmap;
            File image;
            FileInputStream fis;
            for (int i = 0; i < storiesOfDate.getStories().size(); i++) {
                image = new File(imageFile, String.valueOf(storiesOfDate.getStories().get(i).getId()));
                fis = new FileInputStream(image);
                bitmap = BitmapFactory.decodeStream(fis);
                storiesOfDate.getStories().get(i).setImageBitmap(bitmap);
            }
        }
    }

    public void deleteImageOfDate(int date) {
        File imageFile = new File(context.getFilesDir(), getFileNameOfDate(date));
        for (File file : imageFile.listFiles()) {
            file.delete();
        }
        imageFile.delete();
    }

    private String getFileNameOfDate(int date) {
        return "Date" + date + '/';
    }

    private void saveBitmap(File file, Bitmap bitmap) {
        new Thread(() -> {
            try {
                DataService.semaphore.acquire();
                FileOutputStream fos = new FileOutputStream(file, false);
                bitmap.compress(Bitmap.CompressFormat.WEBP, 75, fos);
                fos.flush();
                fos.close();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                DataService.semaphore.release();
            }
        }).start();
    }
}
