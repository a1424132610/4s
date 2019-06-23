package com.fours.domain;

import com.fours.util.EasyuiColumn;

public class Permission {
    private Long id;

    @EasyuiColumn(title = "权限名称")
    private String name;

    @EasyuiColumn(title = "权限资源")
    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", resource=" + resource +
                '}';
    }
}