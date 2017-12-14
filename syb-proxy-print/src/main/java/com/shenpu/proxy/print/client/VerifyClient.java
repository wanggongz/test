package com.shenpu.proxy.print.client;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("verify-server")
public interface VerifyClient {

	@RequestMapping(value = "policy/queryAllByApplyNo", method = RequestMethod.POST)
	Map<String , Object> queryAllByApplyNo(@RequestParam(value = "applyNo") String applyNo);
	
}
