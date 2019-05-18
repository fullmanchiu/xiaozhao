package com.bishe.qiuzhi.module.seminar.view;

import android.content.Intent;
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
import com.bishe.qiuzhi.app.Constants;
import com.bishe.qiuzhi.module.position.view.PositionDetailActivity;
import com.bishe.qiuzhi.module.search.view.SearchActivity;
import com.bishe.qiuzhi.module.seminar.adapter.SeminarAdapter;
import com.bishe.qiuzhi.module.seminar.model.SeminarBean;
import com.bishe.qiuzhi.net.Api;
import com.bishe.qiuzhi.net.OnGsonRespListener;
import com.bishe.qiuzhi.wedgit.NiceToolBar;

import java.util.List;
import java.util.PropertyResourceBundle;

public class SeminarFragment extends Fragment {
    private RecyclerView recyclerView;
    private SeminarAdapter mSeminarAdapter;
    private NiceToolBar niceToolBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.semimar_fragment, container, false);
        recyclerView = view.findViewById(R.id.rv_seminar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        niceToolBar = view.findViewById(R.id.niceToolBar);
        niceToolBar.setOnSearchClickListener(() -> startActivity(new Intent(getActivity(), SearchActivity.class)));
        mSeminarAdapter = new SeminarAdapter(getContext());
        mSeminarAdapter.setOnItemCLickListener((position, seminarBean) -> {
            Intent intent = new Intent(getActivity(), SeminarDetailActivity.class);
            intent.putExtra(Constants.EXTRA_SEMINAR_ID, seminarBean.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(mSeminarAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        Api.getSeminarData(new OnGsonRespListener<List<SeminarBean>>() {
            @Override
            public void onSuccess(List<SeminarBean> data) {
                mSeminarAdapter.setData(data);
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
