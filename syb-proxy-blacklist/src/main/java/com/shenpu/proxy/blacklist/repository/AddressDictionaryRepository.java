package com.shenpu.proxy.blacklist.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.shenpu.proxy.blacklist.domain.AddressDictionary;
import com.shenpu.proxy.blacklist.repository.es.AddressDictionaryEsRepository;

public interface AddressDictionaryRepository extends ElasticsearchRepository<AddressDictionary, Long> ,AddressDictionaryEsRepository{
	
}
