package com.bishe.qiuzhi.net;

/**
 * 网络请求Response封装
 *
 * @param <T>
 */
public class Response<T> extends BaseResponse {
    private int code;
    private String msg;
    private T data;

    /**
     * 如果code返回是1 则认为成功
     * @return
     */
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
