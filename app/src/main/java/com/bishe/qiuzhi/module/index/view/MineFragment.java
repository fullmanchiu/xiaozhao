package com.bishe.qiuzhi.module.index.view;

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
import com.bishe.qiuzhi.module.login.view.LoginActivity;

public class MineFragment extends Fragment {
    private TextView tvName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        tvName = view.findViewById(R.id.tv_login);
        tvName.setOnClickListener(v -> startActivity(new Intent(getContext(), LoginActivity.class)));
        return view;
    }
}
