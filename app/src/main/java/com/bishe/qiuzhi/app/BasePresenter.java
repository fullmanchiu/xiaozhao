package com.bishe.qiuzhi.app;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
