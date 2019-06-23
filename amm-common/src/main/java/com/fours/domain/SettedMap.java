package com.fours.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SettedMap {
    private Long id;

    private String custormerId; //客户名称

    private Date settedTime = new Date();  //结算时间

    private Long totalMoney; //总金额

    private Integer payId; //支付方式

    private String address;  //地址

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustormerId() {
        return custormerId;
    }

    public void setCustormerId(String custormerId) {
        this.custormerId = custormerId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getSettedTime() {
        return settedTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setSettedTime(Date settedTime) {
        this.settedTime = settedTime;
    }


    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

