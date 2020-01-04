package com.wxb.dingtalk.controller;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.taobao.api.ApiException;
import com.wxb.dingtalk.common.EnvProtites;
import com.wxb.dingtalk.common.Result;
import com.wxb.dingtalk.enums.ResultCode;
import com.wxb.dingtalk.service.DingTalkApi;
import com.wxb.dingtalk.service.DingTalkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: dingtalk
 * @description: 获取钉钉API信息入口
 * @author: 木同
 * @create: 2019-12-26 13:48
 **/
@RestController
@RequestMapping(value = "/dingtalk/api/")
public class DingTalkController {

    @Autowired
    private EnvProtites envProtites;
    @Autowired
    private DingTalkApi dingTalkApi;
    @Autowired
    private DingTalkService dingTalkService;

    /**
     * @Description: 保存钉钉所有用户
     * @Author: 木同
     * @Date: 2019/12/30
     */
    @GetMapping(value = "saveDingTalkUserInfo")
    public Result saveDingTalkUserInfo() throws ApiException {
        return dingTalkService.saveUserInfo();
    }

    /**
     * @Description: 获取钉钉部门用户详情列表
     * @Author: 木同
     * @Date: 2019/12/26
     */
    @GetMapping(value = "queryUserDetailList")
    public Result queryUserDetailList(@RequestParam(value = "departId") String departId) throws ApiException {
        if (StringUtils.isBlank(departId)){
            return new Result(ResultCode.PARAM_FAIL);
        }
        return dingTalkApi.queryDepartUserDetail(Long.valueOf(departId),0L,100L);
    }

    /**
     * @Description: 获取钉钉部门用户信息列表
     * @Author: 木同
     * @Date: 2019/12/26
     */
    @GetMapping(value = "queryUserSimpleList")
    public Result queryUserSimpleList(@RequestParam(value = "departId") String departId) throws ApiException {
        if (StringUtils.isBlank(departId)){
            return new Result(ResultCode.PARAM_FAIL);
        }
        return dingTalkApi.queryUserSimplelist(Long.valueOf(departId));
    }

    /**
     * @Description: 获取钉钉部门用户Id列表
     * @Author: 木同
     * @Date: 2019/12/26
     */
    @GetMapping(value = "queryDepartMemberList")
    public Result queryDepartMemberList(@RequestParam(value = "departId") String departId) throws ApiException {
        if (StringUtils.isBlank(departId)){
            return new Result(ResultCode.PARAM_FAIL);
        }
        return dingTalkApi.queryDepartMember(departId);
    }

    /**
     * @Description: 获取钉钉部门详情
     * @Author: 木同
     * @Date: 2019/12/26
     */
    @GetMapping(value = "queryDepartmentDetail")
    public Result queryDepartmentDetail(@RequestParam(value = "departId") String departId) throws ApiException {
        if (StringUtils.isBlank(departId)){
            return new Result(ResultCode.PARAM_FAIL);
        }
        return dingTalkApi.departmentDetail(departId);
    }

    /**
     * @Description: 获取部门信息返回树形
     * @Param:
     * @return:
     * @Author: 木同
     * @Date: 2019/12/28
     */
    @GetMapping(value = "queryDepartmentTree")
    public Result queryDepartmentTree(@RequestParam(value = "parentId") String parentId){
        if (StringUtils.isBlank(parentId)){
            return new Result(ResultCode.PARAM_FAIL);
        }
        return dingTalkService.queryDepartmentByTree(parentId);
    }

    /**
     * @Description: 获取钉钉部门信息并保存
     * @Author: 木同
     * @Date: 2019/12/26
     */
    @GetMapping(value = "queryDepartmentList")
    public Result queryDepartmentList(@RequestParam(value = "departId") String departId) throws ApiException {
        if (StringUtils.isBlank(departId)){
            return new Result(ResultCode.PARAM_FAIL);
        }
        return dingTalkApi.departmentList(departId);
    }

    @GetMapping(value = "/queryUserInfo")
    public String queryUserInfo(HttpServletRequest httpServletRequest) throws ApiException {
        String loginTmpCode = httpServletRequest.getParameter("loginTmpCode");
        DefaultDingTalkClient client = new DefaultDingTalkClient(envProtites.getHost() + "sns/getuserinfo_bycode");
        OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
        req.setTmpAuthCode(loginTmpCode);
        OapiSnsGetuserinfoBycodeResponse response = client.execute(req,envProtites.getAppId(),envProtites.getLoginAppSecrect());
        String result = JSONObject.toJSONString(response);
        String body = response.getBody();
        OapiSnsGetuserinfoBycodeResponse.UserInfo userInfo = response.getUserInfo();
        String userIn = JSONObject.toJSONString(userInfo);
        System.out.println(userIn+"\n"+body+"\n"+result);
        return userInfo.getOpenid();
    }
}
