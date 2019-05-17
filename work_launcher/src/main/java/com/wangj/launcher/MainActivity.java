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
    protected void initViews() {
        tv = findViewById(R.id.textView);
        tv.setText(BuildConfig.DEBUG ? "debug" : "release");

        tv.setText(tv.getText().toString().concat("\n打包时间：").concat(BuildConfig.ReleaseTime));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
