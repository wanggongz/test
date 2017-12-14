package com.shenpu.proxy.verify.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shenpu.proxy.verify.client.BlacklistClient;
import com.shenpu.proxy.verify.domain.Blacklist;

@RestController
public class ZipkinController {

	@Autowired
	private BlacklistClient blacklistClient;
	
	Logger logger = LoggerFactory.getLogger(ZipkinController.class);
	
	@RequestMapping("/zipkintest")
	@HystrixCommand(fallbackMethod="fallbackMethod")
	public String zipkinTest(){
		logger.info("zipkin-log..");
		Blacklist queryByIdNo = blacklistClient.queryByIdNo("12333");
		if(queryByIdNo==null){
			return "黑名单未命中...........";
		}else{
			return "黑名单命中...........";
		}
	}
	
	
	public String fallbackMethod(){
		return "hystrix.........";
	}
}
