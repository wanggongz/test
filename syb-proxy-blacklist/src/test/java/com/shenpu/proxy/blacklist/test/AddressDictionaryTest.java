package com.shenpu.proxy.blacklist.test;

import java.util.Iterator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.shenpu.proxy.blacklist.domain.AddressDictionary;
import com.shenpu.proxy.blacklist.repository.AddressDictionaryRepository;
import com.shenpu.proxy.blacklist.service.AddressDictionaryService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddressDictionaryTest {
//
	@Autowired
	private AddressDictionaryRepository addressDictionaryService;
	
	@Test
	public void Test(){
		long count = addressDictionaryService.count();
		System.out.println(count);
//		Iterable<AddressDictionary> findAll = dictionaryRepository.findAll();
//		Iterator<AddressDictionary> iterator = findAll.iterator();
//		if(iterator.hasNext()){
//			System.out.println(iterator.next());
//		}
		
	}
	
}
