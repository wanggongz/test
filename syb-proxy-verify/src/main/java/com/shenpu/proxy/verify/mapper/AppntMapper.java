package com.shenpu.proxy.verify.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shenpu.proxy.verify.domain.Appnt;

@Mapper
public interface AppntMapper {
    
	/**
     * 根据applyNo查询投保人
     * @param applyNo
     * @return
     */
	Appnt selectByApplyNo(@Param("applyNo") String applyNo);
	
    int insert(Appnt record);

    int insertSelective(Appnt record);

}