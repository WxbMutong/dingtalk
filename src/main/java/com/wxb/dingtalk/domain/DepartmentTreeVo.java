package com.wxb.dingtalk.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: dingtalk
 * @description: 部门递归实现类
 * @author: 木同
 * @create: 2019-12-28 21:31
 **/
public class DepartmentTreeVo {

    private Integer departmentId;

    private Integer parentId;

    private String departmentName;

    private List<DepartmentTreeVo> childs = new ArrayList<>();

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
        this.departmentName = departmentName;
    }

    public List<DepartmentTreeVo> getChilds() {
        return childs;
    }

    public void setChilds(List<DepartmentTreeVo> childs) {
        this.childs = childs;
    }
}
