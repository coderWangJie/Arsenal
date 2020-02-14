package com.wangj.launcher.presenter;

import android.os.Handler;
import android.os.Message;

import com.wangj.core.util.LogUtil;
import com.wangj.launcher.view.ISplashView;

public class ISplashPresenterImpl implements ISplashPresenter {
    private static final String TAG = ISplashPresenterImpl.class.getSimpleName();

    private ISplashView splashView;

    private int delaySecond = 5;
    private MyHandler handler;
    private int msgWhat = 0;

    public ISplashPresenterImpl(ISplashView splashView) {
        this.splashView = splashView;
        handler = new MyHandler();
    }

    @Override
    public void start() {
        splashView.startSplashView();

        handler.sendEmptyMessage(msgWhat);
    }

    @Override
    public void stop() {
        LogUtil.d(TAG, "stop");

        if (handler.hasMessages(msgWhat)) {
            LogUtil.d(TAG, "handler's MessageQueue not null and clear");
            handler.removeMessages(msgWhat);
        }
        handler = null;
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                if (delaySecond >= 0) {
                    splashView.refreshWaiting(delaySecond--);
                    sendEmptyMessageDelayed(msgWhat, 1000);
                } else {
                    splashView.countdownFinish();
                }
            }
        }
    }
}
