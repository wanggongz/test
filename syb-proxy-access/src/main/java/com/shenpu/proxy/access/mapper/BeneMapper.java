package com.shenpu.proxy.access.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.shenpu.proxy.access.domain.Bene;

@Mapper
public interface BeneMapper {
    int insert(Bene record);

    int insertSelective(Bene record);
}