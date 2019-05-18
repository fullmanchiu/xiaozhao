package com.bishe.qiuzhi.module.search.view;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.app.Constants;
import com.bishe.qiuzhi.module.position.adapter.PositionAdapter;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.module.position.view.PositionDetailActivity;
import com.bishe.qiuzhi.module.search.contract.SearchContract;
import com.bishe.qiuzhi.module.search.presenter.SearchPresenter;
import com.bishe.qiuzhi.module.seminar.adapter.SeminarAdapter;
import com.bishe.qiuzhi.module.seminar.model.SeminarBean;
import com.bishe.qiuzhi.module.seminar.view.SeminarDetailActivity;
import com.bishe.qiuzhi.net.OnGsonRespListener;

import org.w3c.dom.Text;

import java.util.List;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {

    private ImageView ivBack, ivClear;
    private Spinner spinner;
    private EditText editText;
    private Button btnSearch;
    private RecyclerView rvPosition, rvSeminar;
    private int searchTag = 0;
    private PositionAdapter mPositionAdapter;
    private SeminarAdapter mSeminarAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        ivBack = findViewById(R.id.iv_back);
        ivClear = findViewById(R.id.iv_clear);
        spinner = findViewById(R.id.spinner);
        editText = findViewById(R.id.et_search_value);
        btnSearch = findViewById(R.id.btn_search);
        rvPosition = findViewById(R.id.rv_position);
        rvSeminar = findViewById(R.id.rv_seminar);
    }

    @Override
    protected void initEventAndData() {
        ivBack.setOnClickListener(v -> onBackPressed());
        ivClear.setOnClickListener(v -> editText.setText(""));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    ivClear.setVisibility(View.GONE);
                } else {
                    ivClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchTag = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnSearch.setOnClickListener(v -> {
            String search = editText.getText().toString();
            if (TextUtils.isEmpty(search)) {
                Toast.makeText(mContext, R.string.search_empty, Toast.LENGTH_SHORT).show();
            } else {
                search(search);
            }
        });

        rvPosition.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvPosition.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mPositionAdapter = new PositionAdapter(mContext);
        mPositionAdapter.setOnItemCLickListener((position, positionBean) -> {
            Intent intent = new Intent(mContext, PositionDetailActivity.class);
            intent.putExtra(Constants.EXTRA_POSITION_ID, positionBean.getId());
            startActivity(intent);
        });
        rvPosition.setAdapter(mPositionAdapter);

        rvSeminar.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvSeminar.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mSeminarAdapter = new SeminarAdapter(mContext);
        mSeminarAdapter.setOnItemCLickListener((position, seminarBean) -> {
            Intent intent = new Intent(mContext, SeminarDetailActivity.class);
            intent.putExtra(Constants.EXTRA_SEMINAR_ID, seminarBean.getId());
            startActivity(intent);
        });
        rvSeminar.setAdapter(mSeminarAdapter);
    }

    private void search(String search) {
        rvSeminar.setVisibility(View.GONE);
        rvPosition.setVisibility(View.GONE);
        if (searchTag == 0) {
            mPresenter.searchPosition(search);
        } else {
            mPresenter.searchSeminar(search);
        }
    }

    @Override
    protected void createPresenter() {
        mPresenter = new SearchPresenter();
    }

    @Override
    public OnGsonRespListener<List<PositionBean>> onGetSearchPositionResult() {
        return new OnGsonRespListener<List<PositionBean>>() {
            @Override
            public void onSuccess(List<PositionBean> data) {
                mPositionAdapter.setData(data);
                rvPosition.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mContext, R.string.search_result_empty, Toast.LENGTH_SHORT).show();
                rvPosition.setVisibility(View.GONE);
                rvSeminar.setVisibility(View.GONE);
            }
        };
    }

    @Override
    public OnGsonRespListener<List<SeminarBean>> onGetSearchSeminarResult() {
        return new OnGsonRespListener<List<SeminarBean>>() {
            @Override
            public void onSuccess(List<SeminarBean> data) {
                mSeminarAdapter.setData(data);
                rvSeminar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(mContext, R.string.search_result_empty, Toast.LENGTH_SHORT).show();
                rvPosition.setVisibility(View.GONE);
                rvSeminar.setVisibility(View.GONE);
            }
        };
    }
}
