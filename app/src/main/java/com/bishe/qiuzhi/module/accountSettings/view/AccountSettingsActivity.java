package com.bishe.qiuzhi.module.accountSettings.view;

import android.content.Intent;
import android.service.autofill.UserData;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.module.accountSettings.contract.AccountSettingsContract;
import com.bishe.qiuzhi.module.accountSettings.presenter.AccountSettingsPresenter;
import com.bishe.qiuzhi.module.login.model.LoginModel;

public class AccountSettingsActivity extends BaseActivity<AccountSettingsPresenter> implements AccountSettingsContract.View {

    private LinearLayout llUserName, llNickName, llEmail, llMobile, llpwd, llLogout;
    private TextView tvUserName, tvNickName, tvEmail, tvMobile;
    private LoginModel.UserinfoBean userinfoBean;
    private AlertDialog alertDialog;
    private AlertDialog editDialog;

    private TextView tvEditDialogTitle, tvEditDialogName, tvEditDialogHint;
    private EditText etEditDialogValue;

    @Override
    protected int getLayout() {
        return R.layout.activity_account_settings;
    }

    @Override
    protected void initView() {
        llUserName = findViewById(R.id.ll_user_name);
        llNickName = findViewById(R.id.ll_nick_name);
        llEmail = findViewById(R.id.ll_email);
        llMobile = findViewById(R.id.ll_mobile);
        llpwd = findViewById(R.id.ll_pwd);
        llLogout = findViewById(R.id.ll_log_out);
        tvUserName = findViewById(R.id.tv_user_name);
        tvNickName = findViewById(R.id.tv_nick_name);
        tvEmail = findViewById(R.id.tv_email);
        tvMobile = findViewById(R.id.tv_mobile);
    }

    @Override
    protected void initEventAndData() {
        userinfoBean = App.getInstance().getUserData();
        tvUserName.setText(userinfoBean.getUsername());
        tvNickName.setText(userinfoBean.getNickname());
        tvEmail.setText("");
        tvMobile.setText(userinfoBean.getMobile());
        llUserName.setOnClickListener(v -> {
            showAlert(getString(R.string.username_change_title), getString(R.string.username_cannot_change));
        });
        llNickName.setOnClickListener(v -> showEditDialog(getString(R.string.nickname_modify), getString(R.string.text_account_settings_nickname), getString(R.string.nickname), getString(R.string.nickname_modify_hint)));
        llEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        llMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        llpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        llLogout.setOnClickListener(v -> showAlert(getString(R.string.text_account_settings_logout), getString(R.string.username_cannot_change)));
    }

    private void showEditDialog(String title, String name, String etHint, String hint) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = LayoutInflater.from(App.getInstance());
        View view = inflater.inflate(R.layout.edit_dialog_layout, null);

        tvEditDialogTitle = view.findViewById(R.id.tv_title);
        tvEditDialogName = view.findViewById(R.id.tv_name);
        tvEditDialogHint = view.findViewById(R.id.tv_hint);
        etEditDialogValue = view.findViewById(R.id.et_value);
        editDialog = builder.create();
        editDialog.show();
        editDialog.getWindow().setContentView(view);
        tvEditDialogTitle.setText(title);
        tvEditDialogName.setText(name);
        etEditDialogValue.setHint(etHint);
        tvEditDialogHint.setText(hint);
        Window window = editDialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN;
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        params.dimAmount = 0.5f;
        window.setAttributes(params);
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

    @Override
    protected void createPresenter() {
        mPresenter = new AccountSettingsPresenter();
    }
}
