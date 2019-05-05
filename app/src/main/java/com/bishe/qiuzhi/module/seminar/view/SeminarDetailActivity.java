package com.bishe.qiuzhi.module.seminar.view;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.app.Constants;
import com.bishe.qiuzhi.module.login.view.LoginActivity;
import com.bishe.qiuzhi.module.seminar.contract.SeminarDetailContract;
import com.bishe.qiuzhi.module.seminar.model.SeminarDetailModel;
import com.bishe.qiuzhi.module.seminar.presenter.SeminarDetailPresenter;
import com.bishe.qiuzhi.net.OnGsonRespListener;
import com.bishe.qiuzhi.utils.DateUtil;
import com.bishe.qiuzhi.wedgit.BottomView;
import com.bishe.qiuzhi.wedgit.TitleBar;

public class SeminarDetailActivity extends BaseActivity<SeminarDetailPresenter> implements SeminarDetailContract.View {
    private int id;
    private TitleBar titleBar;
    private ConstraintLayout constraintLayout;
    private ProgressBar progressBar;
    private ScrollView scrollView;
    private BottomView bottomView;
    private TextView tvSeminarName, tvDate, tvSchool, tvAddress, tvContent;


    @Override
    protected int getLayout() {
        return R.layout.activity_seminar_detail;
    }

    @Override
    protected void initView() {
        id = getIntent().getIntExtra(Constants.EXTRA_SEMINAR_ID, 0);
        scrollView = findViewById(R.id.scrollView);
        titleBar = findViewById(R.id.titleBar);
        progressBar = findViewById(R.id.pb);
        constraintLayout = findViewById(R.id.container);
        bottomView = findViewById(R.id.bottomView);
        tvSeminarName = findViewById(R.id.tv_seminar_name);
        tvDate = findViewById(R.id.tv_date);
        tvSchool = findViewById(R.id.tv_school);
        tvAddress = findViewById(R.id.tv_address);
        tvContent = findViewById(R.id.tv_seminar_description);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getSeminarDetail(id);
        titleBar.setOnBackClickListener(() -> onBackPressed());
        bottomView.setOnShareButtonClickListener(() -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_tip) + Constants.APP_DOWNLOAD_URL);
            startActivity(Intent.createChooser(intent, getString(R.string.share)));
        });
        bottomView.setOnFavButtonClickListener(() -> {
            if (!App.getInstance().isLogin()) {
                showLoginDialog();
            } else {
                if (bottomView.getFavStatus()) {
                    mPresenter.unFavSeminar(id);
                } else {
                    mPresenter.favSeminar(id);
                }
            }
        });
        bottomView.setOnTextButtonClickListener(() -> {
            if (!App.getInstance().isLogin()) {
                showLoginDialog();
            } else {
                if (bottomView.getApplyStatus()) {
                    Toast.makeText(mContext, R.string.resueSendAlrealdy, Toast.LENGTH_SHORT).show();
                } else {
                    //TODO 加入日历
                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                Rect scrollRect = new Rect();
                scrollView.getHitRect(scrollRect);
                //子控件在可视范围内（至少有一个像素在可视范围内）
                if (tvSeminarName.getLocalVisibleRect(scrollRect)) {
                    titleBar.setTitle("");
                    tvSeminarName.setVisibility(View.VISIBLE);
                } else {
                    ////子控件完全不在可视范围内
                    titleBar.setTitle(tvSeminarName.getText().toString());
                    tvSeminarName.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    private void showLoginDialog() {
        final AlertDialog.Builder loginDialog = new AlertDialog.Builder(mContext);
        loginDialog.setMessage(R.string.loginDialogMessage)
                .setPositiveButton(R.string.loginDialogPositiveText
                        , (dialog, which) -> dialog.cancel())
                .setNegativeButton(R.string.loginDialogNegativeText
                        , (dialog, which) -> startActivity(
                                new Intent(mContext, LoginActivity.class)));
        loginDialog.show();
    }

    @Override
    protected void createPresenter() {
        mPresenter = new SeminarDetailPresenter();
    }

    @Override
    public OnGsonRespListener<SeminarDetailModel> onGetSeminarDetail() {
        return new OnGsonRespListener<SeminarDetailModel>() {
            @Override
            public void onSuccess(SeminarDetailModel data) {
                progressBar.setVisibility(View.GONE);
                constraintLayout.setVisibility(View.VISIBLE);
                tvSeminarName.setText(data.getName());
                String date = DateUtil.simpleFormat("MM-dd    hh:mm", data.getStart_time()) + "-" + DateUtil.simpleFormat("hh:mm", data.getEnd_time());
                tvDate.setText(date);
                tvSchool.setText(data.getSchool().getName());
                tvAddress.setText(data.getAddress());
                tvContent.setText(Html.fromHtml(data.getContent()));
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
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
