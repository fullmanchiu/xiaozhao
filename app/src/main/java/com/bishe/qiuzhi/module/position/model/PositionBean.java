package com.bishe.qiuzhi.module.position.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PositionBean {



    /**
     * id : 3
     * title : 插画实习生
     * salary_min : 3000
     * salary_max : 5000
     * company_id : 3
     * company_name : 蜗牛游戏
     * company_logo : http://img2.tuohuangzu.com/THZ/UserBlog/0/14/2015040715442435887.jpg
     * location : 苏州
     * num : 0
     * publish_time : 2019-04-20 17:58:32
     * sort : 0
     * status : 1
     * create_time : 2019-04-20 17:58:36
     * update_time : 2019-04-20 17:58:38
     */

    private int id;
    private String title;
    @SerializedName("salary_min")
    private int salaryMin;
    @SerializedName("salary_max")
    private int salaryMax;
    @SerializedName("company_id")
    private int companyId;
    @SerializedName("company_name")
    private String companyName;
    @SerializedName("company_logo")
    private String companyLogo;
    private String location;
    private int num;
    @SerializedName("publish_time")
    private String publishTime;
    private int sort;
    private int status;
    @SerializedName("create_time")
    private String createTime;
    @SerializedName("update_time")
    private String updateTime;

    public static List<PositionBean> arrayJobBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<PositionBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(int salaryMin) {
        this.salaryMin = salaryMin;
    }

    public int getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(int salaryMax) {
        this.salaryMax = salaryMax;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
