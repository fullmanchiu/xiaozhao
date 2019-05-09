package com.bishe.qiuzhi.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * BaseActivity
 * 所有新建 Activity 需要继承自BaseActivity
 *
 * @param <T>
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected T mPresenter;     // MVP 中的 presenter，业务逻辑需要由presenter处理
    protected Activity mContext; //context


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());//设置布局文件
        mContext = this;//给Context赋值
        createPresenter();//创建presenter
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        App.getInstance().addActivity(this);
        initView();
        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        App.getInstance().removeActivity(this);//activity 销毁时移出hashset
    }

    /**
     * 设置布局
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化监听器及数据
     */
    protected abstract void initEventAndData();

    /**
     * 创建presenter
     */
    protected abstract void createPresenter();

    /**
     * 显示错误
     */
    @Override
    public void showError() {

    }
}
