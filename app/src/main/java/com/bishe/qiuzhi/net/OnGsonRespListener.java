package com.bishe.qiuzhi.net;

public interface OnGsonRespListener<T> {
    void onSuccess(T data);

    void onFail(String error);
}
