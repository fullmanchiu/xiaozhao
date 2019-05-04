package com.bishe.qiuzhi.module.resume.view;

import android.util.Log;

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

    @Override
    protected int getLayout() {
        return R.layout.activity_resume;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEventAndData() {
        mPresenter.getResume();
        mPresenter.postResume(new Gson().toJson(
                new ResumeModel(App.getInstance().getUserData().getUser_id() + "",
                        new ResumeModel.MyInfoBean("邱亮", "男", "1992-05-28",
                                "江苏省苏州市", "18550038654", "526178731@qq.com")
                        , new ResumeModel.EducationBean("东南大学", "2013-8-1",
                        "2015-8-1", "本科", "计算机课科学与技术")
                        , new ResumeModel.TargetBean("全职", "苏州",
                        "5k-6k", "同时天才", "计算机科学与技术"))));
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
