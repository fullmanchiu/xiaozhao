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

        OnGsonRespListener onFavResult();

        OnGsonRespListener onUnFavResult();

        OnGsonRespListener onSendResult();
    }

    interface Presenter extends BasePresenter<View> {
        void getPositionDetail(int id);

        void FavPosition(int id);

        void UnFavPosition(int id);

        void sendResume(int id);
    }
}
