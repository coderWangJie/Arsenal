package com.wangj.login.presenter;

import com.wangj.login.ui.ILoginView;

public class ILoginPresenterImpl implements ILoginPresenter {
    private ILoginView iLoginView;

    public ILoginPresenterImpl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Override
    public void requestLogin(String account, String password) {
        String mockAccount = "222222";
        String mockPassword = "222222";

        if (account.equals(mockAccount) && password.equals(mockPassword)) {
            iLoginView.loginSuccess();
        } else {
            iLoginView.loginFail();
        }
    }
}
