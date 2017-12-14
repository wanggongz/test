//package com.shenpu.proxy.blacklist.test;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.shenpu.proxy.blacklist.domain.Dictionary;
//import com.shenpu.proxy.blacklist.mapper.DictionaryMapper;
//import com.shenpu.proxy.blacklist.repository.DictionaryRepository;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class DictionaryTest {
//
//	@Autowired
//	private DictionaryMapper dictionaryMapper;
//	
//	@Autowired
//	private DictionaryRepository dictionaryRepository;
//	
//	@Test
//	public void Test1(){
////		selectAllExample();
////		selectByPrimaryKey();
//		List<Dictionary> findByCodeType = dictionaryRepository.findByCodeTypeAndCode("nativeplace","CHN");
//		
//		for(Dictionary d: findByCodeType){
//			System.out.println("----------"+d);
//		}
//		
//	}
//	
//	
//	
//	public void selectAllExample(){
//		List<Dictionary> selectAllExample = dictionaryMapper.selectAllExample();
//		System.out.println(selectAllExample.size());
//	}
//	
//	
//	public void selectByPrimaryKey(){
//		Dictionary selectByPrimaryKey = dictionaryMapper.selectByPrimaryKey(1l);
//		System.out.println(selectByPrimaryKey);
//	}
//	
//}
