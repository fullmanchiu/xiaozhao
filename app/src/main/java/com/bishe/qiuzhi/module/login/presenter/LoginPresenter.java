package com.bishe.qiuzhi.module.login.presenter;

import com.bishe.qiuzhi.module.login.contract.LoginContract;
import com.bishe.qiuzhi.net.Api;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;

    @Override
    public void attachView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void signIn(String userName, String pwd) {
        Api.signIn(userName,pwd,mView.onSignInResult());
    }
}
