package com.wangj.login.presenter;

import com.wangj.login.ui.ILoginView;

public class ILoginPresenterImpl implements ILoginPresenter {
    private ILoginView iLoginView;

    public ILoginPresenterImpl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }
}
