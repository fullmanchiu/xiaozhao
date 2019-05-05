package com.bishe.qiuzhi.module.apply.presenter;

import com.bishe.qiuzhi.module.apply.contract.ApplyContract;
import com.bishe.qiuzhi.net.Api;

public class ApplyPresenter implements ApplyContract.Presenter {
    private ApplyContract.View mView;

    @Override
    public void attachView(ApplyContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void getApplyList() {
        Api.getApplyList(mView.onGetAppyList());
    }
}

