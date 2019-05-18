package com.bishe.qiuzhi.module.search.presenter;

import com.bishe.qiuzhi.module.search.contract.SearchContract;
import com.bishe.qiuzhi.net.Api;

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View mView;

    @Override
    public void attachView(SearchContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }


    @Override
    public void searchPosition(String search) {
        Api.getPositionData(search,mView.onGetSearchPositionResult());
    }

    @Override
    public void searchSeminar(String search) {
        Api.getSeminarData(search,mView.onGetSearchSeminarResult());
    }
}
