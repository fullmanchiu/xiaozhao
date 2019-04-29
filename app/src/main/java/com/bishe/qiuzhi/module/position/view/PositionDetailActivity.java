package com.bishe.qiuzhi.module.position.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.wedgit.TitleBar;

public class PositionDetailActivity extends AppCompatActivity {
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_detail_layout);
        initView();
        initListener();
    }

    private void initView() {
        titleBar = findViewById(R.id.titleBar);
    }

    private void initListener() {
        titleBar.setOnBackClickListener(() -> onBackPressed());
    }
}
