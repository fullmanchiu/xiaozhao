package com.bishe.qiuzhi.module.discover.contract;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;

public interface DiscoverContract {
    interface Model {
    }

    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
