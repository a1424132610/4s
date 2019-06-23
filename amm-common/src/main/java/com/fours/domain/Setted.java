package com.fours.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fours.util.EasyuiColumn;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Setted {
    private Long id;
    @EasyuiColumn(title = "客户名称")
    private String custormerId;
    @EasyuiColumn(title = "结算时间")
    private Date settedTime =new Date();
    @EasyuiColumn(title = "应付金额")
    private BigDecimal amountId;
    @EasyuiColumn(title = "实付金额")
    private BigDecimal totalMoney;
    @EasyuiColumn(title = "支付方式")
    private Integer payId;
    @EasyuiColumn(title = "维修工单号")
    private Long repairorderId;
    @EasyuiColumn(title = "地址")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   /* public Integer getCustormerId() {
        return custormerId;
    }

    public void setCustormerId(Integer custormerId) {
        this.custormerId = custormerId;
    }*/

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setSettedTime(Date settedTime) {
        this.settedTime = settedTime;
    }

    public BigDecimal getAmountId() {
        return amountId;
    }

    public void setAmountId(BigDecimal amountId) {
        this.amountId = amountId;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public Long getRepairorderId() {
        return repairorderId;
    }

    public void setRepairorderId(Long repairorderId) {
        this.repairorderId = repairorderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}