package com.shenpu.proxy.blacklist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shenpu.proxy.blacklist.domain.Blacklist;

@Mapper
public interface BlacklistMapper {
    
	List<Blacklist> selectAll();
	
    int insert(Blacklist record);

    int insertSelective(Blacklist record);

}