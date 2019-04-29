package com.bishe.qiuzhi.module.settings.view;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.module.settings.contract.SettingsContract;
import com.bishe.qiuzhi.module.settings.presenter.SettingsPresenter;

public class SettingsActivity extends BaseActivity<SettingsPresenter> implements SettingsContract.View {

    @Override
    protected int getLayout() {
        return R.layout.activity_settings;
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
}
