package com.bishe.qiuzhi.module.resume.presenter;

import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.module.resume.contract.ResumeContract;
import com.bishe.qiuzhi.net.Api;

public class ResumePresenter implements ResumeContract.Presenter {
    private ResumeContract.View mView;

    @Override
    public void attachView(ResumeContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void getResume() {
        Api.getResume(App.getInstance().getUserData().getUser_id(), mView.onGetResume());
    }

    @Override
    public void postResume(String resume) {
        Api.postResume(App.getInstance().getUserData().getUser_id(), resume, mView.onGetPostResume());
    }
}
