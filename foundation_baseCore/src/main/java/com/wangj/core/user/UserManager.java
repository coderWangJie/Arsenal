package com.wangj.core.user;

public class UserManager {
    private static final String TAG = UserManager.class.getSimpleName();

    private static boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void login(UserInfo userInfo) {
        isLogin = true;
    }

    public void logout() {
        isLogin = false;
    }
}
