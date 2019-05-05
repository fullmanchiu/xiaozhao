package com.bishe.qiuzhi.module.fav.view;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.app.Constants;
import com.bishe.qiuzhi.module.fav.adapter.FavPositionAdapter;
import com.bishe.qiuzhi.module.fav.adapter.FavSeminarAdapter;
import com.bishe.qiuzhi.module.fav.contract.FavContract;
import com.bishe.qiuzhi.module.fav.presenter.FavPresenter;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.module.position.view.PositionDetailActivity;
import com.bishe.qiuzhi.module.seminar.model.SeminarBean;
import com.bishe.qiuzhi.net.OnGsonRespListener;
import com.bishe.qiuzhi.wedgit.TitleBar;

import java.util.List;

public class FavActivity extends BaseActivity<FavPresenter> implements FavContract.View {
    private RadioButton rbPosition, rbSeminar;
    private TitleBar titleBar;
    private ProgressBar progressBar;
    private RecyclerView rvPosition, rvSeminar;
    private FavPositionAdapter favPositionAdapter;
    private FavSeminarAdapter favSeminarAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_fav;
    }

    @Override
    protected void initView() {
        titleBar = findViewById(R.id.titleBar);
        rbPosition = findViewById(R.id.rb_position);
        rbSeminar = findViewById(R.id.rb_seminar);
        progressBar = findViewById(R.id.pb);
        rvPosition = findViewById(R.id.rv_position);
        rvSeminar = findViewById(R.id.rv_seminar);
    }

    @Override
    protected void initEventAndData() {
        titleBar.setTitle(R.string.mine_collection);
        titleBar.setOnBackClickListener(() -> onBackPressed());
        rvPosition.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvPosition.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        favPositionAdapter = new FavPositionAdapter(mContext);
        favPositionAdapter.setOnItemCLickListener((position, positionBean) -> {
            Intent intent = new Intent(mContext, PositionDetailActivity.class);
            intent.putExtra(Constants.EXTRA_POSITION_ID, positionBean.getId());
            startActivity(intent);
        });
        rvPosition.setAdapter(favPositionAdapter);
        rvSeminar.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvSeminar.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        favSeminarAdapter = new FavSeminarAdapter(mContext);
//        favSeminarAdapter.setOnItemCLickListener((position, positionBean) -> {
//            Intent intent = new Intent(mContext, PositionDetailActivity.class);
//            intent.putExtra(Constants.EXTRA_POSITION_ID, positionBean.getId());
//            startActivity(intent);
//        });
        rvSeminar.setAdapter(favSeminarAdapter);
        mPresenter.getFavPositionList();
        rbPosition.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mPresenter.getFavPositionList();
                rvPosition.setVisibility(View.GONE);
                rvSeminar.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        rbSeminar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mPresenter.getFavSeminarList();
                rvPosition.setVisibility(View.GONE);
                rvSeminar.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        rbPosition.setChecked(true);
    }

    @Override
    protected void createPresenter() {
        mPresenter = new FavPresenter();
    }

    @Override
    public OnGsonRespListener<List<PositionBean>> onGetFavPositionList() {
        return new OnGsonRespListener<List<PositionBean>>() {
            @Override
            public void onSuccess(List<PositionBean> data) {
                favPositionAdapter.setData(data);
                progressBar.setVisibility(View.GONE);
                rvPosition.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                rvPosition.setVisibility(View.VISIBLE);

            }
        };
    }

    @Override
    public OnGsonRespListener<List<SeminarBean>> onGetFavSeminarList() {
        return new OnGsonRespListener<List<SeminarBean>>() {
            @Override
            public void onSuccess(List<SeminarBean> data) {
                favSeminarAdapter.setData(data);
                progressBar.setVisibility(View.GONE);
                rvSeminar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                rvSeminar.setVisibility(View.VISIBLE);
            }
        };
    }
}
