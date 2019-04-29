package com.bishe.qiuzhi.module.index.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.adapter.ViewPagerAdapter;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.module.index.contract.MainContract;
import com.bishe.qiuzhi.module.index.presenter.MainPresenterImpl;
import com.bishe.qiuzhi.module.position.view.PositionFragment;
import com.bishe.qiuzhi.module.seminar.view.SeminarFragment;

public class MainActivity extends BaseActivity<MainPresenterImpl> implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private MenuItem menuItem;

    @Override
    protected void initEventAndData() {
        initListener();
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        viewPager = findViewById(R.id.viewPager);
        setupViewPager();
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new PositionFragment());
        adapter.addFragment(new SeminarFragment());
        adapter.addFragment(new DiscoverFragment());
        adapter.addFragment(new MineFragment());
        viewPager.setAdapter(adapter);
    }

    private void initListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_job:
                viewPager.setCurrentItem(0);
                break;
            case R.id.nav_seminar:
                viewPager.setCurrentItem(1);
                break;
            case R.id.nav_discover:
                viewPager.setCurrentItem(2);
                break;
            case R.id.nav_mine:
                viewPager.setCurrentItem(3);
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            bottomNavigationView.getMenu().getItem(i).setChecked(false);
        }
        menuItem = bottomNavigationView.getMenu().getItem(position);
        menuItem.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}