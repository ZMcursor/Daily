package com.zmcursor.daily.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zmcursor.daily.Conf;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by ZMcursor on 2017/6/30 0030.
 */

public class Utils {

    private static final String TAG = "Utils";

//public int sizeOfArrayLists()

    public static String getDateForInt(int date) {
        int year, month, day;
        year = date / 10000;
        month = (date / 100) % 100;
        day = date % 100;
        return (String.valueOf(year) + "年" + String.valueOf(month) + "月" + String.valueOf(day) + "日");
    }

    public static int dateBefore(int date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        Calendar ca = Calendar.getInstance();
        ca.set(date / 10000, (date / 100) % 100 - 1, date % 100);
        ca.add(Calendar.DAY_OF_MONTH, -1);
        Loger.Util(TAG, sdf.format(ca.getTime()));
        return Integer.parseInt(sdf.format(ca.getTime()));
    }

    //    public static String getFileName(){
//
//    }
//
//    public static int pd2px(Context context, int dp) {
//        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
//    }
//
    public static int getStatusHeigth(Context context) {
        return context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("status_bar_height", "dimen", "android"));
    }

    //
    public static int getNavigationHeigth(Context context) {
        return context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    public static void setStatusBarPadding(View view) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + Conf.getConf().getStatusBarHeight(), view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void setNavigationBarPadding(View view) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom() + Conf.getConf().getNavigationBarHeight());
    }

    public static void setWindows(Activity activity) {
        Window window = activity.getWindow();
//        window.requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

//    public static int[] string2int(String dates) {
//        int[] dateInt = new int[Conf.saveDateNum + 1];
//        dateInt[0] = 0;
//        int l = 0, r = 0;
//        while (r < dates.length()) {
//            while (r < dates.length() && dates.charAt(r) != ',') r++;
//            dateInt[++dateInt[0]] = Integer.parseInt(dates.substring(l, r));
//            l = ++r;
//        }
//        return dateInt;
//    }
//
//    public static String int2String(int[] dateInt) {
//        String dates = String.valueOf(dateInt[1]);
//        for (int i = 2; i <= dateInt[0]; i++) {
//            dates = dates + ',' + String.valueOf(dateInt[i]);
//        }
//        return dates;
//    }

}
