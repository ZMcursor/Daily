package com.zmcursor.daily.MVPPresenter;

import com.zmcursor.daily.UI.Activity.MainActivity;
import com.zmcursor.daily.UI.Fragment.MenuFragment;
import com.zmcursor.daily.Utils.Loger;

/**
 * Created by ZMcursor on 2017/7/21 0021.
 */

public class MenuPresenter {

    private static final String TAG = "MenuPresenter";

    private MenuFragment menuFragment;
    private MainActivity mainActivity;

    public MenuPresenter(MenuFragment menuFragment, MainActivity mainActivity) {
        this.menuFragment = menuFragment;
        this.mainActivity = mainActivity;
    }

    public void init() {
        Loger.UI(TAG, "init");
    }
}
