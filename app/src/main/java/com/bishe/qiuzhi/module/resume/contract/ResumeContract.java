package com.bishe.qiuzhi.module.resume.contract;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;
import com.bishe.qiuzhi.module.resume.model.ResumeModel;
import com.bishe.qiuzhi.net.OnGsonRespListener;

public interface ResumeContract {
    interface Model {
    }

    interface View extends BaseView {
        OnGsonRespListener<ResumeModel> onGetResume();

        OnGsonRespListener onGetPostResume();
    }

    interface Presenter extends BasePresenter<View> {
        void getResume();

        void postResume(String resume);
    }
}
