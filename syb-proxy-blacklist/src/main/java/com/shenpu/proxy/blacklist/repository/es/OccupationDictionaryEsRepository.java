package com.shenpu.proxy.blacklist.repository.es;

import com.shenpu.proxy.blacklist.domain.OccupationDictionary;

public interface OccupationDictionaryEsRepository {
	
	OccupationDictionary queryById(String id);
	
}
