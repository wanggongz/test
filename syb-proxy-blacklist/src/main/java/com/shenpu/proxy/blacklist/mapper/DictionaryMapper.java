package com.shenpu.proxy.blacklist.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shenpu.proxy.blacklist.domain.Dictionary;

@Mapper
public interface DictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
    
    List<Dictionary> selectAllExample();
}