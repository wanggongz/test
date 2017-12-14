package com.shenpu.proxy.blacklist.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shenpu.proxy.blacklist.domain.Dictionary;
import com.shenpu.proxy.blacklist.service.DictionaryService;

@RestController
@RequestMapping(value = "dictionary")
public class DictionaryController {
	
	private final Logger logger = LogManager.getLogger(DictionaryController.class.getName());

	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping(value = "/queryByCodeTypeAndCode", method = RequestMethod.GET)
	public Dictionary queryByTypeAndCode(@RequestParam String codeType, @RequestParam String code) {
		logger.info("根据编号类型和编号查询>>>>>>>>>>>>");
		Dictionary dictionary = dictionaryService.findByCodeTypeAndCode(codeType, code);
		return dictionary;
	}
	
	@RequestMapping(value = "/savaAll", method = RequestMethod.GET)
	public Boolean savaAll() {
		dictionaryService.saveAll();
		return false;
	}
}
