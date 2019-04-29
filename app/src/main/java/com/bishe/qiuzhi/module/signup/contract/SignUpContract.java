package com.bishe.qiuzhi.module.signup.contract;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;
import com.bishe.qiuzhi.net.OnGsonRespListener;

public interface SignUpContract {
    interface Model {
    }

    interface View extends BaseView {
        OnGsonRespListener onSignUpResult();
    }

    interface Presenter extends BasePresenter<View> {
        void signUp(String userName, String pwd, String email, String tel);
    }
}
