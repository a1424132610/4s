package com.fours.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fours.util.EasyuiColumn;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Log {
    private Long id;

    @EasyuiColumn(title = "操作用户")
    private String opUser;

    @EasyuiColumn(title = "操作时间")
    private Date opTime=new Date();

    @EasyuiColumn(title = "登录IP")
    private String opIp;

    @EasyuiColumn(title = "使用功能")
    private String function;

    @EasyuiColumn(title = "操作参数信息")
    private String params;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getOpTime() {
        return opTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getOpIp() {
        return opIp;
    }

    public void setOpIp(String opIp) {
        this.opIp = opIp;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", opUser='" + opUser + '\'' +
                ", opTime=" + opTime +
                ", opIp='" + opIp + '\'' +
                ", function='" + function + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}