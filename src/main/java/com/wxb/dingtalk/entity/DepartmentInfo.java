package com.wxb.dingtalk.entity;

public class DepartmentInfo {
    private Integer id;

    private Integer departmentId;

    private Integer parentId;

    private String departmentName;

    private Object childs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public Object getChilds() {
        return childs;
    }

    public void setChilds(Object childs) {
        this.childs = childs;
    }
}