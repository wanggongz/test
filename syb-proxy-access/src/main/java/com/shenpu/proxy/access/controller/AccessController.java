package com.shenpu.proxy.access.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shenpu.proxy.access.dto.DataInfo;
import com.shenpu.proxy.access.mq.PrintRabbitMqSender;
import com.shenpu.proxy.access.service.PolicyService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class AccessController {

	@Autowired
	private PolicyService policyService;
	
	@Autowired PrintRabbitMqSender printRabbitMqSender;
	
	private static final Logger logger = LogManager.getLogger(AccessController.class);
	
	@ApiOperation(value="承包",notes="承包")
	@ApiImplicitParam(name="applyNo",value="流水号",dataType="String",required=true)
	@RequestMapping(value="/access", method = RequestMethod.POST)
	public DataInfo access(@RequestBody String applyNo) {
		DataInfo info = new DataInfo();
		try {
			logger.info(">>>>>>>>>>>>>进入承保");
			info = policyService.modifyByPrimaryKeySelective(applyNo);
		} catch (Exception e) {
			logger.error("流水号"+applyNo+"承包异常:{}",e);
			info.setSuccess(false);
			info.setMsg("流水号"+applyNo+"承包异常:"+e);
		}
		return info;
	}
	
	
	
	
	/**
	 * 发送出单消息
	 * @param applyNos
	 * @return
	 */
	@ApiIgnore
	@RequestMapping(value="/sendPrint", method = RequestMethod.POST)
	public boolean sendPrint(@RequestBody List<String> applyNos) {
		boolean b = true ;
		try {
			for(String applyNo: applyNos){
				printRabbitMqSender.send(applyNo);
			}
		} catch (Exception e) {
			b = false;
			String applyNo="";
			for(String s: applyNos){
				applyNo+=s+"、";
			}
			logger.error("流水号（"+applyNo+"）发送出单消息（异常补偿）异常:{}"+e);
		}
		return b;
	}
}
