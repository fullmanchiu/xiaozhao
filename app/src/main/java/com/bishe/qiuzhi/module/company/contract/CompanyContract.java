package com.bishe.qiuzhi.module.company.contract;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.net.OnGsonRespListener;

import java.util.List;

public interface CompanyContract {
    interface Model {
    }

    interface View extends BaseView {
        OnGsonRespListener<List<PositionBean>> onGetPosition();
    }

    interface Presenter extends BasePresenter<View> {
        void getPositionByCompanyId(int companyId);
    }
}
