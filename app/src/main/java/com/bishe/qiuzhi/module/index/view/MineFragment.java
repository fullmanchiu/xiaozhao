package com.bishe.qiuzhi.module.index.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.module.login.view.LoginActivity;
import com.bishe.qiuzhi.module.settings.view.SettingsActivity;

import static android.app.Activity.RESULT_OK;

public class MineFragment extends Fragment {
    private TextView tvName;
    private RelativeLayout rlSettings;
    private final int requestCodeLogin = 100;
    private final int requestCodeSignOut = 101;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        tvName = view.findViewById(R.id.tv_login);
        rlSettings = view.findViewById(R.id.rl_settings);
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
