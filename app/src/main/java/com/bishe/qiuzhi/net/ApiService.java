package com.bishe.qiuzhi.net;


import com.bishe.qiuzhi.module.login.model.LoginModel;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.module.position.model.PositionDetailModel;
import com.bishe.qiuzhi.module.resume.model.ResumeModel;
import com.bishe.qiuzhi.module.seminar.model.SeminarBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/Position/getPositionList")
    Call<Response<List<PositionBean>>> getJobs();

    @GET("api/Seminar/getSeminarList")
    Call<Response<List<SeminarBean>>> getSeminars();

    @GET("api/user/login")
    Call<Response<LoginModel>> signIn(@Query("account") String userName, @Query("password") String pwd);

    @GET("api/user/register")
    Call<Response> signUp(@Query("username") String userName, @Query("password") String pwd, @Query("email") String email, @Query("mobile") String tel);

    @GET("api/user/logout")
    Call<Response> signOut(@Query("token") String token);

    @GET("api/Position/getPosition")
    Call<Response<PositionDetailModel>> getPositionDetail(@Query("position_id") int id);

    @GET("api/Resume/getResume")
    Call<Response<ResumeModel>> getResume(@Query("user_id") int uid, @Query("token") String token);

    @GET("api/Resume/addResume")
    Call<Response> postResume(@Query("user_id") int uid, @Query("content") String resume, @Query("token") String token);

    @GET("api/Position/getPositionList")
    Call<Response<List<PositionBean>>> getPositionByCompanyId(@Query("company_id") int companyId);
}
