package com.shenpu.proxy.verify.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shenpu.proxy.verify.domain.Insured;

@Mapper
public interface InsuredMapper {
    
	
	/**
     * 批量查询
     * @param list
     */
	void insertBatch(@Param(value = "list") List<Insured> list);
	
	/**
	 * 根据applyNo查询
	 * @param applyNo
	 * @return
	 */
	List<Insured> selectByApplyNo(@Param("applyNo") String applyNo);
	
    int insert(Insured record);

    int insertSelective(Insured record);

}