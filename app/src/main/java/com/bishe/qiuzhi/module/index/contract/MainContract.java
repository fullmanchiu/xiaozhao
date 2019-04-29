package com.bishe.qiuzhi.module.index.contract;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;

public interface MainContract {
    interface Model {
    }

    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
