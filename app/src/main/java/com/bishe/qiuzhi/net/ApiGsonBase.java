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

public class ApiGsonBase {
    public static final String TAG = "Api";

    public static Retrofit getGsonRetrofit(String domain) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(message -> Log.d(TAG, message));

        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这一句一定要记得写，否则没有数据输出
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(10_000, TimeUnit.MILLISECONDS)
                .readTimeout(30_000, TimeUnit.MILLISECONDS)
                .writeTimeout(10_000, TimeUnit.MILLISECONDS)
                .addInterceptor(logInterceptor).build();

        return new Retrofit.Builder()
                .baseUrl(domain)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static <R extends BaseResponse<T>, T> void enqueue(Call<R> enqueueCall, final OnGsonRespListener<T> listener) {
        enqueueCall.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (listener != null) {
                    R data = response.body();
                    if (data != null) {
                        if (data.isSuccess()) {
                            listener.onSuccess(data.getData());
                        } else {
                            listener.onFail(data.getMessage());
                        }
                    } else {
                        Log.d(TAG, "response is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                if (listener != null) {
                    listener.onFail(t.toString());
                }
            }
        });

    }
}
