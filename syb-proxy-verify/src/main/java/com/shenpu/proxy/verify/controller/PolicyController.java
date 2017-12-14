package com.shenpu.proxy.verify.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shenpu.proxy.verify.service.PolicyService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/policy")
@ApiIgnore
public class PolicyController {

	@Autowired
	private PolicyService policyService;
	
	private static final Logger logger = Logger.getLogger(PolicyController.class);
	
	/**
	 * 根据流水号查询投保信息
	 * @param applyNo
	 * @return
	 */
	@ApiImplicitParam(name="applyNo",value="流水号",required=true,dataType="String")
	@ApiOperation(value="查询投保信息",notes="根据流水号查询投保信息")
	@RequestMapping(value = "/queryAllByApplyNo",method = RequestMethod.POST)
	public Map<String, Object> queryAllByApplyNo(@RequestParam String applyNo){
		
		Map<String, Object> map = null;
		try {
			map = policyService.queryByApplyNo(applyNo);
		} catch (Exception e) {
			logger.error("根据"+applyNo+"查询,保单及投保人等信息异常：{}",e);
		}
		return map;
	}
	
	/**
	 * 异常补偿
	 * @return
	 */
	@ApiOperation(value="异常补偿",notes="获取已经核保成功并且已支付但是未承包信息，修改其信息为承包成功并且出单")
	@RequestMapping(value = "/updateException",method = RequestMethod.POST)
	public boolean updateException(){
		boolean b = true;
		try {
			policyService.updateException();
		} catch (Exception e) {
			b = false;
			logger.error("执行异常补偿信息异常：{}",e);
		}
		return b;
	}
	
	
}
