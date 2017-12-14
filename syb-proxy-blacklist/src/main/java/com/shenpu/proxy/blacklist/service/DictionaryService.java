package com.shenpu.proxy.blacklist.service;

import com.shenpu.proxy.blacklist.domain.Dictionary;

public interface DictionaryService {

	Dictionary findByCodeTypeAndCode(String codeType, String code);
	
	void saveAll();
	
	
}
