package com.bishe.qiuzhi.module.fav.contract;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;
import com.bishe.qiuzhi.module.fav.model.FavModel;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.net.OnGsonRespListener;

import java.util.List;

public interface FavContract {
    interface Model {
    }

    interface View extends BaseView {
        OnGsonRespListener<List<PositionBean>> onGetFavPositionList();

        OnGsonRespListener<List<FavModel.FavSeminarModel>> onGetFavSeminarList();
    }

    interface Presenter extends BasePresenter<View> {
        void getFavPositionList();

        void getFavSeminarList();
    }
}
