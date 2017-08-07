package com.zmcursor.daily.MVPModel.NetLink;

import android.accounts.NetworkErrorException;

import com.zmcursor.daily.MVPModel.service.DataService;
import com.zmcursor.daily.Utils.Loger;

import java.io.IOException;

/**
 * Created by ZMcursor on 2017/6/30 0030.
 */

public class RawContent {

    private static final String TAG = "RawContent";

    public static Object getRawContent(String url, boolean isImage) {
        Object content = null;
        try {
//            Loger.NET(TAG, "semaphore  acquire" + DataService.semaphore.availablePermits());

            DataService.semaphore.acquire();

            if (isImage) {
                content = HttpsGet.getImage(url);
            } else {
                content = HttpsGet.getString(url);
            }
            Loger.NET(TAG, "content," + String.valueOf(content == null));

        } catch (IOException | InterruptedException | NetworkErrorException e) {
            e.printStackTrace();
        } finally {
            DataService.semaphore.release();
//            Loger.NET(TAG, "semaphore  release" + DataService.semaphore.availablePermits());
        }
        return content;
    }

//    public static void getRawContent(final String url, final boolean isImage, final OnLoadingCompleteListener onLoadingCompleteListener) {
//
//        final Handler handler = new Handler();
//
//        new Thread(new Runnable() {
//
//            Object content = null;
//
//            @Override
//            public void run() {
//
//                content = getRawContent(url, isImage);
//                Log.e(TAG, "content");
//
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.e(TAG, "handler");
//                        onLoadingCompleteListener.onLoadingComplete(content);
//                    }
//                });
//            }
//        }).start();
//    }
}
