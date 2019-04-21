package com.bishe.qiuzhi.view;

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

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.adapter.SeminarAdapter;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.model.SeminarBean;
import com.bishe.qiuzhi.utils.GsonUtil;

public class SeminarFragment extends Fragment {
    private RecyclerView recyclerView;
    private SeminarAdapter mSeminarAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.semimar_fragment, container, false);
        recyclerView = view.findViewById(R.id.rv_seminar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mSeminarAdapter = new SeminarAdapter(getContext());
        recyclerView.setAdapter(mSeminarAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        mSeminarAdapter.setData(SeminarBean.arraySeminarBeanFromData(GsonUtil.getJsonStrFromFile(App.getApp(), "seminar.json")));
    }
}