package com.zmcursor.daily.UI.Fragment;

import com.zmcursor.daily.MVPPresenter.MenuPresenter;
import com.zmcursor.daily.R;
import com.zmcursor.daily.UI.Activity.MainActivity;

/**
 * Created by ZMcursor on 2017/7/5 0005.
 */

public class MenuFragment extends MyFragment {

    private MainActivity mainActivity;
    private MenuPresenter presenter;

    public MenuFragment(MainActivity mainActivity) {
        super(mainActivity);
        this.mainActivity = mainActivity;
        presenter = new MenuPresenter(this, mainActivity);
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void sync() {

    }

    @Override
    protected void init() {

    }

    @Override
    public int getLayout() {
        return R.layout.menu_layout;
    }
}
