package com.bishe.qiuzhi.net;

import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.module.login.model.LoginModel;
import com.bishe.qiuzhi.module.position.model.PositionBean;
import com.bishe.qiuzhi.module.position.model.PositionDetailModel;
import com.bishe.qiuzhi.module.resume.model.ResumeModel;
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
        //HOST = "xz.colafans.cn/index.php/";
        HOST = "ql.crm-embrace.vip";
        apiService = ApiGsonBase.getGsonRetrofit(getDomain()).create(ApiService.class);
    }

    private static String getToken() {
        return App.getInstance().getUserData().getToken();
    }

    private static int getUserId() {
        return App.getInstance().getUserData().getUser_id();
    }

    public static void getPositionData(OnGsonRespListener<List<PositionBean>> listener) {
        ApiGsonBase.enqueue(apiService.getJobs(), listener);
    }

    public static void getSeminarData(OnGsonRespListener<List<SeminarBean>> listener) {
        ApiGsonBase.enqueue(apiService.getSeminars(), listener);
    }

    public static void signIn(String userName, String pwd, OnGsonRespListener<LoginModel> listener) {
        ApiGsonBase.enqueue(apiService.signIn(userName, pwd), listener);
    }

    public static void signUp(String userName, String pwd, String email, String tel, OnGsonRespListener listener) {
        ApiGsonBase.enqueue(apiService.signUp(userName, pwd, email, tel), listener);
    }

    public static void signOut(OnGsonRespListener listener) {
        ApiGsonBase.enqueue(apiService.signOut(getToken()), listener);
    }

    public static void getPositionDetail(int id, OnGsonRespListener<PositionDetailModel> listener) {
        ApiGsonBase.enqueue(apiService.getPositionDetail(id), listener);
    }

    public static void getResume(int uid, OnGsonRespListener<ResumeModel> listener) {
        ApiGsonBase.enqueue(apiService.getResume(uid, getToken()), listener);
    }

    public static void postResume(int uid, String resume, OnGsonRespListener listener) {
        ApiGsonBase.enqueue(apiService.postResume(uid, resume, getToken()), listener);
    }

    public static void getPositionByCompanyId(int companyId, OnGsonRespListener<PositionBean> listener) {
        ApiGsonBase.enqueue(apiService.getPositionByCompanyId(companyId), listener);
    }

    public static void favPosition(int id, OnGsonRespListener listener) {
        ApiGsonBase.enqueue(apiService.favPostion(id, App.getInstance().getUserData().getUser_id(), getToken()), listener);
    }

    public static void unFavPosition(int id, OnGsonRespListener listener) {
        ApiGsonBase.enqueue(apiService.unFavPosition(id, getUserId(), getToken()), listener);
    }


}
