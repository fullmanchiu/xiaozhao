package com.bishe.qiuzhi.module.fav.contract;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.module.seminar.model.SeminarBean;
import com.bishe.qiuzhi.net.OnGsonRespListener;

import java.util.List;

public interface FavContract {
    interface Model {
    }

    interface View extends BaseView {
        OnGsonRespListener<List<PositionBean>> onGetFavPositionList();

        OnGsonRespListener<List<SeminarBean>> onGetFavSeminarList();
    }

    interface Presenter extends BasePresenter<View> {
        void getFavPositionList();

        void getFavSeminarList();
    }
}
