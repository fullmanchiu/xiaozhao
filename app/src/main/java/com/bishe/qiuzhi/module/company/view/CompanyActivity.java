package com.bishe.qiuzhi.module.company.view;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.app.Constants;
import com.bishe.qiuzhi.module.company.contract.CompanyContract;
import com.bishe.qiuzhi.module.company.presenter.CompanyPresenter;
import com.bishe.qiuzhi.module.fav.adapter.FavPositionAdapter;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.module.position.view.PositionDetailActivity;
import com.bishe.qiuzhi.net.OnGsonRespListener;
import com.bishe.qiuzhi.wedgit.TitleBar;
import com.bumptech.glide.Glide;

import java.util.List;

public class CompanyActivity extends BaseActivity<CompanyPresenter> implements CompanyContract.View {
    private int companyId;
    private String companyDescribe, companyName, companyLogo;
    private ImageView ivCompanyLogo;
    private TextView tvCompanyName, tvCompanyDescribe;
    private RadioButton rbDescribe, rbPosition;
    private RecyclerView rvPosition;
    private FavPositionAdapter favPositionAdapter;
    private TitleBar titleBar;

    @Override
    protected int getLayout() {
        return R.layout.activity_company;
    }

    @Override
    protected void initView() {
        titleBar = findViewById(R.id.titleBar);

        ivCompanyLogo = findViewById(R.id.iv_company_logo);
        tvCompanyName = findViewById(R.id.tv_company_name);
        tvCompanyDescribe = findViewById(R.id.tv_company_description);
        rbDescribe = findViewById(R.id.rb_describe);
        rbPosition = findViewById(R.id.rb_position);
        rvPosition = findViewById(R.id.rv_position);
    }

    @Override
    protected void initEventAndData() {
        titleBar.setOnBackClickListener(() -> onBackPressed());
        companyId = getIntent().getIntExtra(Constants.EXTRA_COMPANY_ID, 0);
        companyDescribe = getIntent().getStringExtra(Constants.EXTRA_COMPANY_DESCRIBE);
        companyName = getIntent().getStringExtra(Constants.EXTRA_COMPANY_NAME);
        companyLogo = getIntent().getStringExtra(Constants.EXTRA_COMPANY_LOGO);
        Glide.with(mContext).load(Constants.DOMAIN + companyLogo).into(ivCompanyLogo);
        mPresenter.getPositionByCompanyId(companyId);
        tvCompanyName.setText(companyName);
        tvCompanyDescribe.setText(Html.fromHtml(companyDescribe));
        rvPosition.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvPosition.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        favPositionAdapter = new FavPositionAdapter(mContext);
        favPositionAdapter.setOnItemCLickListener((position, positionBean) -> {
            Intent intent = new Intent(mContext, PositionDetailActivity.class);
            intent.putExtra(Constants.EXTRA_POSITION_ID, positionBean.getId());
            startActivity(intent);
        });
        rvPosition.setAdapter(favPositionAdapter);
        rbPosition.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tvCompanyDescribe.setVisibility(View.GONE);
                rvPosition.setVisibility(View.VISIBLE);
            }
        });
        rbDescribe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvCompanyDescribe.setVisibility(View.VISIBLE);
                    rvPosition.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter = new CompanyPresenter();
    }

    @Override
    public OnGsonRespListener<List<PositionBean>> onGetPosition() {
        return new OnGsonRespListener<List<PositionBean>>() {
            @Override
            public void onSuccess(List<PositionBean> data) {
                favPositionAdapter.setData(data);
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
                ;
            }
        };
    }
}
