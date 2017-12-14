package com.shenpu.proxy.blacklist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shenpu.proxy.blacklist.domain.AddressDictionary;
import com.shenpu.proxy.blacklist.service.AddressDictionaryService;
import com.shenpu.proxy.blacklist.service.GlobalService;

@Controller
@RequestMapping("/address")
public class AddressDictionaryController {
	
	@Autowired
	private AddressDictionaryService addressDictionaryService;
	
	@Autowired
	private GlobalService globalService;
	
	@RequestMapping("/queryLowers")
	@ResponseBody
	public List<AddressDictionary> queryListByUpPlaceCode(String code){
		List<AddressDictionary> queryListByUpPlaceCode = addressDictionaryService.queryListByUpPlaceCode(code);
		return queryListByUpPlaceCode;
	}
	
	@RequestMapping("/queryHighest")
	@ResponseBody
	public List<AddressDictionary> queryList(){
		return addressDictionaryService.queryListNoUpPlaceCode();
	}
	
	/**
	 * 保存数据到es中
	 * @return
	 */
	@RequestMapping("/saveAll")
	@ResponseBody
	public boolean saveAll(){
		System.out.println("start");
//		addressDictionaryService.saveAll();
		try {
			globalService.saveAllToES();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("over");
		return true;
	}
	
	/**
	 * 根据code查询
	 */
	@RequestMapping(value = "/queryByPlaceCode",method= RequestMethod.GET)
	@ResponseBody
	public boolean queryByCode(@RequestParam String placeCode){
		return addressDictionaryService.queryByPlaceCode(placeCode);
	
	}
	
	/**
	 * 根据code和upcode查询
	 */
	@RequestMapping(value = "/queryByPlaceCodeAndUpPlaceCode",method= RequestMethod.GET)
	@ResponseBody
	public boolean queryByCode(@RequestParam String placeCode,@RequestParam String upPlaceCode){
		
		return addressDictionaryService.queryByPlaceCodeAndUpCode(placeCode,upPlaceCode);
	
	}
	
}
