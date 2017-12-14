package com.shenpu.proxy.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shenpu.proxy.pay.domain.Order;
import com.shenpu.proxy.pay.mapper.OrderMapper;

/**
 * 
 * @desc 订单的service
 *
 * @author hcy
 *
 * 2016年9月14日
 */
@Service("orderService")
public class OrderService {
	
	@Autowired
	private OrderMapper orderDao;
	
	
	/**
	 * 根据订单号查询订单信息
	 * @param orderNo
	 * @return
	 */
	public Order queryOrderByOrderNo(String orderNo){
		return orderDao.selectByPrimaryKey(orderNo);
	}
	
	/**
	 * 保存订单信息
	 * @param orderNo
	 * @return
	 */
	public int saveOrder(Order orderNo){
		return orderDao.insert(orderNo);
	}

	/**
	 * 保存订单信息
	 * @param orderNo
	 * @return
	 */
	public Order queryOrderByOrderno(String orderNo){
		return orderDao.selectByPrimaryKey(orderNo);
	}

}
