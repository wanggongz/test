package com.shenpu.proxy.pay.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.shenpu.proxy.pay.domain.Order;

@Mapper
public interface OrderMapper {
	 int insert(Order order);

	 Order selectByPrimaryKey(String orderNo);

	 int updateOrderState(Order order);

}