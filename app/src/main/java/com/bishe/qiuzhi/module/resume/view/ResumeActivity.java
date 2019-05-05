package com.bishe.qiuzhi.module.resume.view;

import android.util.Log;
import android.widget.TextView;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.db.UserManager;
import com.bishe.qiuzhi.module.resume.contract.ResumeContract;
import com.bishe.qiuzhi.module.resume.model.ResumeModel;
import com.bishe.qiuzhi.module.resume.presenter.ResumePresenter;
import com.bishe.qiuzhi.net.OnGsonRespListener;
import com.bishe.qiuzhi.utils.GsonUtil;
import com.google.gson.Gson;

public class ResumeActivity extends BaseActivity<ResumePresenter> implements ResumeContract.View {
    private TextView tvName, tvInfo, tvTel, tvEmail, tvSalary, tvLocation, tvIndustry;

    @Override
    protected int getLayout() {
        return R.layout.activity_resume;
    }

    @Override
    protected void initView() {
        tvName = findViewById(R.id.tv_nick_name);
        tvInfo = findViewById(R.id.tv_info);
        tvTel = findViewById(R.id.tv_tel);
        tvEmail = findViewById(R.id.tv_email);
        tvSalary = findViewById(R.id.tv_salary);
        tvLocation = findViewById(R.id.tv_location);
        tvIndustry = findViewById(R.id.tv_industry);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getResume();
//        mPresenter.postResume(new Gson().toJson(
//                new ResumeModel(App.getInstance().getUserData().getUser_id() + "",
//                        new ResumeModel.MyInfoBean("邱亮", "男", "1992-05-28",
//                                "江苏省苏州市", "18550038654", "526178731@qq.com")
//                        , new ResumeModel.EducationBean("东南大学", "2013-8-1",
//                        "2015-8-1", "本科", "计算机课科学与技术")
//                        , new ResumeModel.TargetBean("全职", "苏州",
//                        "5k-6k", "同时天才", "计算机科学与技术"))));
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
}
