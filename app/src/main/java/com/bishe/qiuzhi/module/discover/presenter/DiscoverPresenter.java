package com.bishe.qiuzhi.module.discover.presenter;

import com.bishe.qiuzhi.app.BaseView;
import com.bishe.qiuzhi.module.discover.contract.DiscoverContract;

public class DiscoverPresenter implements DiscoverContract.Presenter {
    private DiscoverContract.View mView;

    @Override
    public void attachView(DiscoverContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
