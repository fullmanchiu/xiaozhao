package com.bishe.qiuzhi.module.search.contract;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.module.seminar.model.SeminarBean;
import com.bishe.qiuzhi.net.OnGsonRespListener;

import java.util.List;

public interface SearchContract {
    interface Model {
    }

    interface View extends BaseView {
        OnGsonRespListener<List<PositionBean>> onGetSearchPositionResult();

        OnGsonRespListener<List<SeminarBean>> onGetSearchSeminarResult();
    }

    interface Presenter extends BasePresenter<View> {
        void searchPosition(String search);

        void searchSeminar(String search);
    }
}
