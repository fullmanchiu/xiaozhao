package com.bishe.qiuzhi.module.position.view;

import android.content.Intent;
import android.os.Bundle;
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
import com.bishe.qiuzhi.module.position.adapter.PositionAdapter;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.net.Api;
import com.bishe.qiuzhi.net.OnGsonRespListener;

import java.util.List;

public class PositionFragment extends Fragment {
    private RecyclerView recyclerView;
    private PositionAdapter mPositionAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.job_fragment, container, false);
        recyclerView = view.findViewById(R.id.rv_job);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mPositionAdapter = new PositionAdapter(getContext());
        mPositionAdapter.setOnItemCLickListener((position, positionBean) -> {
            Intent intent = new Intent(getActivity(), PositionDetailActivity.class);
            startActivity(intent);
        });
        recyclerView.setAdapter(mPositionAdapter);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        Api.getPositionData(new OnGsonRespListener<List<PositionBean>>() {
            @Override
            public void onSuccess(List<PositionBean> data) {
                mPositionAdapter.setData(data);
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
