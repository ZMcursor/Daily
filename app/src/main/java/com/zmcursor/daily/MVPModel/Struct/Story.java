package com.zmcursor.daily.MVPModel.Struct;

/**
 * Created by ZMcursor on 2017/7/20 0020.
 */

public class Story extends EStory {

    private boolean isRead = false;

    public Story() {
        super(0, 0, null, null, null, false, 0);
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsRead(boolean read) {
        isRead = read;
    }
}
