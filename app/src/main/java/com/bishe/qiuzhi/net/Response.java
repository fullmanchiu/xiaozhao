package com.bishe.qiuzhi.net;

public class Response<T> extends BaseResponse {
    private int code;
    private String msg;
    private T data;

    @Override
    public boolean isSuccess() {
        return code == 1;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public Object getData() {
        return data;
    }
}
