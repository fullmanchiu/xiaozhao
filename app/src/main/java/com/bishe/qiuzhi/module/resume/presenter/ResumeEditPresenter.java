package com.bishe.qiuzhi.module.resume.presenter;

import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.module.resume.contract.ResumeEditContract;
import com.bishe.qiuzhi.net.Api;

public class ResumeEditPresenter implements ResumeEditContract.Presenter {
    private ResumeEditContract.View mView;

    @Override
    public void attachView(ResumeEditContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void postResume(String toJson) {
        Api.postResume(App.getInstance().getUserData().getUser_id(), toJson, mView.onGetPostResume());
    }

    @Override
    public void getResume() {
        Api.getResume(App.getInstance().getUserData().getUser_id(), mView.onGetResume());
    }
}
