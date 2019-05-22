package com.bishe.qiuzhi.module.accountSettings.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.module.accountSettings.contract.AccountSettingsContract;
import com.bishe.qiuzhi.module.accountSettings.model.UploadResult;
import com.bishe.qiuzhi.module.accountSettings.presenter.AccountSettingsPresenter;
import com.bishe.qiuzhi.module.login.model.LoginModel;
import com.bishe.qiuzhi.net.OnGsonRespListener;
import com.wildma.pictureselector.PictureSelector;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

public class AccountSettingsActivity extends BaseActivity<AccountSettingsPresenter> implements AccountSettingsContract.View {

    private LinearLayout llAvatar, llUserName, llNickName, llEmail, llMobile, llpwd, llLogout;
    private TextView tvUserName, tvNickName, tvEmail, tvMobile;
    private LoginModel.UserinfoBean userinfoBean;
    private AlertDialog alertDialog;
    private AlertDialog editDialog;
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String MOBILE = "mobile";


    private TextView tvEditDialogTitle, tvEditDialogName, tvEditDialogHint;
    private EditText etEditDialogValue;
    private Button btnEditDialogSave;

    @Override
    protected int getLayout() {
        return R.layout.activity_account_settings;
    }

    @Override
    protected void initView() {
        llAvatar = findViewById(R.id.ll_user_avatar);
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
        tvEmail.setText(userinfoBean.getEmail());
        tvMobile.setText(userinfoBean.getMobile());
        llAvatar.setOnClickListener(v -> PictureSelector
                .create(mContext, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(true, 200, 200, 1, 1));
        llUserName.setOnClickListener(v -> showAlert(getString(R.string.username_change_title),
                getString(R.string.username_cannot_change)));
        llNickName.setOnClickListener(v -> showEditDialog(getString(R.string.nickname_modify),
                getString(R.string.text_account_settings_nickname),
                getString(R.string.nickname),
                getString(R.string.nickname_modify_hint), NAME));
        llEmail.setOnClickListener(v -> showEditDialog(getString(R.string.email_modify), getString(R.string.text_account_settings_email),
                getString(R.string.email),
                getString(R.string.email_modify_hint), EMAIL));
        llMobile.setOnClickListener(v -> showEditDialog(getString(R.string.mobile_modify_title),
                getString(R.string.mobile),
                getString(R.string.mobile), getString(R.string.mobile_modify_hint), MOBILE));
        llpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(getString(R.string.pwd_modify_title), getString((R.string.pwd_modify_hint)));
            }
        });
        llLogout.setOnClickListener(v -> showAlert(getString(R.string.text_account_settings_logout), getString(R.string.username_cannot_change)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                Log.d("aaaa", "picturePath:" + picturePath);
                mPresenter.uploadAvatar(getFileName(), new File(picturePath));
            }
        }
    }

    public String getFileName() {
        String begin = "img_";
        return begin + UUID.randomUUID();
    }

    private void showEditDialog(String title, String name, String etHint, String hint, String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = LayoutInflater.from(App.getInstance());
        View view = inflater.inflate(R.layout.edit_dialog_layout, null);
        tvEditDialogTitle = view.findViewById(R.id.tv_title);
        tvEditDialogName = view.findViewById(R.id.tv_name);
        tvEditDialogHint = view.findViewById(R.id.tv_hint);
        etEditDialogValue = view.findViewById(R.id.et_value);
        btnEditDialogSave = view.findViewById(R.id.btnSave);
        btnEditDialogSave.setOnClickListener(v -> {
            String temp = etEditDialogValue.getText().toString();
            switch (type) {
                case NAME:
                    tvNickName.setText(temp);
                    App.getInstance().getUserData().setNickname(temp);
                    break;
                case EMAIL:
                    tvEmail.setText(temp);
                    App.getInstance().getUserData().setEmail(temp);
                    break;
                case MOBILE:
                    tvMobile.setText(temp);
                    App.getInstance().getUserData().setMobile(temp);
                    break;
            }
            mPresenter.modifyUser();
            editDialog.dismiss();
        });
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

    @Override
    public OnGsonRespListener onModifyResult() {
        return new OnGsonRespListener() {
            @Override
            public void onSuccess(Object data) {
                Toast.makeText(mContext, "修改成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public OnGsonRespListener<UploadResult> onUploadResult() {
        return new OnGsonRespListener<UploadResult>() {
            @Override
            public void onSuccess(UploadResult data) {
                App.getInstance().getUserData().setAvatar(data.getUrl());
                mPresenter.modifyUser();
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mContext, "头像修改失败", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
