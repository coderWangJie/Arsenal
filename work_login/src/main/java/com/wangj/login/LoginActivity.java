package com.wangj.login;

import com.wangj.core.android.BaseActivity;
import com.wangj.login.presenter.ILoginPresenter;
import com.wangj.login.presenter.ILoginPresenterImpl;
import com.wangj.login.ui.ILoginView;

public class LoginActivity extends BaseActivity implements ILoginView {

    private ILoginPresenter loginPresenterImpl;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.login_activity_login;
    }

    @Override
    protected void registerPresenter() {
        loginPresenterImpl = new ILoginPresenterImpl(this);
    }


}
