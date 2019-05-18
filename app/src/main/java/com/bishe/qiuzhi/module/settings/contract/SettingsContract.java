package com.bishe.qiuzhi.module.settings.contract;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;
import com.bishe.qiuzhi.net.OnGsonRespListener;

public interface SettingsContract {
    interface Model {
    }

    interface View extends BaseView {
        OnGsonRespListener onSignOut();

        OnGsonRespListener onCheckUpdate();
    }

    interface Presenter extends BasePresenter<View> {
        void signOut();

        void checkUpdate();
    }
}
