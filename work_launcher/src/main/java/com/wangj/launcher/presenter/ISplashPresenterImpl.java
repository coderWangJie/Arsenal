package com.wangj.launcher.presenter;

import android.os.Handler;
import android.os.Message;

import com.wangj.launcher.view.ISplashView;

public class ISplashPresenterImpl implements ISplashPresenter {

    private ISplashView splashView;

    private int delaySecond = 5;

    public ISplashPresenterImpl(ISplashView splashView) {
        this.splashView = splashView;
    }

    @Override
    public void checkInfo() {
        splashView.startSplashView();

        MyHandler handler = new MyHandler();
        handler.sendEmptyMessage(0);
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                if (delaySecond > 0) {
                    splashView.refreshWaiting(--delaySecond);
                    sendEmptyMessageDelayed(0, 1000);
                } else {
                    splashView.initFinsh();
                }
            }
        }
    }
}
