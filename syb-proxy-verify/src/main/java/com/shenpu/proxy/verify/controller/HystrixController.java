package com.shenpu.proxy.verify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shenpu.proxy.verify.client.BlacklistClient;

@RestController
public class HystrixController {
	
	@Autowired
	private BlacklistClient blacklistClient;
	
	@Autowired
	private Environment env;
	
	/**
	 * 测试hystrix
	 */
	@RequestMapping("/get")
	@HystrixCommand(fallbackMethod="findByIdFallback")
	public String testHystrix1(String message){
		
		RelaxedPropertyResolver relaxedPropertyResolver = new RelaxedPropertyResolver(env,"server.");
		String property = relaxedPropertyResolver.getProperty("port");
		System.out.println("当前端口号："+Integer.valueOf(property));
		
		if(Integer.valueOf(property)%2==0){
			System.out.println("异常服务----------------");
			throw new RuntimeException("异常-----");
		}else{
			System.out.println("正常服务----------------");
		}
		return property;
	}
	
	
	public String findByIdFallback(String message){
		return "调用失败，调用findByIdFallback~~~~~";
	}
	
}
