package com.wxb.dingtalk.mapper;

import com.dingtalk.api.response.OapiUserListbypageResponse;
import com.wxb.dingtalk.entity.UserInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int insertUsers(List<OapiUserListbypageResponse.Userlist> users);
}