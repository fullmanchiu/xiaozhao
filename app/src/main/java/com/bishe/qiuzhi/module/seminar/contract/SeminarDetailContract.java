package com.bishe.qiuzhi.module.seminar.contract;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;
import com.bishe.qiuzhi.module.seminar.model.SeminarDetailModel;
import com.bishe.qiuzhi.net.OnGsonRespListener;

public interface SeminarDetailContract {
    interface Model {
    }

    interface View extends BaseView {
        OnGsonRespListener<SeminarDetailModel> onGetSeminarDetail();

        OnGsonRespListener onFavResult();

        OnGsonRespListener onUnFavResult();
    }

    interface Presenter extends BasePresenter<View> {
        void getSeminarDetail(int id);

        void unFavSeminar(int id);

        void favSeminar(int id);
    }
}
