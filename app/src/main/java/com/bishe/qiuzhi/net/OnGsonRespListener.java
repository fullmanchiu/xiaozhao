package com.bishe.qiuzhi.net;

/**
 * 网络请求后的回调
 * @param <T>
 */
public interface OnGsonRespListener<T> {
    void onSuccess(T data);

    void onFail(String error);
}
