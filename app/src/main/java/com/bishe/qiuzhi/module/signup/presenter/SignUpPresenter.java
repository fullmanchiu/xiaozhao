package com.bishe.qiuzhi.module.signup.presenter;

import com.bishe.qiuzhi.module.signup.contract.SignUpContract;
import com.bishe.qiuzhi.net.Api;

public class SignUpPresenter implements SignUpContract.Presenter {
    SignUpContract.View mView;

    @Override
    public void signUp(String userName, String pwd, String email, String tel) {
        Api.signUp(userName,pwd,email,tel,mView.onSignUpResult());
    }

    @Override
    public void attachView(SignUpContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
