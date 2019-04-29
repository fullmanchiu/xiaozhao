package com.bishe.qiuzhi.module.index.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.module.login.view.LoginActivity;

import static android.app.Activity.RESULT_OK;

public class MineFragment extends Fragment {
    private TextView tvName;
    private int requestCode = 100;
    private boolean isLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        tvName = view.findViewById(R.id.tv_login);
        if (App.getInstance().getUserData() != null) {
            initUserData();
        }
        if (App.getInstance().getIsLogin()) {
            tvName.setOnClickListener(null);
        } else {
            tvName.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), LoginActivity.class), Activity.RESULT_FIRST_USER));
        }

        return view;
    }

    private void initUserData() {
        isLogin = true;
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
