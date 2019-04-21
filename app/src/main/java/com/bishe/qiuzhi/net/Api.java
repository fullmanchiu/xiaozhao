package com.bishe.qiuzhi.net;

import com.bishe.qiuzhi.model.PositionBean;

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
}
