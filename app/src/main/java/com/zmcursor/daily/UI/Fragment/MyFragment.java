package com.zmcursor.daily.UI.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by ZMcursor on 2017/7/6 0006.
 */

public abstract class MyFragment {

    private View rootView;
    private Context context;

    public MyFragment(Context context) {
        this.context = context;
    }

    public View createView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        rootView = inflater.inflate(getLayout(), null, false);
        onViewCreated();
        return rootView;
    }

    public Context getContext() {
        return context;
    }

    public abstract void onViewCreated();

    public abstract void onDestroyView();

    public abstract void onDestroy();

    public abstract void sync();

    protected abstract void init();

    public abstract int getLayout();

    public View getRootView() {
        return rootView;
    }


}
