package com.bishe.qiuzhi.module.position.model;

import com.bishe.qiuzhi.module.position.contract.PositionDetailContract;

public class PositionDetailModel implements PositionDetailContract.Model {

    /**
     * id : 1
     * title : Android实习生
     * salary_min : 3000
     * salary_max : 5000
     * company_id : 1
     * location : 杭州
     * num : 3
     * publish_time : 2019-04-20 17:52:54
     * industry :
     * type : 1
     * content : 345345
     * weigh : 0
     * create_time : 2019-04-20 17:52:57
     * update_time : 2019-04-20 17:53:00
     * company : {"id":1,"name":"阿里巴巴","image":"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=1ef9303ddb1373f0f53f68999c342cc6/caef76094b36acafe1673eba76d98d1000e99cf5.jpg","describe":"的撒二哈人家客户给了他人体秤吃VBVBVBVB xfhdcr6 f fg7t6fgv7 7gvbyuyabv67uuie4htuiehtarughuiarhguiarhguifghruifguisghfi8uacb4ycv7893ycvn4yc784ctn7yxc747xcn834","industry":"计算机软件","company_scale":"少于50人","company_type":"外资独企","weigh":1,"create_time":"2019-04-25 22:57:19","update_time":"2019-04-25 22:57:19"}
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
    private int is_send;
    private int is_fav;

    public int getIs_send() {
        return is_send;
    }

    public void setIs_send(int is_send) {
        this.is_send = is_send;
    }

    public int getIs_fav() {
        return is_fav;
    }

    public void setIs_fav(int is_fav) {
        this.is_fav = is_fav;
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
         * id : 1
         * name : 阿里巴巴
         * image : https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=1ef9303ddb1373f0f53f68999c342cc6/caef76094b36acafe1673eba76d98d1000e99cf5.jpg
         * describe : 的撒二哈人家客户给了他人体秤吃VBVBVBVB xfhdcr6 f fg7t6fgv7 7gvbyuyabv67uuie4htuiehtarughuiarhguiarhguifghruifguisghfi8uacb4ycv7893ycvn4yc784ctn7yxc747xcn834
         * industry : 计算机软件
         * company_scale : 少于50人
         * company_type : 外资独企
         * weigh : 1
         * create_time : 2019-04-25 22:57:19
         * update_time : 2019-04-25 22:57:19
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
