package com.bishe.qiuzhi.net;

import com.bishe.qiuzhi.module.login.model.LoginModel;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.module.seminar.model.SeminarBean;

import java.util.List;

public class Api {
    private static final String HTTP_SCHEMA;
    private static final String HTTP_SCHEMA_SUFFIX;
    private static final String HOST;
    private static ApiService apiService;

    private static String getDomain() {
        return HTTP_SCHEMA + HTTP_SCHEMA_SUFFIX + HOST;
    }

    static {
        HTTP_SCHEMA = "http";
        HTTP_SCHEMA_SUFFIX = "://";
        HOST = "ql.crm-embrace.vip";
        apiService = ApiGsonBase.getGsonRetrofit(getDomain()).create(ApiService.class);
    }

    public static void getPositionData(OnGsonRespListener<List<PositionBean>> listener) {
        ApiGsonBase.enqueue(apiService.getJobs(), listener);
    }

    public static void getSeminarData(OnGsonRespListener<List<SeminarBean>> listener) {
        ApiGsonBase.enqueue(apiService.getSeminars(), listener);
    }

    public static void signIn(String userName, String pwd, OnGsonRespListener<LoginModel> listener) {
        ApiGsonBase.enqueue(apiService.signIn(userName,pwd),listener);
    }

    public static void signUp(String userName, String pwd, String email, String tel, OnGsonRespListener listener) {
        ApiGsonBase.enqueue(apiService.signUp(userName,pwd,email,tel),listener);
    }
}
