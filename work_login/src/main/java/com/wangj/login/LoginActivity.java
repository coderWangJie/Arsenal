package com.wangj.login;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARoutMapping;
import com.wangj.core.android.BaseActivity;
import com.wangj.core.constant.AppConstant;
import com.wangj.login.presenter.ILoginPresenter;
import com.wangj.login.presenter.ILoginPresenterImpl;
import com.wangj.login.ui.ILoginView;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARoutMapping.LoginMapping.Login)
public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R2.id.textInputLayoutAccount)
    TextInputLayout layoutAccount;
    TextInputEditText editAccount;

    @BindView(R2.id.textInputLayoutPWD)
    TextInputLayout layoutPassword;
    TextInputEditText editPassword;

    private ILoginPresenter loginPresenter;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.login_activity_login;
    }

    @Override
    protected void registerPresenter() {
        loginPresenter = new ILoginPresenterImpl(this);
    }

    @Override
    protected void doBeforeResume() {
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
        loginPresenter.requestLogin(editAccount.getText().toString(),
                editPassword.getText().toString());
    }

    @Override
    public void loginSuccess() {
        finish();
    }

    @Override
    public void loginFail() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }
}
