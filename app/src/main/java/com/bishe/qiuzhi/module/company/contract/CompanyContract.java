package com.bishe.qiuzhi.module.company.contract;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.net.OnGsonRespListener;

public interface CompanyContract {
    interface Model {
    }

    interface View extends BaseView {
        OnGsonRespListener<PositionBean> onGetPosition();
    }

    interface Presenter extends BasePresenter<View> {
        void getPositionByCompanyId(int companyId);
    }
}
