package com.bishe.qiuzhi.module.company.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.module.company.contract.CompanyContract;
import com.bishe.qiuzhi.module.company.presenter.CompanyPresenter;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.net.OnGsonRespListener;

public class CompanyActivity extends BaseActivity<CompanyPresenter> implements CompanyContract.View {

    @Override
    protected int getLayout() {
        return R.layout.activity_company;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public OnGsonRespListener<PositionBean> onGetPosition() {
        return new OnGsonRespListener<PositionBean>() {
            @Override
            public void onSuccess(PositionBean data) {

            }

            @Override
            public void onFail(String error) {

            }
        };
    }
}
