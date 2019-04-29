package com.bishe.qiuzhi.module.settings.presenter;

import com.bishe.qiuzhi.module.settings.contract.SettingsContract;

public class SettingsPresenter implements SettingsContract.Presenter {
    private SettingsContract.View mView;

    @Override
    public void attachView(SettingsContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
