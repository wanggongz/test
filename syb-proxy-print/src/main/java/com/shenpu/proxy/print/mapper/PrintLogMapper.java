package com.shenpu.proxy.print.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.shenpu.proxy.print.domain.PrintLog;

@Mapper
public interface PrintLogMapper {
    int insert(PrintLog record);

    int insertSelective(PrintLog record);
}