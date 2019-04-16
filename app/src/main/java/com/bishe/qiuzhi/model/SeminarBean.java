package com.bishe.qiuzhi.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SeminarBean {

    /**
     * title : 南京大桥机器
     * date : 今天8:00-12:30
     * location : 南京大学×鼓楼校区体育馆（汉口西路22号）
     * logo : http://pic.baike.soso.com/p/20131210/20131210112701-1317032153.jpg
     */

    private String title;
    private String date;
    private String location;
    private String logo;

    public static List<SeminarBean> arraySeminarBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<SeminarBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
