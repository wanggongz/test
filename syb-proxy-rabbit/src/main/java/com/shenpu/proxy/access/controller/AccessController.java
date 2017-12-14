package com.shenpu.proxy.access.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shenpu.proxy.access.mq.AccessRabbitMqSender;

import springfox.documentation.annotations.ApiIgnore;

@RestController
public class AccessController {

	@Autowired AccessRabbitMqSender printRabbitMqSender;
	
	/**
	 * 发送消息
	 * @param applyNos
	 */
	@ApiIgnore
	@RequestMapping(value="/sendPrint", method = RequestMethod.GET)
	public String sendPrint(@RequestParam String message) {
		try {
			printRabbitMqSender.send(message);
		} catch (Exception e) {
		}
		return "发送成功。";
	}
	
	/**
	 * 发送消息2
	 */
	@ApiIgnore
	@RequestMapping(value="/sendPrint2", method = RequestMethod.GET)
	public String sendPrint2(@RequestParam String message) {
		try {
			printRabbitMqSender.send2(message);
		} catch (Exception e) {
		}
		return "发送成功。";
	}
	
	/**
	 * 发送消息2
	 * 有exchange无routing
	 */
	@ApiIgnore
	@RequestMapping(value="/sendPrint3", method = RequestMethod.GET)
	public String sendPrint3(@RequestParam String message) {
		try {
			printRabbitMqSender.send3(message);
		} catch (Exception e) {
		}
		return "发送成功。";
	}
}
