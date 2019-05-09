package com.bishe.qiuzhi.module.apply.contract;

import android.os.BaseBundle;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;
import com.bishe.qiuzhi.module.apply.model.ApplyModel;
import com.bishe.qiuzhi.net.OnGsonRespListener;

import java.util.List;

public interface ApplyContract {
    interface Model {
    }

    interface View extends BaseView {
        //获取到数据后的回调
        OnGsonRespListener<List<ApplyModel>> onGetAppyList();
    }

    interface Presenter extends BasePresenter<View> {
        //获取已申请的数据
        void getApplyList();
    }
}
