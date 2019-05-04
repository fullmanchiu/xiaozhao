package com.bishe.qiuzhi.module.position.contract;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;
import com.bishe.qiuzhi.module.position.model.PositionDetailModel;
import com.bishe.qiuzhi.net.OnGsonRespListener;

public interface PositionDetailContract {
    interface Model {
    }

    interface View extends BaseView {
        OnGsonRespListener<PositionDetailModel> onGetPositionDetail();
    }

    interface Presenter extends BasePresenter<View> {
        void getPositionDetail(int id);
    }
}
