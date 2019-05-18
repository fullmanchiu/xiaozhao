package com.bishe.qiuzhi.net;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装网络请求框架
 */
public class ApiGsonBase {
    public static final String TAG = "Api";

    public static Retrofit getGsonRetrofit(String domain) {
        //网络请求日志拦截器，在日志中打印出网络请求
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(message -> Log.d(TAG, message));
        //设置日志拦截器的级别
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这一句一定要记得写，否则没有数据输出
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)//失败是重试
                .connectTimeout(10_000, TimeUnit.MILLISECONDS)//连接超时
                .readTimeout(30_000, TimeUnit.MILLISECONDS)//读取超时
                .writeTimeout(10_000, TimeUnit.MILLISECONDS)//写入超时
                .addInterceptor(logInterceptor).build();

        return new Retrofit.Builder()
                .baseUrl(domain)
                .addConverterFactory(GsonConverterFactory.create())//将json转换为对象
                .client(client)
                .build();
    }

    /**
     * 执行网络请求
     *
     * @param enqueueCall
     * @param listener    回调
     * @param <R>
     * @param <T>
     */
    public static <R extends BaseResponse<T>, T> void enqueue(Call<R> enqueueCall, final OnGsonRespListener<T> listener) {
        enqueueCall.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (listener != null) {
                    R data = response.body();
                    if (data != null) {
                        if (data.isSuccess()) {
                            //成功回调
                            listener.onSuccess(data.getData());
                        } else {
                            //失败回调
                            listener.onFail(data.getMessage());
                        }
                    } else {
                        listener.onFail("response is null");
                        Log.d(TAG, "response is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                if (listener != null) {
                    //网络请求失败回调
                    listener.onFail(t.toString());
                }
            }
        });

    }
}
