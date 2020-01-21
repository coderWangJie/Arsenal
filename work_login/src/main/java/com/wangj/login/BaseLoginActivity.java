package com.wangj.login;

import com.wangj.core.android.BaseActivity;
import com.wangj.core.user.UserInfo;

public abstract class BaseLoginActivity extends BaseActivity {

    public void loginSuccess(UserInfo userInfo) {

    }

    public void loginFail(String errCode, String errMsg) {

    }
}
