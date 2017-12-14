package com.shenpu.proxy.blacklist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shenpu.proxy.blacklist.domain.OccupationDictionary;
@Mapper
public interface OccupationDictionaryMapper {
	List<OccupationDictionary> selectByAll();
	
    int deleteByPrimaryKey(String occupationId);

    int insert(OccupationDictionary record);

    int insertSelective(OccupationDictionary record);

    OccupationDictionary selectByPrimaryKey(String occupationId);

    int updateByPrimaryKeySelective(OccupationDictionary record);

    int updateByPrimaryKey(OccupationDictionary record);
}