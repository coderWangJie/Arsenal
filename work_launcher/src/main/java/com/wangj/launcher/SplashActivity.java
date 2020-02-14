package com.wangj.launcher;

import android.content.Intent;
import android.widget.TextView;

import com.wangj.core.android.BaseActivity;
import com.wangj.core.util.AppVersionUtil;
import com.wangj.launcher.presenter.ISplashPresenter;
import com.wangj.launcher.presenter.ISplashPresenterImpl;
import com.wangj.launcher.view.ISplashView;

import butterknife.BindView;

public class SplashActivity extends BaseActivity implements ISplashView {

    private ISplashPresenter splashPresenter;

    @BindView(R2.id.tvSkip)
    TextView tvSkip;
    @BindView(R2.id.tvVersionInfo)
    TextView tvVersion;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.launcher_activity_splash;
    }

    @Override
    protected void registerPresenter() {
        splashPresenter = new ISplashPresenterImpl(this);
    }

    @Override
    protected void initOnCreate() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        splashPresenter.checkInfo();
    }

    @Override
    public void startSplashView() {
        tvVersion.setText((BuildConfig.DEBUG ? "Debug" : "Release")
                .concat("\n").concat(BuildConfig.ReleaseTime)
                .concat("\nv").concat(AppVersionUtil.getVersionName(this)));
    }

    @Override
    public void refreshWaiting(int second) {
        tvSkip.setText(String.valueOf(second).concat("点击跳过"));
    }

    @Override
    public void countdownFinish() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Splash页不允许手动退出
    }
}
