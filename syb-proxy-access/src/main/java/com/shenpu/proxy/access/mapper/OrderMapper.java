package com.shenpu.proxy.access.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.shenpu.proxy.access.domain.Order;

@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);
    
    Order selectByApplyNo(String applyNo);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}