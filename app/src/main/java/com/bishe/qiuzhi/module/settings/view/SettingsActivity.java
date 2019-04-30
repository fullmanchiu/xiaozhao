package com.bishe.qiuzhi.module.settings.view;

import android.widget.Button;
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.module.settings.contract.SettingsContract;
import com.bishe.qiuzhi.module.settings.presenter.SettingsPresenter;
import com.bishe.qiuzhi.net.OnGsonRespListener;

public class SettingsActivity extends BaseActivity<SettingsPresenter> implements SettingsContract.View {
    private Button btnSignOut;

    @Override
    protected int getLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initView() {
        btnSignOut = findViewById(R.id.btn_sign_out);
    }

    @Override
    protected void initEventAndData() {
        btnSignOut.setOnClickListener(v -> mPresenter.signOut());
    }

    @Override
    protected void createPresenter() {
        mPresenter = new SettingsPresenter();
    }

    @Override
    public OnGsonRespListener onSignOut() {
        return new OnGsonRespListener() {
            @Override
            public void onSuccess(Object data) {
                App.getInstance().signOut();
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFail(String error) {
                App.getInstance().signOut();
                setResult(RESULT_OK);
                finish();
                Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
            }
        };
    }
}
