package com.zmcursor.daily.UI.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zmcursor.daily.R;
import com.zmcursor.daily.Utils.Loger;

/**
 * Created by ZMcursor on 2017/7/4 0004.
 */

public class SplashFragment extends BaseFragment {

    private static final String TAG = "SplashFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Loger.UI(TAG, "onCreate");
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.splash_layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onResume() {
        Loger.UI(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onDestroy() {
        Loger.UI(TAG, "onDestroy");
        super.onDestroy();
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }
}