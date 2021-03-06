package com.bishe.qiuzhi.module.position.model;

public class PositionBean {

    /**
     * id : 3
     * title : 插画实习生
     * salary_min : 3000
     * salary_max : 5000
     * company_id : 6
     * location : 苏州
     * num : 3
     * publish_time : 1555754312
     * industry :
     * type : 1
     * content : 43
     * weigh : 0
     * create_time : 2019-04-20 17:58:36
     * update_time : 2019-04-20 17:58:38
     * company : {"id":6,"name":"华为","image":"https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=31d4b5911ddfa9ecfd2e51115aeb903e/b03533fa828ba61e6bd41fa24d34970a304e5901.jpg","describe":"华为公司","industry":"计算机软件","company_scale":"少于50人","company_type":"外资独企","weigh":6,"create_time":"2019-04-27 23:29:11","update_time":"2019-04-27 23:29:11"}
     */

    private int id;
    private String title;
    private int salary_min;
    private int salary_max;
    private int company_id;
    private String location;
    private int num;
    private int publish_time;
    private String industry;
    private String type;
    private String content;
    private int weigh;
    private String create_time;
    private String update_time;
    private CompanyBean company;

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

    public int getSalary_min() {
        return salary_min;
    }

    public void setSalary_min(int salary_min) {
        this.salary_min = salary_min;
    }

    public int getSalary_max() {
        return salary_max;
    }

    public void setSalary_max(int salary_max) {
        this.salary_max = salary_max;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
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

    public int getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(int publish_time) {
        this.publish_time = publish_time;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public CompanyBean getCompany() {
        return company;
    }

    public void setCompany(CompanyBean company) {
        this.company = company;
    }

    public static class CompanyBean {
        /**
         * id : 6
         * name : 华为
         * image : https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=31d4b5911ddfa9ecfd2e51115aeb903e/b03533fa828ba61e6bd41fa24d34970a304e5901.jpg
         * describe : 华为公司
         * industry : 计算机软件
         * company_scale : 少于50人
         * company_type : 外资独企
         * weigh : 6
         * create_time : 2019-04-27 23:29:11
         * update_time : 2019-04-27 23:29:11
         */

        private int id;
        private String name;
        private String image;
        private String describe;
        private String industry;
        private String company_scale;
        private String company_type;
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

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getCompany_scale() {
            return company_scale;
        }

        public void setCompany_scale(String company_scale) {
            this.company_scale = company_scale;
        }

        public String getCompany_type() {
            return company_type;
        }

        public void setCompany_type(String company_type) {
            this.company_type = company_type;
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
