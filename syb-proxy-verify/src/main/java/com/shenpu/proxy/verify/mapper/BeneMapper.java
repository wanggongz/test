package com.shenpu.proxy.verify.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shenpu.proxy.verify.domain.Bene;

@Mapper
public interface BeneMapper {
    
	/**
     * 批量添加
     * @param list
     */
	void insertBatch(@Param(value = "list")List<Bene> list);
	
	/**
	 * 根据applyno查询
	 * @param applyNo
	 * @return
	 */
	List<Bene> selectByApplyNo(@Param("applyNo") String applyNo);
	
    int insert(Bene record);

    int insertSelective(Bene record);

}