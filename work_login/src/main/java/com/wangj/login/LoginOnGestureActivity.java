package com.wangj.login;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARouterMapping;
import com.wangj.core.user.UserInfo;
import com.wangj.login.presenter.ILoginOnGesturePresenter;
import com.wangj.login.presenter.ILoginOnGesturePresenterImpl;
import com.wangj.login.ui.ILoginOnGestureView;

@Route(path = ARouterMapping.LoginMapping.LoginOnGesture)
public class LoginOnGestureActivity extends BaseLoginActivity implements ILoginOnGestureView {

    ILoginOnGesturePresenter gesturePresenter;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.login_activity_login_gesture;
    }

    @Override
    protected void registerPresenter() {
        gesturePresenter = new ILoginOnGesturePresenterImpl(this);
    }

    @Override
    protected void initOnCreate() {

    }

    @Override
    public void onSuccess(UserInfo userInfo) {

    }

    @Override
    public void onFail(String errCode, String errMsg) {

    }
}
