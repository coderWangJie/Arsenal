package com.wangj.login.presenter;

import com.wangj.core.user.UserInfo;
import com.wangj.login.ui.ILoginOnPwdView;

public class ILoginOnPwdPresenterImpl implements ILoginOnPwdPresenter {
    private ILoginOnPwdView iLoginView;

    public ILoginOnPwdPresenterImpl(ILoginOnPwdView iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Override
    public void requestLogin(CharSequence account, CharSequence password) {
        String mockAccount = "222222";
        String mockPassword = "222222";

        if (account.equals(mockAccount) && password.equals(mockPassword)) {
            UserInfo userInfo = new UserInfo();
            iLoginView.onSuccess(userInfo);
        } else {
            iLoginView.onFail("errCode", "errMsg");
        }
    }
}
