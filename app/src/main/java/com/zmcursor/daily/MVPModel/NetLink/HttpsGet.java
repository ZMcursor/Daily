package com.zmcursor.daily.MVPModel.NetLink;

import android.accounts.NetworkErrorException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zmcursor.daily.Utils.Loger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ZMcursor on 2017/6/30 0030.
 */

public class HttpsGet {

    private static final String TAG = "HttpsGet";

    public static Bitmap getImage(String url) throws IOException, NetworkErrorException {
        HttpURLConnection httpURLConnection = getURLConnection(url);
        if (httpURLConnection == null) return null;
        InputStream inputStream = httpURLConnection.getInputStream();
        if (inputStream == null) return null;

        Loger.NET(TAG, "getImageString,inputStream");

        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

        Loger.NET(TAG, "getImageString,decodeStream");

        inputStream.close();
        httpURLConnection.disconnect();

        Loger.NET(TAG, "getImageString,close");

        Loger.NET(TAG, "bitmap" + bitmap.getByteCount());

        return bitmap;
    }

    public static String getString(String url) throws IOException, NetworkErrorException {
        HttpURLConnection httpURLConnection = getURLConnection(url);
        if (httpURLConnection == null) return null;
        InputStream inputStream = httpURLConnection.getInputStream();
        if (inputStream == null) return null;

        Loger.NET(TAG, "getString,inputStream");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 模板代码 必须熟练
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(buffer)) != -1) {
            Loger.NET(TAG, String.valueOf(len));
            byteArrayOutputStream.write(buffer, 0, len);
        }
        Loger.NET(TAG, "byteArrayOutputStream.write");
        inputStream.close();
        Loger.NET(TAG, "inputStream.close");
        String string = byteArrayOutputStream.toString();// 把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
        byteArrayOutputStream.close();
        Loger.NET(TAG, "byteArrayOutputStream.close");
        httpURLConnection.disconnect();
        return string;
    }

    private static HttpURLConnection getURLConnection(String url) throws IOException, NetworkErrorException {
        HttpURLConnection httpURLConnection;

        Loger.NET(TAG, "getURLConnection," + url);

        // 利用string url构建URL对象
        httpURLConnection = (HttpURLConnection) ((new URL(url)).openConnection());
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setConnectTimeout(10000);

        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            return httpURLConnection;
        } else {
            throw new NetworkErrorException("response status is " + responseCode);
        }
    }
}
