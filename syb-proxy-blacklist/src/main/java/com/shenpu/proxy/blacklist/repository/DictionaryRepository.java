package com.shenpu.proxy.blacklist.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.shenpu.proxy.blacklist.domain.Dictionary;
import com.shenpu.proxy.blacklist.repository.es.DictionaryEsRepository;

public interface DictionaryRepository extends ElasticsearchRepository<Dictionary, Integer>, DictionaryEsRepository{
}
