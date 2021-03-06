package com.bishe.qiuzhi.module.login.model;

import com.bishe.qiuzhi.module.login.contract.LoginContract;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LoginModel implements LoginContract.Model {

    /**
     * userinfo : {"id":1,"username":"admin","nickname":"admin","mobile":"13888888888","avatar":"/assets/img/avatar.png","score":0,"token":"0224a2a5-05e0-406a-bca6-d84f4ca26801","user_id":1,"createtime":1556472884,"expiretime":1559064884,"expires_in":2592000}
     */

    private UserinfoBean userinfo;


    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public static class UserinfoBean {
        public static final String USER_TABLE_NAME = "user";
        public static final String USER_TABLE_COLUMN_USER_ID = "user_id";
        public static final String USER_TABLE_COLUMN_USER_NAME = "user_name";
        public static final String USER_TABLE_COLUMN_NICK_NAME = "nick_name";
        public static final String USER_TABLE_COLUMN_USER_EMAIL = "user_email";
        public static final String USER_TABLE_COLUMN_MOBILE = "mobile";
        public static final String USER_TABLE_COLUMN_AVATAR = "avatar";
        public static final String USER_TABLE_COLUMN_TOKEN = "token";
        /**
         * id : 1
         * username : admin
         * nickname : admin
         * mobile : 13888888888
         * avatar : /assets/img/avatar.png
         * score : 0
         * token : 0224a2a5-05e0-406a-bca6-d84f4ca26801
         * user_id : 1
         * createtime : 1556472884
         * expiretime : 1559064884
         * expires_in : 2592000
         */

        private int id;
        private String username;
        private String nickname;
        private String mobile;
        private String avatar;
        private String email;
        private int score;
        private String token;
        private int user_id;
        private int createtime;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        private int expiretime;
        private int expires_in;

        public UserinfoBean(int userId, String userName, String nickName, String email, String mobile, String avatar, String token) {
            this.user_id = userId;
            this.username = userName;
            this.nickname = nickName;
            this.email = email;
            this.mobile = mobile;
            this.avatar = avatar;
            this.token = token;
        }

        public static List<UserinfoBean> arrayUserinfoBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<UserinfoBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getExpiretime() {
            return expiretime;
        }

        public void setExpiretime(int expiretime) {
            this.expiretime = expiretime;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }
    }
}
