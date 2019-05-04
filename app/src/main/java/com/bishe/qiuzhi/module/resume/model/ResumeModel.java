package com.bishe.qiuzhi.module.resume.model;

import com.bishe.qiuzhi.module.resume.contract.ResumeContract;

public class ResumeModel implements ResumeContract.Model {

    public ResumeModel(String user_id, MyInfoBean my_info, EducationBean education, TargetBean target) {
        this.user_id = user_id;
        this.my_info = my_info;
        this.education = education;
        this.target = target;
    }

    /**
     * user_id : 1
     * my_info : {"name":"","sex":"","birthday":"","address":"","phone":"","email":""}
     * education : {"school":"","start_time":"","end_time":"","qualifications":"","skill":""}
     * target : {"work_type":"","work_address":"","work_salary":"","tag":"","work_industry":""}
     */

    private String user_id;
    private MyInfoBean my_info;
    private EducationBean education;
    private TargetBean target;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public MyInfoBean getMy_info() {
        return my_info;
    }

    public void setMy_info(MyInfoBean my_info) {
        this.my_info = my_info;
    }

    public EducationBean getEducation() {
        return education;
    }

    public void setEducation(EducationBean education) {
        this.education = education;
    }

    public TargetBean getTarget() {
        return target;
    }

    public void setTarget(TargetBean target) {
        this.target = target;
    }

    public static class MyInfoBean {
        public MyInfoBean(String name, String sex, String birthday, String address, String phone, String email) {
            this.name = name;
            this.sex = sex;
            this.birthday = birthday;
            this.address = address;
            this.phone = phone;
            this.email = email;
        }

        /**
         * name :
         * sex :
         * birthday :
         * address :
         * phone :
         * email :
         */

        private String name;
        private String sex;
        private String birthday;
        private String address;
        private String phone;
        private String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class EducationBean {
        public EducationBean(String school, String start_time, String end_time, String qualifications, String skill) {
            this.school = school;
            this.start_time = start_time;
            this.end_time = end_time;
            this.qualifications = qualifications;
            this.skill = skill;
        }

        /**
         * school :
         * start_time :
         * end_time :
         * qualifications :
         * skill :
         */

        private String school;
        private String start_time;
        private String end_time;
        private String qualifications;
        private String skill;

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getQualifications() {
            return qualifications;
        }

        public void setQualifications(String qualifications) {
            this.qualifications = qualifications;
        }

        public String getSkill() {
            return skill;
        }

        public void setSkill(String skill) {
            this.skill = skill;
        }
    }

    public static class TargetBean {
        public TargetBean(String work_type, String work_address, String work_salary, String tag, String work_industry) {
            this.work_type = work_type;
            this.work_address = work_address;
            this.work_salary = work_salary;
            this.tag = tag;
            this.work_industry = work_industry;
        }

        /**
         * work_type :
         * work_address :
         * work_salary :
         * tag :
         * work_industry :
         */

        private String work_type;
        private String work_address;
        private String work_salary;
        private String tag;
        private String work_industry;


        public String getWork_type() {
            return work_type;
        }

        public void setWork_type(String work_type) {
            this.work_type = work_type;
        }

        public String getWork_address() {
            return work_address;
        }

        public void setWork_address(String work_address) {
            this.work_address = work_address;
        }

        public String getWork_salary() {
            return work_salary;
        }

        public void setWork_salary(String work_salary) {
            this.work_salary = work_salary;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getWork_industry() {
            return work_industry;
        }

        public void setWork_industry(String work_industry) {
            this.work_industry = work_industry;
        }
    }
}
