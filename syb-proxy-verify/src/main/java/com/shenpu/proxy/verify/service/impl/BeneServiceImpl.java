package com.shenpu.proxy.verify.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenpu.proxy.verify.domain.Bene;
import com.shenpu.proxy.verify.mapper.BeneMapper;
import com.shenpu.proxy.verify.service.BeneService;

@Service
public class BeneServiceImpl implements BeneService {
	
	@Autowired
	private BeneMapper beneMapper;
	
	@Transactional
	public int insert(Bene record){
		return beneMapper.insert(record);
	}
	
	@Transactional
	public Bene queryByApplyNo(String applyNo){
		return beneMapper.selectByApplyNo(applyNo).get(0);
	}
	
	@Transactional
	public int insertSelective(Bene record){
		return beneMapper.insertSelective(record);
    }
    
	@Transactional
	public void insertBatch(List<Bene> list){
		beneMapper.insertBatch(list);
    }
	
}
