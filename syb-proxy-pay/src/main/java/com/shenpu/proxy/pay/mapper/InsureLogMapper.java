package com.shenpu.proxy.pay.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.shenpu.proxy.pay.domain.InsureLog;
@Mapper
public interface InsureLogMapper {
    int deleteByPrimaryKey(String insureLogId);

    int insert(InsureLog record);

    int insertSelective(InsureLog record);

    InsureLog selectByPrimaryKey(String insureLogId);

    int updateByPrimaryKeySelective(InsureLog record);

    int updateByPrimaryKeyWithBLOBs(InsureLog record);

    int updateByPrimaryKey(InsureLog record);
}