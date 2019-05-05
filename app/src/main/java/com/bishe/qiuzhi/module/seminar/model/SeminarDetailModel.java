package com.bishe.qiuzhi.module.seminar.model;

import com.bishe.qiuzhi.module.seminar.contract.SeminarDetailContract;
import com.google.gson.Gson;

public class SeminarDetailModel implements SeminarDetailContract.Model {
    /**
     * id : 1
     * name : 海底捞餐饮
     * school_id : 2
     * address : 56创-16号会议室
     * start_time : 1556121256
     * end_time : 1556380459
     * content : wertg
     * weigh : 1
     * create_time : 2019-04-24 23:55:26
     * update_time : 2019-04-24 23:55:29
     * is_fav : 0
     * school : {"id":2,"name":"西交大","school_address":"苏州","school_content":"个人他一个人他","image":"/uploads/20190504/503c366734b0f1cfbaf6a362e146cc42.jpg","weigh":2,"create_time":"2019-04-27 23:27:24","update_time":"2019-04-27 23:27:24"}
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
    private int is_fav;
    private SchoolBean school;

    public static SeminarDetailModel objectFromData(String str) {

        return new Gson().fromJson(str, SeminarDetailModel.class);
    }

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

    public int getIs_fav() {
        return is_fav;
    }

    public void setIs_fav(int is_fav) {
        this.is_fav = is_fav;
    }

    public SchoolBean getSchool() {
        return school;
    }

    public void setSchool(SchoolBean school) {
        this.school = school;
    }

    public static class SchoolBean {
        /**
         * id : 2
         * name : 西交大
         * school_address : 苏州
         * school_content : 个人他一个人他
         * image : /uploads/20190504/503c366734b0f1cfbaf6a362e146cc42.jpg
         * weigh : 2
         * create_time : 2019-04-27 23:27:24
         * update_time : 2019-04-27 23:27:24
         */

        private int id;
        private String name;
        private String school_address;
        private String school_content;
        private String image;
        private int weigh;
        private String create_time;
        private String update_time;

        public static SchoolBean objectFromData(String str) {

            return new Gson().fromJson(str, SchoolBean.class);
        }

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

        public String getSchool_address() {
            return school_address;
        }

        public void setSchool_address(String school_address) {
            this.school_address = school_address;
        }

        public String getSchool_content() {
            return school_content;
        }

        public void setSchool_content(String school_content) {
            this.school_content = school_content;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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
