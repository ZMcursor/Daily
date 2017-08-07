package com.zmcursor.daily.MVPModel.Struct;

import com.zmcursor.daily.Utils.Utils;

/**
 * Created by ZMcursor on 2017/7/20 0020.
 */

public class Date {

    private int iDate;
    private String sDate;

    public Date(int iDate) {
        this.iDate = iDate;
        sDate = Utils.getDateForInt(iDate);
    }

    public Date(int iDate, String sDate) {
        this.iDate = iDate;
        this.sDate = sDate;
    }

    public int getIntDate() {
        return iDate;
    }

    public String getStringDate() {
        return sDate;
    }
}
