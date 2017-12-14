package com.shenpu.proxy.blacklist.service;

import com.shenpu.proxy.blacklist.domain.Blacklist;

public interface BlacklistService {

	public void saveAll();
	
	public Blacklist queryByIdNo(String idNo);
}
