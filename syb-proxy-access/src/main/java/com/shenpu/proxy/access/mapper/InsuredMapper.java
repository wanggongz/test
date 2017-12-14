package com.shenpu.proxy.access.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.shenpu.proxy.access.domain.Insured;

@Mapper
public interface InsuredMapper {
    int insert(Insured record);

    int insertSelective(Insured record);
    
    Long selectSumPremiumByApplyNo(String applyNo);
}