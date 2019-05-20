package com.wangj.launcher;

import android.widget.TextView;

import com.wangj.core.android.BaseActivity;

public class MainActivity extends BaseActivity {

    private TextView tv;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.launcher_activity_main;
    }

    @Override
    protected void registerPresenter() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
