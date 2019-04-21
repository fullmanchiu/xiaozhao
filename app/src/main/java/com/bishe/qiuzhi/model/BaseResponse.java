package com.bishe.qiuzhi.model;

public abstract class BaseResponse<T> {
    public abstract boolean isSuccess();

    public abstract String getMessage();

    public abstract T getData();
}
