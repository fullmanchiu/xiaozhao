package com.bishe.qiuzhi.module.apply.view;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.app.Constants;
import com.bishe.qiuzhi.module.apply.adapter.ApplyAdapter;
import com.bishe.qiuzhi.module.apply.contract.ApplyContract;
import com.bishe.qiuzhi.module.apply.model.ApplyModel;
import com.bishe.qiuzhi.module.apply.presenter.ApplyPresenter;
import com.bishe.qiuzhi.module.position.view.PositionDetailActivity;
import com.bishe.qiuzhi.net.OnGsonRespListener;
import com.bishe.qiuzhi.wedgit.TitleBar;

import java.util.List;

/**
 * 我的投递页面
 */
public class ApplyActivity extends BaseActivity<ApplyPresenter> implements ApplyContract.View {
    private TitleBar titleBar;
    private ProgressBar progressBar;
    private RecyclerView rvApply;
    private ApplyAdapter applyAdapter;

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayout() {
        return R.layout.activity_apply;
    }

    /**
     * 初始化View 控件
     */
    @Override
    protected void initView() {
        //通过id绑定控件
        titleBar = findViewById(R.id.titleBar);
        progressBar = findViewById(R.id.pb);
        rvApply = findViewById(R.id.rv_apply);
    }

    /**
     * 初始化监听器和数据
     */
    @Override
    protected void initEventAndData() {
        mPresenter.getApplyList();//从网络获取数据
        //设置rv的布局管理器
        rvApply.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        //设置rv的间隔
        rvApply.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        //初始化rv 设配器
        applyAdapter = new ApplyAdapter(mContext);
        //设置rv item的点击事件
        applyAdapter.setOnItemCLickListener((position, applyModel) -> {
            Intent intent = new Intent(mContext, PositionDetailActivity.class);
            intent.putExtra(Constants.EXTRA_POSITION_ID, applyModel.getId());
            startActivity(intent);
        });
        //rv绑定适配器
        rvApply.setAdapter(applyAdapter);
        //标题栏返回键点击事件
        titleBar.setOnBackClickListener(() -> onBackPressed());
    }

    @Override
    protected void createPresenter() {
        mPresenter = new ApplyPresenter();
    }


    /**
     * 网络回调
     *
     * @return
     */
    @Override
    public OnGsonRespListener<List<ApplyModel>> onGetAppyList() {
        return new OnGsonRespListener<List<ApplyModel>>() {
            @Override
            public void onSuccess(List<ApplyModel> data) {
                //成功加载数据
                applyAdapter.setData(data);
                progressBar.setVisibility(View.GONE);
                rvApply.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFail(String error) {
                //失败提示
                Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                rvApply.setVisibility(View.VISIBLE);
            }
        };
    }
}
