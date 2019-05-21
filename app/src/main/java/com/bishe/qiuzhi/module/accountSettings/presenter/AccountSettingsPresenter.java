package com.bishe.qiuzhi.module.accountSettings.presenter;

import com.bishe.qiuzhi.module.accountSettings.contract.AccountSettingsContract;
import com.bishe.qiuzhi.net.Api;

import java.io.File;

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

    @Override
    public void modifyUser() {
        Api.modifyUser(mView.onModifyResult());
    }

    @Override
    public void uploadAvatar(String name, File file) {
        Api.uploadAvatar(name, file, mView.onUploadResult());
    }
}
