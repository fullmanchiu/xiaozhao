package com.bishe.qiuzhi.module.index.presenter;

import com.bishe.qiuzhi.module.index.contract.MainContract;

public class MainPresenterImpl implements MainContract.Presenter {
    private MainContract.View mView;

    @Override
    public void attachView(MainContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
