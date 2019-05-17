package com.wangj.launcher.presenter;

import com.wangj.launcher.view.ISplashView;

public class ISplashPresenterImpl implements ISplashPresenter {

    private ISplashView splashView;

    public ISplashPresenterImpl(ISplashView splashView) {
        this.splashView = splashView;
    }

    @Override
    public void checkInfo() {
        splashView.startSplashView();
    }
}
