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
    private int requestCode = 100;

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
            tvName.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), LoginActivity.class), requestCode));
        }
        rlSettings.setOnClickListener(v -> startActivity(new Intent(getContext(), SettingsActivity.class)));
        return view;
    }

    private void initUserData() {
        tvName.setText(App.getInstance().getUserData().getNickname());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == this.requestCode) {
            if (resultCode == RESULT_OK) {
                initUserData();
            }
        }
    }
}
