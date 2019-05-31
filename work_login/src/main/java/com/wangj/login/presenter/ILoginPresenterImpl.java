package com.wangj.login.presenter;

import com.wangj.core.util.LogUtil;
import com.wangj.login.ui.ILoginView;

public class ILoginPresenterImpl implements ILoginPresenter {
    private ILoginView iLoginView;

    public ILoginPresenterImpl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Override
    public void requestLogin(String account, String password) {
        double result = Math.random();
        LogUtil.e("WangJ", "随机结果：" + result);
        if (result > 0.5) {
            iLoginView.loginSuccess();
        } else {
            iLoginView.loginFail();
        }
    }
}
