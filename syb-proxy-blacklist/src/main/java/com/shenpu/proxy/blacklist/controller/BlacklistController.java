package com.shenpu.proxy.blacklist.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shenpu.proxy.blacklist.domain.Blacklist;
import com.shenpu.proxy.blacklist.service.BlacklistService;

@RestController
@RequestMapping("blacklist")
public class BlacklistController {

	@Autowired
	private BlacklistService blacklistService;
	
	private static final Logger logger = LogManager.getLogger(BlacklistController.class);
	
	@RequestMapping("/saveAll")
	public boolean saveAll(){
		try {
			blacklistService.saveAll();
			return true;
		} catch (Exception e) {
			logger.error("保存黑名单到es异常:{}"+e);
			return false;
		}
		
	}
	
	/**
	 * 根据身份证还查询
	 */
	@RequestMapping(value = "/queryByIdNo",method = RequestMethod.GET)
	public Blacklist queryByIdNo(@RequestParam String idNo){
		logger.info(">>>>>>>>>>开始根据身份证号:"+idNo+"查询黑名单");
		Blacklist blacklist = null;
		try {
			blacklist = blacklistService.queryByIdNo(idNo);
		} catch (Exception e) {
			logger.error("根据身份证号查询异常:{}"+e);;
		}
		logger.info("<<<<<<<<<<<<获取黑名单信息为:"+blacklist);
		return blacklist;
	}
	
}
