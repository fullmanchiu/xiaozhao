package com.bishe.qiuzhi.module.accountSettings.contract;

import com.bishe.qiuzhi.app.BasePresenter;
import com.bishe.qiuzhi.app.BaseView;
import com.bishe.qiuzhi.module.accountSettings.model.UploadResult;
import com.bishe.qiuzhi.net.OnGsonRespListener;

import java.io.File;

public interface AccountSettingsContract {
    interface Model {
    }

    interface View extends BaseView {
        OnGsonRespListener onModifyResult();

        OnGsonRespListener<UploadResult> onUploadResult();
    }

    interface Presenter extends BasePresenter<View> {
        void modifyUser();

        void uploadAvatar(String name, File file);
    }
}
