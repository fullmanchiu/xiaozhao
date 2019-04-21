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
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.adapter.JobAdapter;
import com.bishe.qiuzhi.net.Api;
import com.bishe.qiuzhi.net.OnGsonRespListener;
import com.bishe.qiuzhi.model.PositionBean;

import java.util.List;

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
        //mJobAdapter.setData(PositionBean.arrayJobBeanFromData(GsonUtil.getJsonStrFromFile(App.getApp(), "job.json")));
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://ql.crm-embrace.vip/").addConverterFactory(GsonConverterFactory.create())
//                .client(new OkHttpClient())
//                .build();
//        ApiService apiService = retrofit.create(ApiService.class);
//        Call<Response<List<PositionBean>>> job = apiService.getJobs();
//        job.enqueue(new Callback<Response<List<PositionBean>>>() {
//            @Override
//            public void onResponse(Call<Response<List<PositionBean>>> call, retrofit2.Response<Response<List<PositionBean>>> response) {
//                mJobAdapter.setData((List<PositionBean>) response.body().getData());
//            }
//
//            @Override
//            public void onFailure(Call<Response<List<PositionBean>>> call, Throwable t) {
//            }
//        });
        Api.getPositionData(new OnGsonRespListener<List<PositionBean>>() {
            @Override
            public void onSuccess(List<PositionBean> data) {
                mJobAdapter.setData(data);
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
