package com.fours.domain;

import com.fours.util.EasyuiColumn;

import java.math.BigDecimal;
import java.util.List;

public class Repairorderitem {
    private Long id;

    @EasyuiColumn(title = "维修工单号")
    private String opid;

    @EasyuiColumn(title = "维修员")
    private Maintainer mainid;

    @EasyuiColumn(title = "维修配件")
    private Parts pid;

    @EasyuiColumn(title = "配件价格")
    private BigDecimal amt1;

    @EasyuiColumn(title = "工时费")
    private Long amt2;

    @EasyuiColumn(title = "配件数量")
    private Integer num;

    @EasyuiColumn(title = "总金额")
    private Long totalamt;


    public Repairorderitem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpid() {
        return opid;
    }

    public void setOpid(String opid) {
        this.opid = opid;
    }

    public Maintainer getMainid() {
        return mainid;
    }

    public void setMainid(Maintainer mainid) {
        this.mainid = mainid;
    }

    public Parts getPid() {
        return pid;
    }

    public void setPid(Parts pid) {
        this.pid = pid;
    }

    public BigDecimal getAmt1() {
        return amt1;
    }

    public void setAmt1(BigDecimal amt1) {
        this.amt1 = amt1;
    }

    public Long getAmt2() {
        return amt2;
    }

    public void setAmt2(Long amt2) {
        this.amt2 = amt2;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getTotalamt() {
        return totalamt;
    }

    public void setTotalamt(Long totalamt) {
        this.totalamt = totalamt;
    }
}