package com.shenpu.proxy.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenpu.proxy.pay.domain.Refund;
import com.shenpu.proxy.pay.mapper.RefundMapper;
import com.shenpu.proxy.pay.service.RefundService;
@Service
public class RefundServiceImpl implements RefundService{

	@Autowired
	private RefundMapper refundMapper;
	
	@Override
	public void insert(Refund refund) {
		refundMapper.insert(refund);
	}

	@Override
	public Refund queryById(String id) {
		return refundMapper.selectById(id);
		
	}

}
