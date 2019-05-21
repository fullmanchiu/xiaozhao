package com.bishe.qiuzhi.module.resume.view;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.module.resume.contract.ResumeContract;
import com.bishe.qiuzhi.module.resume.model.ResumeModel;
import com.bishe.qiuzhi.module.resume.presenter.ResumePresenter;
import com.bishe.qiuzhi.net.OnGsonRespListener;
import com.bishe.qiuzhi.wedgit.TitleBar;

public class ResumeActivity extends BaseActivity<ResumePresenter> implements ResumeContract.View {
    private TextView tvName, tvInfo, tvTel, tvEmail, tvSalary, tvLocation, tvIndustry, tvEdit;
    private TitleBar titleBar;

    @Override
    protected int getLayout() {
        return R.layout.activity_resume;
    }

    @Override
    protected void initView() {
        titleBar = findViewById(R.id.titleBar);
        tvName = findViewById(R.id.tv_nick_name);
        tvInfo = findViewById(R.id.tv_info);
        tvTel = findViewById(R.id.tv_tel);
        tvEmail = findViewById(R.id.tv_email);
        tvSalary = findViewById(R.id.tv_salary);
        tvLocation = findViewById(R.id.tv_location);
        tvIndustry = findViewById(R.id.tv_industry);
        tvEdit = findViewById(R.id.tv_edit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getResume();
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getResume();
        tvEdit.setOnClickListener(v -> startActivity(new Intent(mContext, ResumeEditActivity.class)));
        titleBar.setOnBackClickListener(() -> onBackPressed());
    }

    @Override
    protected void createPresenter() {
        mPresenter = new ResumePresenter();
    }

    @Override
    public OnGsonRespListener<ResumeModel> onGetResume() {
        return new OnGsonRespListener<ResumeModel>() {
            @Override
            public void onSuccess(ResumeModel data) {
                tvName.setText(data.getMy_info().getName());
                tvInfo.setText(data.getMy_info().getSex() + "·现居地" + data.getMy_info().getAddress());
                tvTel.setText(data.getMy_info().getPhone());
                tvEmail.setText(data.getMy_info().getEmail());
                tvSalary.setText(data.getTarget().getWork_salary());
                tvLocation.setText(data.getTarget().getWork_address());
                tvIndustry.setText(data.getTarget().getWork_industry());
                Log.d("aaaaa", data.getMy_info().getName().toString());
            }

            @Override
            public void onFail(String error) {
                showDialog();
                Log.d("aaaaa", error);
            }
        };
    }

    @Override
    public OnGsonRespListener onGetPostResume() {
        return new OnGsonRespListener() {
            @Override
            public void onSuccess(Object data) {

            }

            @Override
            public void onFail(String error) {

            }
        };
    }

    private void showDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setMessage(R.string.resume_empty)
                .setPositiveButton(R.string.resumeDiloagPositiveText
                        , (dialogInterface, which) -> startActivity(
                                new Intent(mContext, ResumeEditActivity.class)))
                .setNegativeButton(R.string.resumeDialogNegativeText
                        , (dialogInterface, which) -> dialogInterface.cancel());
        dialog.show();
    }
}
