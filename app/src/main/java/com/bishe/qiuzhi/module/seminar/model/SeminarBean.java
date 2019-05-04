package com.bishe.qiuzhi.module.seminar.model;

public class SeminarBean {

    /**
     * id : 1
     * name : 海底捞餐饮
     * school_id : 2
     * address : 56创-16号会议室
     * start_time : 2019-04-24 23:54:16
     * end_time : 2019-04-27 23:54:19
     * content : null
     * weigh : 1
     * create_time : 2019-04-24 23:55:26
     * update_time : 2019-04-24 23:55:29
     * school : {"id":2,"name":"西交大","image":"https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=50b25991fb36afc30e0c38638b228cf9/203fb80e7bec54e7652c0e4fb5389b504ec26a9a.jpg","weigh":2,"create_time":"2019-04-27 23:27:24","update_time":"2019-04-27 23:27:24"}
     */

    private int id;
    private String name;
    private int school_id;
    private String address;
    private long start_time;
    private long end_time;
    private String content;
    private int weigh;
    private String create_time;
    private String update_time;
    private SchoolBean school;

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

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
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
         * image : https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=50b25991fb36afc30e0c38638b228cf9/203fb80e7bec54e7652c0e4fb5389b504ec26a9a.jpg
         * weigh : 2
         * create_time : 2019-04-27 23:27:24
         * update_time : 2019-04-27 23:27:24
         */

        private int id;
        private String name;
        private String image;
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
