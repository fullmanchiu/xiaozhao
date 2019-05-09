package com.bishe.qiuzhi.net;

/**
 * 网络返回对象封装
 *
 * @param <T>
 */
public abstract class BaseResponse<T> {
    public abstract boolean isSuccess();

    public abstract String getMessage();

    public abstract T getData();
}
