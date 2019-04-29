package com.bishe.qiuzhi.module.login.contract;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;
import com.bishe.qiuzhi.module.login.model.LoginModel;
import com.bishe.qiuzhi.net.OnGsonRespListener;

public interface LoginContract {
    interface Model {
    }

    interface View extends BaseView {
        OnGsonRespListener<LoginModel> onSignInResult();
    }

    interface Presenter extends BasePresenter<View> {
        void signIn(String userName, String pwd);
    }
}
