package com.bishe.qiuzhi.module.seminar.presenter;

import com.bishe.qiuzhi.module.seminar.contract.SeminarDetailContract;
import com.bishe.qiuzhi.net.Api;

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

    @Override
    public void getSeminarDetail(int id) {
        Api.getSeminarDetail(id, mView.onGetSeminarDetail());
    }

    @Override
    public void unFavSeminar(int id) {
        Api.unFavSeminar(id, mView.onUnFavResult());
    }

    @Override
    public void favSeminar(int id) {
        Api.favSeminar(id, mView.onFavResult());
    }
}
