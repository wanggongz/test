package com.shenpu.proxy.blacklist.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.shenpu.proxy.blacklist.domain.Blacklist;
import com.shenpu.proxy.blacklist.repository.es.BlacklistEsRepository;

public interface BlacklistRepository extends ElasticsearchRepository<Blacklist, String> ,BlacklistEsRepository{

	
}
