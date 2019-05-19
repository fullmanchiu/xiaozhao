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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.Constants;
import com.bishe.qiuzhi.module.position.adapter.PositionAdapter;
import com.bishe.qiuzhi.module.position.model.Filter;
import com.bishe.qiuzhi.module.position.model.Location;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.module.search.view.SearchActivity;
import com.bishe.qiuzhi.net.Api;
import com.bishe.qiuzhi.net.OnGsonRespListener;
import com.bishe.qiuzhi.wedgit.NiceToolBar;

import java.util.ArrayList;
import java.util.List;

public class PositionFragment extends Fragment {
    private RecyclerView recyclerView;
    private PositionAdapter mPositionAdapter;
    private ProgressBar progressBar;
    private NiceToolBar niceToolBar;
    private Spinner spinnerLocation, spinnerIndustry, spinnerCompanyType;
    String filterLocation = "全部";
    int filterIndustryId, filterCompanyTypeId;
    private ArrayAdapter<String> locationAdapter, industryAdapter, companyTypeAdapter;
    private List<String> locationList, industryList, companyTypeList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.positon_fragment, container, false);
        progressBar = view.findViewById(R.id.pb);
        recyclerView = view.findViewById(R.id.rv_job);
        niceToolBar = view.findViewById(R.id.niceToolBar);
        niceToolBar.setOnSearchClickListener(() -> startActivity(new Intent(getActivity(), SearchActivity.class)));
        spinnerCompanyType = view.findViewById(R.id.spinner_more);
        companyTypeList = new ArrayList<>();
        companyTypeList.add("不限");
        companyTypeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, companyTypeList);
        spinnerCompanyType.setAdapter(companyTypeAdapter);
        spinnerCompanyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterCompanyTypeId = position;
                refreshRv();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerIndustry = view.findViewById(R.id.spinner_industry);
        industryList = new ArrayList<>();
        industryList.add("不限");
        industryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, industryList);
        spinnerIndustry.setAdapter(industryAdapter);
        spinnerIndustry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterIndustryId = position;
                refreshRv();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerLocation = view.findViewById(R.id.spinner_location);
        locationList = new ArrayList<>();
        locationList.add("全部");
        locationAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, locationList);
        spinnerLocation.setAdapter(locationAdapter);
        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterLocation = parent.getItemAtPosition(position).toString();
                refreshRv();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mPositionAdapter = new PositionAdapter(getContext());
        mPositionAdapter.setOnItemCLickListener((position, positionBean) -> {
            Intent intent = new Intent(getActivity(), PositionDetailActivity.class);
            intent.putExtra(Constants.EXTRA_POSITION_ID, positionBean.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(mPositionAdapter);
        return view;
    }

    private void refreshRv() {
        if (filterLocation.equals("全部")) {
            filterLocation = "";
        }
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        Api.getPositionData(filterLocation, filterIndustryId, filterCompanyTypeId, new OnGsonRespListener<List<PositionBean>>() {
            @Override
            public void onSuccess(List<PositionBean> data) {
                mPositionAdapter.setData(data);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(getContext(), "未查询到相关职位", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        Api.getLocationList(new OnGsonRespListener<List<Location>>() {

            @Override
            public void onSuccess(List<Location> data) {
                locationList.clear();
                locationList.add("全部");
                for (Location location : data) {
                    locationList.add(location.getLocation());
                }
                locationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String error) {

            }
        });
        Api.getPositionFilter(new OnGsonRespListener<Filter>() {
            @Override
            public void onSuccess(Filter data) {
                industryList.clear();
                for (Filter.IndustryBean industryBean : data.getIndustry()) {
                    industryList.add(industryBean.getIndustry_name());
                }
                industryAdapter.notifyDataSetChanged();
                companyTypeList.clear();
                for (Filter.CompanyTypyBean companyTypyBean : data.getCompany_typy()) {
                    companyTypeList.add(companyTypyBean.getCompany_type_name());
                }
                companyTypeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String error) {

            }
        });
        Api.getPositionData(new OnGsonRespListener<List<PositionBean>>() {
            @Override
            public void onSuccess(List<PositionBean> data) {
                mPositionAdapter.setData(data);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
