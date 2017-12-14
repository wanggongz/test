package com.shenpu.proxy.access.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.shenpu.proxy.access.domain.Appnt;

@Mapper
public interface AppntMapper {
    int insert(Appnt record);

    int insertSelective(Appnt record);
}