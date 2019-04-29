package com.bishe.qiuzhi.net;

public abstract class BaseResponse<T> {
    public abstract boolean isSuccess();

    public abstract String getMessage();

    public abstract T getData();
}
