package com.bishe.qiuzhi.module.apply.view;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.app.Constants;
import com.bishe.qiuzhi.module.apply.adapter.ApplyAdapter;
import com.bishe.qiuzhi.module.apply.contract.ApplyContract;
import com.bishe.qiuzhi.module.apply.model.ApplyModel;
import com.bishe.qiuzhi.module.apply.presenter.ApplyPresenter;
import com.bishe.qiuzhi.module.fav.adapter.FavPositionAdapter;
import com.bishe.qiuzhi.module.position.view.PositionDetailActivity;
import com.bishe.qiuzhi.net.OnGsonRespListener;
import com.bishe.qiuzhi.wedgit.TitleBar;

import java.util.List;

public class ApplyActivity extends BaseActivity<ApplyPresenter> implements ApplyContract.View {
    private TitleBar titleBar;
    private ProgressBar progressBar;
    private RecyclerView rvApply;
    private ApplyAdapter applyAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_apply;
    }

    @Override
    protected void initView() {
        titleBar = findViewById(R.id.titleBar);
        progressBar = findViewById(R.id.pb);
        rvApply = findViewById(R.id.rv_apply);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getApplyList();
        rvApply.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvApply.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        applyAdapter = new ApplyAdapter(mContext);
        applyAdapter.setOnItemCLickListener((position, applyModel) -> {
            Intent intent = new Intent(mContext, PositionDetailActivity.class);
            intent.putExtra(Constants.EXTRA_POSITION_ID, applyModel.getId());
            startActivity(intent);
        });
        rvApply.setAdapter(applyAdapter);
        titleBar.setOnBackClickListener(() -> onBackPressed());
    }

    @Override
    protected void createPresenter() {
        mPresenter = new ApplyPresenter();
    }

    @Override
    public OnGsonRespListener<List<ApplyModel>> onGetAppyList() {
        return new OnGsonRespListener<List<ApplyModel>>() {
            @Override
            public void onSuccess(List<ApplyModel> data) {
                applyAdapter.setData(data);
                progressBar.setVisibility(View.GONE);
                rvApply.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                rvApply.setVisibility(View.VISIBLE);
            }
        };
    }
}
