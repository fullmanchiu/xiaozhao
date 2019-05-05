package com.bishe.qiuzhi.module.position.presenter;

import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.module.position.contract.PositionDetailContract;
import com.bishe.qiuzhi.net.Api;

import java.net.IDN;

public class PositionDetailPresenter implements PositionDetailContract.Presenter {
    private PositionDetailContract.View mView;

    @Override
    public void attachView(PositionDetailContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void getPositionDetail(int id) {
        Api.getPositionDetail(id, mView.onGetPositionDetail());
    }

    @Override
    public void FavPosition(int id) {
        Api.favPosition(id, mView.onFavResult());
    }

    @Override
    public void UnFavPosition(int id) {
        Api.unFavPosition(id,mView.onUnFavResult());
    }
}
