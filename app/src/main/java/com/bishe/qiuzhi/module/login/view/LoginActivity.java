package com.bishe.qiuzhi.module.login.view;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.module.login.contract.LoginContract;
import com.bishe.qiuzhi.module.login.model.LoginModel;
import com.bishe.qiuzhi.module.login.presenter.LoginPresenter;
import com.bishe.qiuzhi.module.signup.view.SignUpActivity;
import com.bishe.qiuzhi.net.OnGsonRespListener;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    private TextView tvSignUp, tvForgotPwd;
    private Button btnSignIn;
    private EditText etUserName, etPwd;
    private AlertDialog alertDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        tvSignUp = findViewById(R.id.tv_sign_up);
        tvForgotPwd = findViewById(R.id.tv_forgot_pwd);
        btnSignIn = findViewById(R.id.btn_sign_in);
        etUserName = findViewById(R.id.et_username);
        etPwd = findViewById(R.id.et_pwd);
    }

    @Override
    protected void initEventAndData() {
        tvSignUp.setOnClickListener(v -> startActivity(new Intent(mContext, SignUpActivity.class)));
        tvForgotPwd.setOnClickListener(v -> {
            showAlert(getString(R.string.forgot_pwd), getString(R.string.forgot_pwd_hint));
        });
        btnSignIn.setOnClickListener(v -> {
            mPresenter.signIn(etUserName.getText().toString(), etPwd.getText().toString());
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter = new LoginPresenter();
    }

    @Override
    public OnGsonRespListener<LoginModel> onSignInResult() {
        return new OnGsonRespListener<LoginModel>() {
            @Override
            public void onSuccess(LoginModel data) {
                App.getInstance().setUserDate(data.getUserinfo());
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
            }
        };
    }

    private void showAlert(String title, String alert) {
        if (alertDialog == null) {
            AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
            builder.setCancelable(true);
            builder.setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss());
            alertDialog = builder.create();
        }
        alertDialog.setTitle(title);
        alertDialog.setMessage(alert);
        alertDialog.show();
    }

}
