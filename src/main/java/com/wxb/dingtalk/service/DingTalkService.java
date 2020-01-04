package com.wxb.dingtalk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiUserListbypageResponse;
import com.taobao.api.ApiException;
import com.wxb.dingtalk.common.Result;
import com.wxb.dingtalk.domain.DepartmentTreeVo;
import com.wxb.dingtalk.entity.DepartmentInfo;
import com.wxb.dingtalk.mapper.DepartmentInfoMapper;
import com.wxb.dingtalk.mapper.UserInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: dingtalk
 * @description: 获取钉钉相关数据库信息
 * @author: 木同
 * @create: 2019-12-27 17:45
 **/
@Service
public class DingTalkService {

    @Autowired
    private DingTalkApi dingTalkApi;

    @Autowired
    private DepartmentInfoMapper departmentInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /** 
     * @Description: 保存所有用户详情 
     * @Param:  
     * @return:  
     * @Author: 木同 
     * @Date: 2019/12/31 
     */
    public Result saveUserInfo() throws ApiException {
        List<OapiUserListbypageResponse.Userlist> users = new ArrayList<>();
        List<DepartmentTreeVo> infos = departmentInfoMapper.queryAll();
        for (DepartmentTreeVo depart:infos) {
            Result result = dingTalkApi.queryDepartUserDetail(depart.getDepartmentId(), 0L, 100L);
            if ( result.getStatus() == 200 ){
                JSONObject parseObject = JSONObject.parseObject(result.getData().toString());
                if ( null != parseObject && parseObject.getString("errcode").equals("0") ){
                    List<OapiUserListbypageResponse.Userlist> userlist = JSONArray.parseArray(parseObject.getString("userlist"), OapiUserListbypageResponse.Userlist.class);
                    if (!CollectionUtils.isEmpty(userlist)){
                        users.addAll(userlist);
                    }
                }
            }
        }
        if (!CollectionUtils.isEmpty(users)){
            users.stream().filter(x ->{
                x.setDepartment(x.getDepartment().replace("[","").replace("]",""));
                return true;
            }).collect(Collectors.toList());
            try{
                userInfoMapper.insertUsers(users);
            }catch (Exception e){
                System.out.println(e);
                return new Result(users);
            }
        }
        return Result.ok();
    }

    /**
     * @Description: 获取部门树形结构
     * @Author: 木同
     * @Date: 2019/12/30
     */
    public Result queryDepartmentByTree(String parentId){
        List<DepartmentTreeVo> infos = departmentInfoMapper.queryAll();
        List<DepartmentTreeVo> treeVos = makeTree(infos, Integer.parseInt(parentId));
        return new Result(treeVos);
    }

    /**
     * @Description: 递归部门
     * @Author: 木同
     * @Date: 2019/12/28
     */
    public List<DepartmentTreeVo> makeTree(List<DepartmentTreeVo> treeVo, Integer parentId){
        List<DepartmentTreeVo> parents = treeVo.stream().filter(x -> String.valueOf(x.getParentId()).equals(String.valueOf(parentId))).collect(Collectors.toList());
        List<DepartmentTreeVo> childs = treeVo.stream().filter(x -> !String.valueOf(x.getParentId()).equals(String.valueOf(parentId))).collect(Collectors.toList());

        parents.forEach(x -> {
            makeTree( childs,x.getDepartmentId() ).forEach( y -> x.getChilds().add(y) );
        });

        return parents;
    }

}
