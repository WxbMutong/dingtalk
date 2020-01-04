package com.wxb.dingtalk.mapper;

import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.wxb.dingtalk.domain.DepartmentTreeVo;
import com.wxb.dingtalk.entity.DepartmentInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DepartmentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DepartmentInfo record);

    int insertSelective(DepartmentInfo record);

    DepartmentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DepartmentInfo record);

    int updateByPrimaryKey(DepartmentInfo record);

    int insertDeparts(List<OapiDepartmentListResponse.Department> list);

    List<DepartmentTreeVo> queryAll();
}