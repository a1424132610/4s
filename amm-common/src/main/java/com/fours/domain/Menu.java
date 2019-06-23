package com.fours.domain;

import com.fours.util.EasyuiColumn;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private Long id;
    @EasyuiColumn(title = "菜单编号")
    private String sn;
    @EasyuiColumn(title = "菜单名称")
    private String name;

    @EasyuiColumn(title = "上级菜单")
    private Integer parentId;
    @EasyuiColumn(title = "图标")
    private String icon;
    @EasyuiColumn(title = "地址")
    private String url;
    @EasyuiColumn(title = "intro")
    private String intro;

    private List<Menu> children=new ArrayList<>();

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    //easyui的树需要一个text属性
    public String getText(){
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", intro='" + intro + '\'' +
                ", children=" + children +
                '}';
    }
}