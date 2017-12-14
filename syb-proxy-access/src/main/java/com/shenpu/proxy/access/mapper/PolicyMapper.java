package com.shenpu.proxy.access.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.shenpu.proxy.access.domain.Policy;

@Mapper
public interface PolicyMapper {
    int deleteByPrimaryKey(String policyId);

    int insert(Policy record);

    int insertSelective(Policy record);
    
    Policy selectByApplyNo(String applyNo);

    Policy selectByPrimaryKey(String policyId);

    int updateByPrimaryKeySelective(Policy record);

    int updateByPrimaryKey(Policy record);
}