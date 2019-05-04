package com.bishe.qiuzhi.module.company.presenter;

import com.bishe.qiuzhi.module.company.contract.CompanyContract;
import com.bishe.qiuzhi.net.Api;

public class CompanyPresenter implements CompanyContract.Presenter {
    private CompanyContract.View mView;

    @Override
    public void getPositionByCompanyId(int companyId) {
        Api.getPositionByCompanyId(companyId, mView.onGetPosition());
    }

    @Override
    public void attachView(CompanyContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
