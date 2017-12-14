package com.shenpu.proxy.verify.service;

import java.util.List;

import com.shenpu.proxy.verify.domain.Bene;

public interface BeneService {
	
	int insert(Bene record);

    int insertSelective(Bene record);
    
    void insertBatch(List<Bene> list);
    
    Bene queryByApplyNo(String applyNo);
}
