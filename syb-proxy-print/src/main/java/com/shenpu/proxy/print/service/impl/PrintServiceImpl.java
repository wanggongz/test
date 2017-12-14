package com.shenpu.proxy.print.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenpu.proxy.print.domain.PrintLog;
import com.shenpu.proxy.print.mapper.PrintLogMapper;
import com.shenpu.proxy.print.service.PrintLogService;

@Service
public class PrintServiceImpl implements PrintLogService{

	@Autowired PrintLogMapper printLogMapper;
	
	@Override
	public int addPrintLog(PrintLog printLog) {
		return printLogMapper.insertSelective(printLog);
	}
}
