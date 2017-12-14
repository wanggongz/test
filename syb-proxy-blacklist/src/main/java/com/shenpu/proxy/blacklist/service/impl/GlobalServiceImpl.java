package com.shenpu.proxy.blacklist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenpu.proxy.blacklist.service.AddressDictionaryService;
import com.shenpu.proxy.blacklist.service.BlacklistService;
import com.shenpu.proxy.blacklist.service.DictionaryService;
import com.shenpu.proxy.blacklist.service.GlobalService;
import com.shenpu.proxy.blacklist.service.OccupationDictionaryService;

@Service
public class GlobalServiceImpl implements GlobalService{

	@Autowired
	private OccupationDictionaryService occupationDictionaryService;
	
	@Autowired
	private AddressDictionaryService addressDictionaryService;
	
	@Autowired
	private BlacklistService blacklistService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Override
	@Transactional
	public void saveAllToES() {
		occupationDictionaryService.saveAll();
		addressDictionaryService.saveAll();
		blacklistService.saveAll();
		dictionaryService.saveAll();
	}
}
