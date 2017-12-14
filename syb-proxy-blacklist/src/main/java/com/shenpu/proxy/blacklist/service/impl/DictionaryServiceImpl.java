package com.shenpu.proxy.blacklist.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenpu.proxy.blacklist.domain.Dictionary;
import com.shenpu.proxy.blacklist.mapper.DictionaryMapper;
import com.shenpu.proxy.blacklist.repository.DictionaryRepository;
import com.shenpu.proxy.blacklist.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService{
	
	/*这是mysql的dao*/
	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	/*这是ES的存储文件操作*/
	@Autowired
	private DictionaryRepository dictionaryRepository;
	
	
	@Override
	@Transactional
	public Dictionary findByCodeTypeAndCode(String codeType, String code) {
		Dictionary dictionary = dictionaryRepository.findByCodeTypeAndCode(codeType, code);
		return dictionary;
	}

	/*从mysql查出来后把这个变成文件存到ES*/
	@Override
	@Transactional
	public void saveAll() {
		long count = dictionaryRepository.count();
		if(count==0){
			List<Dictionary> dictionaries = dictionaryMapper.selectAllExample();
			for(Dictionary dictionary : dictionaries) {
				dictionaryRepository.save(dictionary);
			}
		}
	}
}
