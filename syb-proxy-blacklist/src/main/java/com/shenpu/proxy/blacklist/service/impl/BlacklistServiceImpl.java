package com.shenpu.proxy.blacklist.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenpu.proxy.blacklist.domain.Blacklist;
import com.shenpu.proxy.blacklist.mapper.BlacklistMapper;
import com.shenpu.proxy.blacklist.repository.BlacklistRepository;
import com.shenpu.proxy.blacklist.service.BlacklistService;

@Service
public class BlacklistServiceImpl implements BlacklistService{

	@Autowired
	private BlacklistRepository blacklistRepository;
	
	@Autowired
	private BlacklistMapper blacklistMapper;
	
	/**
	 * 存储数据到es
	 */
	@Override
	@Transactional
	public void saveAll() {
		long count = blacklistRepository.count();
		if(count==0){
			List<Blacklist> list = blacklistMapper.selectAll();
			for(Blacklist black: list){
				blacklistRepository.save(black);
			}
		}
	}
	
	
	public Blacklist queryByIdNo(String idNo){
		Blacklist blacklist = blacklistRepository.findByIdNo(idNo);
		return blacklist;
	}

}
