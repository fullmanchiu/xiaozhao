package com.bishe.qiuzhi.module.index.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import static android.content.Context.VIBRATOR_SERVICE;

public class MineFragment extends Fragment {
    private TextView tvName;
    private RelativeLayout rlSettings, rlAbout, rlShare;
    private ImageView ivAvatar;
    private LinearLayout llResume, llFav, llApply;
    private final int requestCodeLogin = 100;
    private final int requestCodeSignOut = 101;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        tvName = view.findViewById(R.id.tv_login);
        llApply = view.findViewById(R.id.ll_apply);
        rlShare = view.findViewById(R.id.rl_share);
        rlAbout = view.findViewById(R.id.rl_about);
        rlSettings = view.findViewById(R.id.rl_settings);
        llResume = view.findViewById(R.id.ll_resume);
        llFav = view.findViewById(R.id.ll_fav);
        ivAvatar = view.findViewById(R.id.iv_avatar);
        llApply.setOnClickListener(v -> startActivity(new Intent(getContext(), ApplyActivity.class)));
        rlShare.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_tip) + Constants.APP_DOWNLOAD_URL);
            startActivity(Intent.createChooser(intent, getString(R.string.share)));
        });
        rlAbout.setOnClickListener(v -> {
            Uri uri = Uri.parse("http://www.colafans.cn:8081/index.php/index/index/index");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        llFav.setOnClickListener(v -> startActivity(new Intent(getContext(), FavActivity.class)));
        llResume.setOnClickListener(v -> startActivity(new Intent(getContext(), ResumeActivity.class)));
        if (App.getInstance().isLogin()) {
            initUserData();
            tvName.setOnClickListener(null);
        } else {
            tvName.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), LoginActivity.class), requestCodeLogin));
        }
        rlSettings.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), SettingsActivity.class), requestCodeSignOut));
        return view;
    }

    private void initUserData() {
        tvName.setText(App.getInstance().getUserData().getNickname());
        Glide.with(getContext()).load(Constants.DOMAIN + App.getInstance().getUserData().getAvatar()).into(ivAvatar);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case requestCodeLogin:
                if (resultCode == RESULT_OK) {
                    initUserData();
                }
                break;
            case requestCodeSignOut:
                if (requestCode == RESULT_OK) {
                    signOut();
                }
                break;
            default:
        }
    }

    private void signOut() {
        tvName.setText(R.string.loginHint);
        tvName.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), LoginActivity.class), requestCodeLogin));
    }
}
