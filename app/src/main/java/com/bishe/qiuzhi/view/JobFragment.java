package com.bishe.qiuzhi.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.adapter.JobAdapter;
import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.model.JobBean;
import com.bishe.qiuzhi.utils.GsonUtil;

public class JobFragment extends Fragment {
    private RecyclerView recyclerView;
    private JobAdapter mJobAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.job_fragment, container, false);
        recyclerView = view.findViewById(R.id.rv_job);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mJobAdapter = new JobAdapter(getContext());
        recyclerView.setAdapter(mJobAdapter);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        mJobAdapter.setData(JobBean.arrayJobBeanFromData(GsonUtil.getJsonStrFromFile(App.getApp(), "job.json")));
    }
}
