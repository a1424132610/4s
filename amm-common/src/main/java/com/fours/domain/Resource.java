package com.fours.domain;

public class Resource {
    private Integer id;

    private String sn;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", sn='" + sn + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}