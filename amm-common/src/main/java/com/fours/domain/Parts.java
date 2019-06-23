package com.fours.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Parts {
    //配件编号
    private Long id;

    //配件名称
    private String partsName;
    //单价
    private BigDecimal price;
    //数量
    private Integer num;
    //警告数量
    private Integer warnNum;
    //配件描述
    private String context;
    //配件入库时间
    private Date createTime = new Date();;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public Integer getWarnNum() {
        return warnNum;
    }

    public void setWarnNum(Integer warnNum) {
        this.warnNum = warnNum;
    }
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }




}