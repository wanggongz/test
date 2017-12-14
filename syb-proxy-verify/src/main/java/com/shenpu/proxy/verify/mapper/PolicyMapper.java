package com.shenpu.proxy.verify.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shenpu.proxy.verify.domain.Policy;

@Mapper
public interface PolicyMapper {
    
	Policy selectByApplyNo(String applyNo);

    int insert(Policy record);

    int insertSelective(Policy record);

	void batchUpdate(@Param("list")List<Policy> list);

    List<Policy> selectByPolicy(@Param("policy") Policy policy);

}