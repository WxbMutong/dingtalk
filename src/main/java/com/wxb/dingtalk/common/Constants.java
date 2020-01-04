package com.wxb.dingtalk.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Constants {

    @Autowired
    private EnvProtites env;

    //获取accessToken
    public static String QUERY_ACCESSTOKEN;
    //获取部门列表
    public static String DEPARTMENT_LIST;
    //获取部门详情
    public static String DEPARTMENT_DETAIL;
    //获取部门用户列表
    public static String DEPART_MEMBER;
    //获取部门用户信息
    public static String USER_SIMPLELIST;
    //获取部门用户详细信息
    public static String DEPART_USER_DETAIL;
    //获取用户打卡结果
    public static String USER_ATTENDANCE_RESULT;

    @PostConstruct
    public void init(){
        QUERY_ACCESSTOKEN = env.getHost() + "gettoken";
        DEPARTMENT_LIST = env.getHost() + "department/list";
        DEPARTMENT_DETAIL = env.getHost() + "department/get";
        DEPART_MEMBER = env.getHost() + "user/getDeptMember";
        USER_SIMPLELIST = env.getHost() + "user/simplelist";
        DEPART_USER_DETAIL = env.getHost() + "user/listbypage";
        USER_ATTENDANCE_RESULT = env.getHost() + "attendance/list";
    }

}
