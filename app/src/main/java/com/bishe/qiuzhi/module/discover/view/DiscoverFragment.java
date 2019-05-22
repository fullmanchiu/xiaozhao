package com.bishe.qiuzhi.module.discover.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.module.discover.adapter.DiscoverAdapter;
import com.bishe.qiuzhi.module.discover.model.DiscoverModel;
import com.bishe.qiuzhi.net.Api;
import com.bishe.qiuzhi.net.OnGsonRespListener;

import java.util.List;

public class DiscoverFragment extends Fragment {
    private RecyclerView rvDiscover;
    private DiscoverAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_fragment, container, false);
        rvDiscover = view.findViewById(R.id.rv_discover);
        rvDiscover.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvDiscover.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter = new DiscoverAdapter(getContext());
        adapter.setOnItemCLickListener((position, seminarBean) -> {
            Uri uri = Uri.parse(seminarBean.getUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        rvDiscover.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        Api.getDiscoverData(new OnGsonRespListener<List<DiscoverModel>>() {
            @Override
            public void onSuccess(List<DiscoverModel> data) {
                if (data != null && data.size() != 0) {
                    adapter.setData(data);
                } else {
                    Toast.makeText(getContext(), "第三方新闻资讯接口返回数据为空", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
