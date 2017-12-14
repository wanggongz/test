package com.shenpu.proxy.blacklist.repository.es;

import com.shenpu.proxy.blacklist.domain.Blacklist;

public interface BlacklistEsRepository {
	
	public Blacklist findByIdNo(String idNo);
	
}
