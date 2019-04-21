//package com.bishe.qiuzhi.app;
//
//import java.io.IOException;
//
//import okhttp3.Interceptor;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class ExpireInterceptor implements Interceptor {
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        final Request originalRequest = chain.request();
//        Request coreRequest = doInterceptRequest(originalRequest);
//        Response response = chain.proceed(coreRequest);
//        if (!response.isSuccessful()) {
//
//        }
//        return response;
//    }
//
//    private Request doInterceptRequest(Request originalRequest) {
//        return null;
//    }
//}
