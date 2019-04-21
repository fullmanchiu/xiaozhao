package com.bishe.qiuzhi.net;


import com.bishe.qiuzhi.model.PositionBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/Position/getPositionList")
    Call<Response<List<PositionBean>>> getJobs();
}
