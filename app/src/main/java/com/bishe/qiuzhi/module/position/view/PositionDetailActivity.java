package com.bishe.qiuzhi.module.position.view;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.app.Constants;
import com.bishe.qiuzhi.module.company.view.CompanyActivity;
import com.bishe.qiuzhi.module.login.view.LoginActivity;
import com.bishe.qiuzhi.module.position.contract.PositionDetailContract;
import com.bishe.qiuzhi.module.position.model.PositionDetailModel;
import com.bishe.qiuzhi.module.position.presenter.PositionDetailPresenter;
import com.bishe.qiuzhi.net.OnGsonRespListener;
import com.bishe.qiuzhi.wedgit.BottomView;
import com.bishe.qiuzhi.wedgit.TitleBar;
import com.bumptech.glide.Glide;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class PositionDetailActivity extends BaseActivity<PositionDetailPresenter> implements PositionDetailContract.View {
    private TitleBar titleBar;
    private TextView tvPositionName, tvDate, tvNum, tvLocation, tvCompanyName, tvCompanyType, tvJobDecription;
    private ImageView ivCompanyLogo;
    private ConstraintLayout constraintLayout, clCompany;
    private ProgressBar progressBar;
    private ScrollView scrollView;
    private int id;
    private int companyId;
    private String companyDescribe;
    private BottomView bottomView;

    @Override
    protected int getLayout() {
        return R.layout.activity_position_detail_layout;
    }

    @Override
    protected void initView() {
        id = getIntent().getIntExtra(Constants.EXTRA_POSITION_ID, 0);
        scrollView = findViewById(R.id.scrollView);
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
        clCompany = findViewById(R.id.cl_company);
        bottomView = findViewById(R.id.bottomView);
    }

    @Override
    protected void initEventAndData() {
        titleBar.setOnBackClickListener(() -> onBackPressed());
        clCompany.setOnClickListener(v -> {
            Intent intent = new Intent(PositionDetailActivity.this, CompanyActivity.class);
            intent.putExtra(Constants.EXTRA_COMPANY_ID, companyId);
            intent.putExtra(Constants.EXTRA_COMPANY_DESCRIBE, companyDescribe);
            startActivity(intent);
        });
        bottomView.setOnShareButtonClickListener(() -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_tip) + Constants.APP_DOWNLOAD_URL);
            startActivity(Intent.createChooser(intent, getString(R.string.share)));
        });
        bottomView.setOnFavButtonClickListener(new BottomView.OnFavButtonClickListener() {
            @Override
            public void onClick() {
                if (!App.getInstance().isLogin()) {
                    showLoginDialog();
                } else {
                    //TODO fav
                    if (bottomView.getFavStatus()) {
                        mPresenter.UnFavPosition(id);
                    } else {
                        mPresenter.FavPosition(id);
                    }
                    Log.d("aaa", "Fav click");
                }
            }
        });
        bottomView.setOnTextButtonClickListener(new BottomView.OnTextButtonClickListener() {
            @Override
            public void onClick() {
                if (!App.getInstance().isLogin()) {
                    showLoginDialog();
                } else {
                    //TODO text
                    Log.d("aaa", "text click");

                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                Rect scrollRect = new Rect();
                scrollView.getHitRect(scrollRect);
                //子控件在可视范围内（至少有一个像素在可视范围内）
                if (tvPositionName.getLocalVisibleRect(scrollRect)) {
                    titleBar.setTitle("");
                    tvPositionName.setVisibility(View.VISIBLE);
                } else {
                    ////子控件完全不在可视范围内
                    titleBar.setTitle(tvPositionName.getText().toString());
                    tvPositionName.setVisibility(View.INVISIBLE);
                }
            });
        }
        mPresenter.getPositionDetail(id);
    }

    private void showLoginDialog() {
        final AlertDialog.Builder loginDialog = new AlertDialog.Builder(mContext);
        loginDialog.setMessage(R.string.loginDialogMessage)
                .setPositiveButton(R.string.loginDialogPositiveText
                        , (dialog, which) -> dialog.cancel())
                .setNegativeButton(R.string.loginDialogNegativeText
                        , (dialog, which) -> startActivity(
                                new Intent(PositionDetailActivity.this, LoginActivity.class)));
        loginDialog.show();
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
                    tvPositionName.setText(data.getTitle());
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
                    tvDate.setText(sdf.format(new Timestamp(data.getPublish_time())));
                    tvNum.setText("招" + data.getNum() + "人");
                    tvLocation.setText(data.getLocation());
                    Glide.with(mContext).load(Constants.DOMAIN + data.getCompany().getImage()).into(ivCompanyLogo);
                    tvCompanyName.setText(data.getCompany().getName());
                    tvCompanyType.setText(data.getCompany().getCompany_type());
                    tvJobDecription.setText(Html.fromHtml(data.getContent()));
                    companyId = data.getCompany().getId();
                    companyDescribe = data.getCompany().getDescribe();
                }
            }

            @Override
            public void onFail(String error) {
                progressBar.setVisibility(View.GONE);
            }
        };
    }

    @Override
    public OnGsonRespListener onFavResult() {
        return new OnGsonRespListener() {
            @Override
            public void onSuccess(Object data) {
                bottomView.setFavStatus(true);
                Toast.makeText(mContext, "收藏成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mContext, "收藏失败", Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    public OnGsonRespListener onUnFavResult() {
        return new OnGsonRespListener() {
            @Override
            public void onSuccess(Object data) {
                bottomView.setFavStatus(false);
                Toast.makeText(mContext, "取消收藏成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mContext, "取消收藏失败", Toast.LENGTH_LONG).show();
            }
        };
    }
}
