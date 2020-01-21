package com.wangj.login;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARouterMapping;
import com.wangj.core.constant.AppConstant;
import com.wangj.core.user.UserInfo;
import com.wangj.login.presenter.ILoginOnPwdPresenter;
import com.wangj.login.presenter.ILoginOnPwdPresenterImpl;
import com.wangj.login.ui.ILoginOnPwdView;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouterMapping.LoginMapping.LoginOnPassword)
public class LoginOnPasswordActivity extends BaseLoginActivity implements ILoginOnPwdView {

    @BindView(R2.id.textInputLayoutAccount)
    TextInputLayout layoutAccount;
    TextInputEditText editAccount;

    @BindView(R2.id.textInputLayoutPWD)
    TextInputLayout layoutPassword;
    TextInputEditText editPassword;

    private ILoginOnPwdPresenter loginPresenter;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.login_activity_login_password;
    }

    @Override
    protected void registerPresenter() {
        loginPresenter = new ILoginOnPwdPresenterImpl(this);
    }

    @Override
    protected void initOnCreate() {
        editAccount = (TextInputEditText) layoutAccount.getEditText();
        editPassword = (TextInputEditText) layoutPassword.getEditText();

        editAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > AppConstant.LOGIN_ACCOUNT_LENGTH_MAX) {
                    layoutAccount.setError("长度超限");
                } else {
                    layoutAccount.setError("");
                }
            }
        });

        editAccount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (((TextInputEditText) v).getText().length() < AppConstant.LOGIN_ACCOUNT_LENGTH_MIN) {
                        layoutAccount.setError("长度不够");
                    }
                }
            }
        });

        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > AppConstant.LOGIN_PASSWORD_LENGTH_MAX) {
                    layoutPassword.setError("长度超限");
                } else {
                    layoutPassword.setErrorEnabled(false);
                }
            }
        });
    }

    @OnClick(R2.id.btnLogin)
    void actionLogin() {
        loginPresenter.requestLogin(editAccount.getText(), editPassword.getText());
    }

    @Override
    public void onSuccess(UserInfo userInfo) {
        loginSuccess(userInfo);
    }

    @Override
    public void onFail(String errCode, String errMsg) {
        loginFail(errCode, errMsg);
    }
}
