package com.bishe.qiuzhi.module.accountSettings.presenter;

import com.bishe.qiuzhi.module.accountSettings.contract.AccountSettingsContract;

public class AccountSettingsPresenter implements AccountSettingsContract.Presenter {
    private AccountSettingsContract.View mView;

    @Override
    public void attachView(AccountSettingsContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
