package com.bishe.qiuzhi.module.position.view;

import android.support.constraint.ConstraintLayout;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.app.Constants;
import com.bishe.qiuzhi.module.position.contract.PositionDetailContract;
import com.bishe.qiuzhi.module.position.model.PositionDetailModel;
import com.bishe.qiuzhi.module.position.presenter.PositionDetailPresenter;
import com.bishe.qiuzhi.net.OnGsonRespListener;
import com.bishe.qiuzhi.wedgit.TitleBar;
import com.bumptech.glide.Glide;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class PositionDetailActivity extends BaseActivity<PositionDetailPresenter> implements PositionDetailContract.View {
    private TitleBar titleBar;
    private TextView tvPositionName, tvDate, tvNum, tvLocation, tvCompanyName, tvCompanyType, tvJobDecription;
    private ImageView ivCompanyLogo;
    private ConstraintLayout constraintLayout;
    private ProgressBar progressBar;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.activity_position_detail_layout;
    }

    @Override
    protected void initView() {
        id = getIntent().getIntExtra(Constants.EXTRA_POSITION_ID, 0);
        titleBar = findViewById(R.id.titleBar);
        tvPositionName = findViewById(R.id.tv_positionName);
        tvDate = findViewById(R.id.tv_date);
        tvNum = findViewById(R.id.tv_num);
        tvLocation = findViewById(R.id.tv_location);
        progressBar = findViewById(R.id.pb);
        constraintLayout = findViewById(R.id.container);
        tvCompanyName = findViewById(R.id.tv_company_name);
        ivCompanyLogo = findViewById(R.id.iv_company_logo);
        tvCompanyType = findViewById(R.id.tv_company_type);
        tvJobDecription = findViewById(R.id.tv_job_description);
    }

    @Override
    protected void initEventAndData() {
        titleBar.setOnBackClickListener(() -> onBackPressed());
        mPresenter.getPositionDetail(id);
    }

    @Override
    protected void createPresenter() {
        mPresenter = new PositionDetailPresenter();
    }

    @Override
    public OnGsonRespListener<PositionDetailModel> onGetPositionDetail() {
        return new OnGsonRespListener<PositionDetailModel>() {
            @Override
            public void onSuccess(PositionDetailModel data) {
                if (data != null) {
                    progressBar.setVisibility(View.GONE);
                    constraintLayout.setVisibility(View.VISIBLE);
                    titleBar.setTitle(data.getTitle());
                    tvPositionName.setText(data.getTitle());
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
                    tvDate.setText(sdf.format(new Timestamp(data.getPublish_time())));
                    tvNum.setText("招" + data.getNum() + "人");
                    tvLocation.setText(data.getLocation());
                    Glide.with(mContext).load(Constants.DOMAIN + data.getCompany().getImage()).into(ivCompanyLogo);
                    tvCompanyName.setText(data.getCompany().getName());
                    tvCompanyType.setText(data.getCompany().getCompany_type());
                    tvJobDecription.setText(Html.fromHtml(data.getContent()));
                }
            }

            @Override
            public void onFail(String error) {
                progressBar.setVisibility(View.GONE);
            }
        };
    }
}
