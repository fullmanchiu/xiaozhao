package com.bishe.qiuzhi.module.index.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.bishe.qiuzhi.R;
import com.bishe.qiuzhi.adapter.ViewPagerAdapter;
import com.bishe.qiuzhi.app.BaseActivity;
import com.bishe.qiuzhi.module.discover.view.DiscoverFragment;
import com.bishe.qiuzhi.module.index.contract.MainContract;
import com.bishe.qiuzhi.module.index.presenter.MainPresenterImpl;
import com.bishe.qiuzhi.module.position.view.PositionFragment;
import com.bishe.qiuzhi.module.seminar.view.SeminarFragment;

/**
 * 主界面
 * 四个fragment 通过viewpager加载
 */
public class MainActivity extends BaseActivity<MainPresenterImpl> implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    private BottomNavigationView bottomNavigationView;//底部导航栏
    private ViewPager viewPager;//viewpager 用来管理fragment的滑动
    private MenuItem menuItem;// 底部导航栏的单个菜单项

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

    /**
     * 初始化viewpager
     */
    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new PositionFragment());
        adapter.addFragment(new SeminarFragment());
        adapter.addFragment(new DiscoverFragment());
        adapter.addFragment(new MineFragment());
        viewPager.setAdapter(adapter);
    }

    /**
     * 底部导航栏点击事件
     * viewpager切换监听
     */
    private void initListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    /**
     * 底部导航栏点击时，切换viewpager
     *
     * @param menuItem
     * @return
     */
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

    /**
     * viewpager切换时 更新底部导航栏状态
     *
     * @param position
     */
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

    public void setToOne() {
        viewPager.setCurrentItem(0);
    }
}
