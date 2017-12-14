package com.shenpu.proxy.pay.service;

import com.shenpu.proxy.pay.domain.Refund;

public interface RefundService {

	void insert(Refund refund);

	Refund queryById(String id);

}
