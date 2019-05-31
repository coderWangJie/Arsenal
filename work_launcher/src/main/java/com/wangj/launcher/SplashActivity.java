package com.wangj.launcher;

import android.content.Intent;
import android.widget.TextView;

import com.wangj.core.android.BaseActivity;
import com.wangj.core.util.LogUtil;
import com.wangj.launcher.presenter.ISplashPresenter;
import com.wangj.launcher.presenter.ISplashPresenterImpl;
import com.wangj.launcher.view.ISplashView;

import butterknife.BindView;

public class SplashActivity extends BaseActivity implements ISplashView {

    private ISplashPresenter splashPresenter;

    @BindView(R2.id.tvSkip)
    TextView tvSkip;
    @BindView(R2.id.tvBuildTime)
    TextView tvBuildTime;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.launcher_activity_splash;
    }

    @Override
    protected void registerPresenter() {
        splashPresenter = new ISplashPresenterImpl(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        splashPresenter.checkInfo();
    }

    @Override
    public void startSplashView() {
        tvSkip.setText(BuildConfig.DEBUG ? "debug" : "release");

        tvBuildTime.setText("打包时间\n".concat(BuildConfig.ReleaseTime));
    }

    @Override
    public void refreshWaiting(int second) {
        tvSkip.setText(String.valueOf(second).concat("点击跳过"));
    }

    @Override
    public void initFinsh() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Splash页不允许手动退出
    }
}
