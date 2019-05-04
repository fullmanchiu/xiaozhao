package com.bishe.qiuzhi.module.seminar.view;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.module.seminar.contract.SeminarDetailContract;
import com.bishe.qiuzhi.module.seminar.presenter.SeminarDetailPresenter;

public class SeminarDetailActivity extends BaseActivity<SeminarDetailPresenter> implements SeminarDetailContract.View {

    @Override
    protected int getLayout() {
        return R.layout.activity_seminar_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new SeminarDetailPresenter();
    }
}
