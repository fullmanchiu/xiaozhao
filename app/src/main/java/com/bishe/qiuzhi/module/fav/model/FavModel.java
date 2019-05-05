package com.bishe.qiuzhi.module.fav.model;

import com.bishe.qiuzhi.module.fav.contract.FavContract;
import com.google.gson.Gson;

public class FavModel implements FavContract.Model {
    public class FavSeminarModel {

        /**
         * id : 2
         * name : 博腾制药
         * school_id : 3
         * address : 7斋125宣讲室
         * start_time : 1556121380
         * end_time : 1556380582
         * content : <p>12341</p>
         * weigh : 2
         * create_time : 2019-04-24 23:56:28
         * update_time : 2019-04-24 23:56:31
         */

        private int id;
        private String name;
        private int school_id;
        private String address;
        private int start_time;
        private int end_time;
        private String content;
        private int weigh;
        private String create_time;
        private String update_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSchool_id() {
            return school_id;
        }

        public void setSchool_id(int school_id) {
            this.school_id = school_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getWeigh() {
            return weigh;
        }

        public void setWeigh(int weigh) {
            this.weigh = weigh;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }
}
