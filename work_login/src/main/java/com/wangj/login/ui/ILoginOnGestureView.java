package com.wangj.login.ui;

import com.wangj.core.user.UserInfo;

public interface ILoginOnGestureView {
    void onSuccess(UserInfo userInfo);
    void onFail(String errCode, String errMsg);
}
