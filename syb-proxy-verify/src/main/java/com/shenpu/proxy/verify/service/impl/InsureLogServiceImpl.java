package com.shenpu.proxy.verify.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenpu.proxy.verify.domain.InsureLog;
import com.shenpu.proxy.verify.mapper.InsureLogMapper;
import com.shenpu.proxy.verify.service.InsureLogService;

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
