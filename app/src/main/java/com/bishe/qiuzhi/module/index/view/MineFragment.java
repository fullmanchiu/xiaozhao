package com.bishe.qiuzhi.module.index.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.app.Constants;
import com.bishe.qiuzhi.module.apply.view.ApplyActivity;
import com.bishe.qiuzhi.module.fav.view.FavActivity;
import com.bishe.qiuzhi.module.login.view.LoginActivity;
import com.bishe.qiuzhi.module.resume.view.ResumeActivity;
import com.bishe.qiuzhi.module.settings.view.SettingsActivity;
import com.bumptech.glide.Glide;

import static android.app.Activity.RESULT_OK;

public class MineFragment extends Fragment {
    private TextView tvName;
    private RelativeLayout rlSettings, rlAbout, rlShare, rlFeedBack;
    private ImageView ivAvatar;
    private LinearLayout llResume, llFav, llApply;
    private final int requestCodeLogin = 100;
    private final int requestCodeSignOut = 101;
    private boolean init;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            initUserData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        tvName = view.findViewById(R.id.tv_login);
        llApply = view.findViewById(R.id.ll_apply);
        rlShare = view.findViewById(R.id.rl_share);
        rlAbout = view.findViewById(R.id.rl_about);
        rlSettings = view.findViewById(R.id.rl_settings);
        rlFeedBack = view.findViewById(R.id.rl_feedback);
        init = true;
        rlFeedBack.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(R.string.feedback);
            builder.setMessage(R.string.feedback_hint);
            builder.setCancelable(true);
            builder.setPositiveButton(R.string.ok, (dialog, which) -> {
                String[] email = {"2418620804@qq.com"};//用来接收意见反馈的邮箱
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("message/rfc822"); // 设置邮件格式
                intent.putExtra(Intent.EXTRA_EMAIL, email); // 接收人
                intent.putExtra(Intent.EXTRA_CC, email); // 抄送人
                intent.putExtra(Intent.EXTRA_SUBJECT, "校招App意见反馈"); // 主题
                intent.putExtra(Intent.EXTRA_TEXT, "请在此处填写您的宝贵意见"); // 正文
                startActivity(Intent.createChooser(intent, "发送邮件邮件"));
            });
            builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
        llResume = view.findViewById(R.id.ll_resume);
        llFav = view.findViewById(R.id.ll_fav);
        ivAvatar = view.findViewById(R.id.iv_avatar);
        llApply.setOnClickListener(v -> {
            if (App.getInstance().isLogin()) {
                startActivity(new Intent(getContext(), ApplyActivity.class));
            } else {
                showLoginDialog();
            }
        });

        rlShare.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_tip) + Constants.APP_DOWNLOAD_URL);
            startActivity(Intent.createChooser(intent, getString(R.string.share)));
        });
        rlAbout.setOnClickListener(v -> {
            Uri uri = Uri.parse("http://www.colafans.cn/index.php/index/index/index");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        llFav.setOnClickListener(v -> {
            if (App.getInstance().isLogin()) {
                startActivity(new Intent(getContext(), FavActivity.class));
            } else {
                showLoginDialog();
            }
        });
        llResume.setOnClickListener(v -> {
            if (App.getInstance().isLogin()) {
                startActivity(new Intent(getContext(), ResumeActivity.class));
            } else {
                showLoginDialog();
            }
        });
        if (App.getInstance().isLogin()) {
            initUserData();
            tvName.setOnClickListener(null);
        } else {
            tvName.setText(R.string.loginHint);
            ivAvatar.setImageResource(R.drawable.ic_default_avatar);
            tvName.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), LoginActivity.class), requestCodeLogin));
        }
        rlSettings.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), SettingsActivity.class), requestCodeSignOut));
        return view;
    }

    private void initUserData() {
        if (init && tvName != null && ivAvatar != null && !App.getInstance().isLogin()) {
            tvName.setText(R.string.loginHint);
            ivAvatar.setImageResource(R.drawable.ic_default_avatar);
            tvName.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), LoginActivity.class), requestCodeLogin));
        }
        if (init && tvName != null && ivAvatar != null && App.getInstance().isLogin()) {
            tvName.setText(App.getInstance().getUserData().getNickname());
            Glide.with(getContext()).load(Constants.DOMAIN + App.getInstance().getUserData().getAvatar()).into(ivAvatar);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            initUserData();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case requestCodeLogin:
                if (resultCode == RESULT_OK) {
                    initUserData();
                    ((MainActivity) getActivity()).setToOne();
                }
                break;
            case requestCodeSignOut:
                if (requestCode == RESULT_OK) {
                    signOut();
                    ((MainActivity) getActivity()).setToOne();
                }
                break;
            default:
        }
    }

    private void signOut() {
        tvName.setText(R.string.loginHint);
        tvName.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), LoginActivity.class), requestCodeLogin));
    }

    private void showLoginDialog() {
        final AlertDialog.Builder loginDialog = new AlertDialog.Builder(getContext());
        loginDialog.setMessage(R.string.loginDialogMessage)
                .setPositiveButton(R.string.loginDialogPositiveText
                        , (dialog, which) -> startActivity(
                                new Intent(getContext(), LoginActivity.class)))
                .setNegativeButton(R.string.loginDialogNegativeText
                        , (dialog, which) -> dialog.cancel());
        loginDialog.show();
    }
}
