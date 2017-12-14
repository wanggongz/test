package com.shenpu.proxy.blacklist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenpu.proxy.blacklist.domain.AddressDictionary;
import com.shenpu.proxy.blacklist.mapper.AddressDictionaryMapper;
import com.shenpu.proxy.blacklist.repository.AddressDictionaryRepository;

@Service("addressDictionaryService")
public class AddressDictionaryService {
	@Autowired
	private AddressDictionaryMapper addressDictionaryMapper;
	
	@Autowired
	private AddressDictionaryRepository addressDictionaryRepository;
	
	
	public List<AddressDictionary> queryListByUpPlaceCode(String code){
		return addressDictionaryMapper.selectListByUpPlaceCode(code);
	}
	
	public List<AddressDictionary> queryListNoUpPlaceCode(){
		return addressDictionaryMapper.selectListNoUpPlaceCode();
	}
	
	public void saveAll(){
		long count = addressDictionaryRepository.count();
		System.out.println(count);
		if (count==0) {
			List<AddressDictionary> list = addressDictionaryMapper.selectAll();
			for(AddressDictionary add: list){
				addressDictionaryRepository.save(add);
			}
		}
	}

	public boolean queryByPlaceCode(String placeCode) {
		AddressDictionary queryByPlaceCode = addressDictionaryRepository.queryByPlaceCode(placeCode);
		return queryByPlaceCode==null?false:true;
	}
	
	public boolean queryByPlaceCodeAndUpCode(String placeCode,String upPlaceCode) {
		AddressDictionary addressDictionary = addressDictionaryRepository.queryByPlaceCodeAndUpPlaceCode(placeCode, upPlaceCode);
		return addressDictionary==null?false:true;
	}
	
}
