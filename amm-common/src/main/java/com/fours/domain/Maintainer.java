package com.fours.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fours.util.EasyuiColumn;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Maintainer {
    private Long optid;

    @EasyuiColumn(title = "维修员姓名")
    private String optName;
    @EasyuiColumn(title = "维修员年龄")
    private Integer age;
    @EasyuiColumn(title = "维修员邮箱")
    private String email;
    @EasyuiColumn(title = "雇佣的日期")
    private Date hireDate = new Date();

    public Long getOptid() {
        return optid;
    }

    public void setOptid(Long optid) {
        this.optid = optid;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getHireDate() {
        return hireDate;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}