package com.wxb.dingtalk.service;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import com.wxb.dingtalk.common.Constants;
import com.wxb.dingtalk.common.EnvProtites;
import com.wxb.dingtalk.common.GlobalVariable;
import com.wxb.dingtalk.common.Result;
import com.wxb.dingtalk.mapper.DepartmentInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: dingtalk
 * @description: 对接钉钉API接口
 * @author: 木同
 * @create: 2019-12-26 11:10
 **/
@Component
public class DingTalkApi {

    @Autowired
    private GlobalVariable globalVariable;
    @Autowired
    public EnvProtites envProtites;
    @Autowired
    private DepartmentInfoMapper departmentInfoMapper;

    /**
     * @Description: 获取用户打卡结果
     * @Param:
     * @return:
     * @Author: 木同
     * @Date: 2020/1/1
     */
    public Result queryAttendanceResult(List<String> userList,String workFromTime,String workToTime) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(Constants.USER_ATTENDANCE_RESULT);
        OapiAttendanceListRequest request = new OapiAttendanceListRequest();
        request.setWorkDateFrom(workFromTime);
        request.setWorkDateTo(workToTime);
        request.setUserIdList(userList);
        request.setOffset(0L);
        request.setLimit(20L);//最大50
        OapiAttendanceListResponse response = client.execute(request,queryToken());
        return new Result(response.getBody());
    }

    /**
     * @Description: 获取部门用户详情
     * @Author: 木同
     * @Date: 2020/1/1
     */
    public Result queryDepartUserDetail(long departId, long offset, long size) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(Constants.DEPART_USER_DETAIL);
        OapiUserListbypageRequest request = new OapiUserListbypageRequest();
        request.setDepartmentId(departId);
        request.setOffset(offset);
        request.setSize(size);
        request.setOrder("entry_desc");
        request.setHttpMethod("GET");
        OapiUserListbypageResponse execute = client.execute(request,queryToken());
        return new Result(execute.getBody());
    }

    /**
     * @Description: 获取部门的用户信息
     * @Param:  departId 部门id
     * @Author: 木同
     * @Date: 2019/12/30
     */
    public Result queryUserSimplelist(long departId) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(Constants.USER_SIMPLELIST);
        OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
        request.setDepartmentId(departId);
        //request.setOffset(0L); request.setSize(10L); 分页 最大100条页面
        request.setHttpMethod("GET");
        OapiUserSimplelistResponse response = client.execute(request, queryToken());
        System.out.println(response.getBody());
        return new Result( response.getUserlist() );
    }

    /**
     * @Description: 获取钉钉部门用户列表
     * @Param:  departId 部门Id
     * @Author: 木同
     * @Date: 2019/12/30
     */
    public Result queryDepartMember(String departId) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(Constants.DEPART_MEMBER);
        OapiUserGetDeptMemberRequest req = new OapiUserGetDeptMemberRequest();
        req.setDeptId(departId);
        req.setHttpMethod("GET");
        OapiUserGetDeptMemberResponse rsp = client.execute(req, queryToken());
        System.out.println(JSON.toJSONString(rsp.getBody()));
        return new Result(rsp.getUserIds());
    }

    /**
     * @Description: 获取部门详情
     * @Param:  departId：部门Id,1代表根部门
     * @Author: 木同
     * @Date: 2019/12/29
     */
    public Result departmentDetail(String departId) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(Constants.DEPARTMENT_DETAIL);
        OapiDepartmentGetRequest request = new OapiDepartmentGetRequest();
        request.setId(departId);
        request.setHttpMethod("GET");
        OapiDepartmentGetResponse response = client.execute(request, queryToken());
        System.out.println("钉钉部门详情：" + JSON.toJSONString(response));
        return new Result(response.getOuterPermitUsers());
    }

    /**
     * @Description: 获取部门信息
     * @Param:  departId：部门Id,1代表根部门
     * @Author: 木同
     * @Date: 2019/12/26
     */
    public Result departmentList(String departId) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(Constants.DEPARTMENT_LIST);
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setId(departId);
        request.setHttpMethod("GET");
        OapiDepartmentListResponse response = client.execute(request, queryToken());
        if ( response.getErrcode() == 0 ){
            List<OapiDepartmentListResponse.Department> department = response.getDepartment();
            departmentInfoMapper.insertDeparts(department);
        }
        return new Result(response.getDepartment());
    }

    /**
     * @Description: 获取钉钉token
     * @Param:
     * @return:
     * @Author: 木同
     * @Date: 2019/12/29
     */
    public String queryToken() throws ApiException {
        String accessToken = globalVariable.getAccessToken();
        if (StringUtils.isNotBlank(accessToken)){
            return accessToken;
        }
        DefaultDingTalkClient client = new DefaultDingTalkClient(Constants.QUERY_ACCESSTOKEN);
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(envProtites.getAppKey());
        request.setAppsecret(envProtites.getAppSecrect());
        request.setHttpMethod("GET");
        OapiGettokenResponse response = client.execute(request);
        globalVariable.setAccessToken(response.getAccessToken());
        return response.getAccessToken();
    }

}
