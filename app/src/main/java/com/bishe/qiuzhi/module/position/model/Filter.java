package com.bishe.qiuzhi.module.position.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Filter {
    private List<IndustryBean> industry;
    private List<CompanyTypyBean> company_typy;
    private List<CompanyScaleBean> company_scale;
    private List<SalaryBean> salary;

    public List<IndustryBean> getIndustry() {
        return industry;
    }

    public void setIndustry(List<IndustryBean> industry) {
        this.industry = industry;
    }

    public List<CompanyTypyBean> getCompany_typy() {
        return company_typy;
    }

    public void setCompany_typy(List<CompanyTypyBean> company_typy) {
        this.company_typy = company_typy;
    }

    public List<CompanyScaleBean> getCompany_scale() {
        return company_scale;
    }

    public void setCompany_scale(List<CompanyScaleBean> company_scale) {
        this.company_scale = company_scale;
    }

    public List<SalaryBean> getSalary() {
        return salary;
    }

    public void setSalary(List<SalaryBean> salary) {
        this.salary = salary;
    }

    public static class IndustryBean {
        /**
         * industry_id : 0
         * industry_name : 不限
         */

        private int industry_id;
        private String industry_name;

        public int getIndustry_id() {
            return industry_id;
        }

        public void setIndustry_id(int industry_id) {
            this.industry_id = industry_id;
        }

        public String getIndustry_name() {
            return industry_name;
        }

        public void setIndustry_name(String industry_name) {
            this.industry_name = industry_name;
        }
    }

    public static class CompanyTypyBean {
        /**
         * company_type_id : 0
         * company_type_name : 不限
         */

        private int company_type_id;
        private String company_type_name;

        public int getCompany_type_id() {
            return company_type_id;
        }

        public void setCompany_type_id(int company_type_id) {
            this.company_type_id = company_type_id;
        }

        public String getCompany_type_name() {
            return company_type_name;
        }

        public void setCompany_type_name(String company_type_name) {
            this.company_type_name = company_type_name;
        }
    }

    public static class CompanyScaleBean {
        /**
         * company_scale_id : 0
         * company_scale_name : 不限
         */

        private int company_scale_id;
        private String company_scale_name;

        public int getCompany_scale_id() {
            return company_scale_id;
        }

        public void setCompany_scale_id(int company_scale_id) {
            this.company_scale_id = company_scale_id;
        }

        public String getCompany_scale_name() {
            return company_scale_name;
        }

        public void setCompany_scale_name(String company_scale_name) {
            this.company_scale_name = company_scale_name;
        }
    }

    public static class SalaryBean {
        /**
         * salary_id : 0
         * salary_name : 不限
         * value : {"position.salary_max":["<",1000]}
         */

        private int salary_id;
        private String salary_name;
        private ValueBean value;

        public int getSalary_id() {
            return salary_id;
        }

        public void setSalary_id(int salary_id) {
            this.salary_id = salary_id;
        }

        public String getSalary_name() {
            return salary_name;
        }

        public void setSalary_name(String salary_name) {
            this.salary_name = salary_name;
        }

        public ValueBean getValue() {
            return value;
        }

        public void setValue(ValueBean value) {
            this.value = value;
        }

        public static class ValueBean {
            @SerializedName("position.salary_max")
            private List<String> _$PositionSalary_max103; // FIXME check this code

            public List<String> get_$PositionSalary_max103() {
                return _$PositionSalary_max103;
            }

            public void set_$PositionSalary_max103(List<String> _$PositionSalary_max103) {
                this._$PositionSalary_max103 = _$PositionSalary_max103;
            }
        }
    }
}
