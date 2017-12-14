package com.shenpu.proxy.blacklist.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.shenpu.proxy.blacklist.domain.OccupationDictionary;
import com.shenpu.proxy.blacklist.repository.es.OccupationDictionaryEsRepository;

public interface OccupationDictionaryRepository extends ElasticsearchRepository<OccupationDictionary, String> , OccupationDictionaryEsRepository{

}
