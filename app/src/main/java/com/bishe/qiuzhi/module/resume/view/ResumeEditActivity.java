package com.bishe.qiuzhi.module.resume.view;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.module.login.model.LoginModel;
import com.bishe.qiuzhi.module.resume.contract.ResumeEditContract;
import com.bishe.qiuzhi.module.resume.model.ResumeModel;
import com.bishe.qiuzhi.module.resume.presenter.ResumeEditPresenter;
import com.bishe.qiuzhi.net.OnGsonRespListener;
import com.google.gson.Gson;

public class ResumeEditActivity extends BaseActivity<ResumeEditPresenter> implements ResumeEditContract.View {

    private TextView tvSave;
    private EditText etName, etBirth, etAddress, etMobile, etEmail,
            etSchool, etStartTime, etEndTime, etqualifications, etSkill,
            etWorkType, etWorkAddress, etSalary, etWorkIndustry, etTag;
    private RadioButton rbMale, rbFemale;
    private LoginModel.UserinfoBean userinfoBean;

    @Override
    protected int getLayout() {
        return R.layout.activity_resume_edit;
    }

    @Override
    protected void initView() {
        tvSave = findViewById(R.id.tv_save);
        etName = findViewById(R.id.et_name);
        etBirth = findViewById(R.id.et_birth);
        etMobile = findViewById(R.id.et_mobile);
        etEmail = findViewById(R.id.et_email);
        etAddress = findViewById(R.id.et_address);
        rbFemale = findViewById(R.id.rb_female);
        rbMale = findViewById(R.id.rb_male);
        etSchool = findViewById(R.id.et_school);
        etStartTime = findViewById(R.id.et_start_time);
        etEndTime = findViewById(R.id.et_end_time);
        etqualifications = findViewById(R.id.et_qualifications);
        etSkill = findViewById(R.id.et_skill);
        etWorkType = findViewById(R.id.et_work_type);
        etWorkAddress = findViewById(R.id.et_work_address);
        etSalary = findViewById(R.id.et_salary);
        etWorkIndustry = findViewById(R.id.et_work_industry);
        etTag = findViewById(R.id.et_tag);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getResume();
        userinfoBean = App.getInstance().getUserData();
        tvSave.setOnClickListener(v -> mPresenter.postResume(new Gson().toJson(
                new ResumeModel(App.getInstance().getUserData().getUser_id() + "",
                        new ResumeModel.MyInfoBean(etName.getText().toString(),
                                rbMale.isChecked() ? "男" : "女", etBirth.getText().toString(),
                                etAddress.getText().toString(), etEmail.getText().toString(),
                                etMobile.getText().toString())
                        , new ResumeModel.EducationBean(etSchool.getText().toString(), etStartTime.getText().toString(),
                        etEndTime.getText().toString(), etqualifications.getText().toString(), etSkill.getText().toString())
                        , new ResumeModel.TargetBean(etWorkType.getText().toString(), etWorkAddress.getText().toString(),
                        etSalary.getText().toString(), etTag.getText().toString(), etWorkIndustry.getText().toString())))));

    }

    @Override
    protected void createPresenter() {
        mPresenter = new ResumeEditPresenter();
    }

    @Override
    public OnGsonRespListener<ResumeModel> onGetResume() {
        return new OnGsonRespListener<ResumeModel>() {
            @Override
            public void onSuccess(ResumeModel data) {
                etName.setText(data.getMy_info().getName());
                etBirth.setText(data.getMy_info().getBirthday());
                etMobile.setText(data.getMy_info().getPhone());
                etEmail.setText(data.getMy_info().getEmail());
                etAddress.setText(data.getMy_info().getAddress());
                if ("男".equals(data.getMy_info().getSex())) {
                    rbMale.setChecked(true);
                } else {
                    rbFemale.setChecked(true);
                }
                etSchool.setText(data.getEducation().getSchool());
                etStartTime.setText(data.getEducation().getStart_time());
                etEndTime.setText(data.getEducation().getEnd_time());
                etqualifications.setText(data.getEducation().getQualifications());
                etSkill.setText(data.getEducation().getSkill());
                etWorkType.setText(data.getTarget().getWork_type());
                etWorkAddress.setText(data.getTarget().getWork_address());
                etSalary.setText(data.getTarget().getWork_salary());
                etWorkIndustry.setText(data.getTarget().getWork_industry());
                etTag.setText(data.getTarget().getTag());
            }

            @Override
            public void onFail(String error) {

            }
        };
    }

    @Override
    public OnGsonRespListener onGetPostResume() {
        return new OnGsonRespListener() {
            @Override
            public void onSuccess(Object data) {
                Toast.makeText(mContext, "简历编辑成功", Toast.LENGTH_SHORT);
                finish();
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mContext, error, Toast.LENGTH_SHORT);
            }
        };
    }
}
