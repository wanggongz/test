package com.shenpu.proxy.blacklist.repository.es;

import java.util.List;

import com.shenpu.proxy.blacklist.domain.Dictionary;

public interface DictionaryEsRepository {

	Dictionary findByCodeTypeAndCode(String codeType, String code);
	
	List<Dictionary> findByCodeType(String codeType);
	
}
