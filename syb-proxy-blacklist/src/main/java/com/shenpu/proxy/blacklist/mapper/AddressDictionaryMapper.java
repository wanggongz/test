package com.shenpu.proxy.blacklist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shenpu.proxy.blacklist.domain.AddressDictionary;
@Mapper
public interface AddressDictionaryMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(AddressDictionary record);
//
//    int insertSelective(AddressDictionary record);
//
//    AddressDictionary selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(AddressDictionary record);
//
//    int updateByPrimaryKey(AddressDictionary record);
    
	//查询所有1级地址
	List<AddressDictionary> selectListNoUpPlaceCode();
	
	//根据上级code查询所有下级对象
    List<AddressDictionary> selectListByUpPlaceCode(String code);
   
    //查询所有
    List<AddressDictionary> selectAll();
}