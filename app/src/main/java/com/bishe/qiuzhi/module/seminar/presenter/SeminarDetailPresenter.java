package com.bishe.qiuzhi.module.seminar.presenter;

import com.bishe.qiuzhi.module.seminar.contract.SeminarDetailContract;

public class SeminarDetailPresenter implements SeminarDetailContract.Presenter {
    private SeminarDetailContract.View mView;

    @Override
    public void attachView(SeminarDetailContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
