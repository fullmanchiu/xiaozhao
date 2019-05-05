package com.bishe.qiuzhi.module.fav.presenter;

import com.bishe.qiuzhi.module.fav.contract.FavContract;
import com.bishe.qiuzhi.net.Api;

public class FavPresenter implements FavContract.Presenter {
    private FavContract.View mView;

    @Override
    public void attachView(FavContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void getFavPositionList() {
        Api.getFavPositionList(mView.onGetFavPositionList());
    }

    @Override
    public void getFavSeminarList() {
        Api.getFavSeminarList(mView.onGetFavSeminarList());
    }
}
