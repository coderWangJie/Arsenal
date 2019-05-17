package com.wangj.launcher;

import android.widget.TextView;

import com.wangj.core.android.BaseActivity;
import com.wangj.launcher.presenter.ISplashPresenter;
import com.wangj.launcher.presenter.ISplashPresenterImpl;
import com.wangj.launcher.view.ISplashView;

public class SplashActivity extends BaseActivity implements ISplashView {

    private ISplashPresenter splashPresenter;

    private TextView tv;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.launcher_activity_splash;
    }

    @Override
    protected void registerPresenter() {
        splashPresenter = new ISplashPresenterImpl(this);
    }

    @Override
    protected void initViews() {
        tv = findViewById(R.id.textView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        splashPresenter.checkInfo();
    }

    @Override
    public void startSplashView() {
//        tv.setText(BuildConfig.DEBUG ? "debug" : "release");
//        tv.setText(tv.getText().toString().concat("\n打包时间：").concat(BuildConfig.ReleaseTime));
    }

    @Override
    public void refreshWaitting() {

    }

    @Override
    public void initFinsh() {

    }
}
