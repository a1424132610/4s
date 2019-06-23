package com.fours.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fours.util.EasyuiColumn;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Repairorder {
    @EasyuiColumn(title = "维修工单号")
    private Long id;

    @EasyuiColumn(title = "客户名称")
    private String custormer;

    @EasyuiColumn(title = "车牌号")
    private String carnum;

    @EasyuiColumn(title = "创建时间")
    private Date createtime=new Date();
    @EasyuiColumn(title = "状态")
    private Boolean status = true;

    @EasyuiColumn(title = "维修人员")
    private Maintainer optid;

    @EasyuiColumn(title = "地址")
    private String address;

    private Boolean zt = true;

    public Boolean getZt() {
        return zt;
    }

    public void setZt(Boolean zt) {
        this.zt = zt;
    }

    List<Repairorderitem> items = new ArrayList<>();

    public List<Repairorderitem> getItems() {
        return items;
    }

    public void setItems(List<Repairorderitem> items) {
        this.items = items;
    }

    /*public List<Repairorderitem> getNnn() {
        return items;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustormer() {
        return custormer;
    }

    public void setCustormer(String custormer) {
        this.custormer = custormer;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreatetime() {
        return createtime;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Maintainer getOptid() {
        return optid;
    }

    public void setOptid(Maintainer optid) {
        this.optid = optid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Repairorder{" +
                "id=" + id +
                ", custormer='" + custormer + '\'' +
                ", carnum='" + carnum + '\'' +
                ", createtime=" + createtime +
                ", status=" + status +
                ", optid='" + optid + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}