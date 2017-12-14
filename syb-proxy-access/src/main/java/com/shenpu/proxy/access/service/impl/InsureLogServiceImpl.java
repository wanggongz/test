package com.shenpu.proxy.access.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenpu.proxy.access.domain.InsureLog;
import com.shenpu.proxy.access.mapper.InsureLogMapper;
import com.shenpu.proxy.access.service.InsureLogService;

@Service("insureLogService")
public class InsureLogServiceImpl implements InsureLogService {
	
	@Autowired
	private InsureLogMapper insureLogMapper;
	
	@Override
	@Transactional
	public void save(InsureLog insureLog) {
		insureLogMapper.insert(insureLog);
	}

}
