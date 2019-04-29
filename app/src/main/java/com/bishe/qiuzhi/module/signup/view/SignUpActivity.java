package com.bishe.qiuzhi.module.signup.view;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.module.signup.contract.SignUpContract;
import com.bishe.qiuzhi.module.signup.presenter.SignUpPresenter;
import com.bishe.qiuzhi.net.OnGsonRespListener;

public class SignUpActivity extends BaseActivity<SignUpPresenter> implements SignUpContract.View {
    private EditText etUserName, etPwd, etPwdConfirm, etEmail, etTel;
    private Button btnSignUp;

    @Override
    protected int getLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void initView() {
        etUserName = findViewById(R.id.et_username);
        etPwd = findViewById(R.id.et_pwd);
        etPwdConfirm = findViewById(R.id.et_pwd_confirm);
        etEmail = findViewById(R.id.et_email);
        etTel = findViewById(R.id.et_tel);
        btnSignUp = findViewById(R.id.btn_sign_up);
    }

    @Override
    protected void initEventAndData() {
        btnSignUp.setOnClickListener(v -> {
            if (!etPwd.getText().toString().equals(etPwdConfirm.getText().toString())) {
                Toast.makeText(mContext, R.string.errorPwdNotSame, Toast.LENGTH_LONG).show();
            } else {
                mPresenter.signUp(etUserName.getText().toString()
                        , etPwd.getText().toString()
                        , etEmail.getText().toString()
                        , etTel.getText().toString());
            }
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter = new SignUpPresenter();
    }

    @Override
    public OnGsonRespListener onSignUpResult() {
        return new OnGsonRespListener() {
            @Override
            public void onSuccess(Object data) {
                finish();
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
            }
        };
    }
}
