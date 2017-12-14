package com.shenpu.proxy.verify.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("access-server")
public interface AccessClient {

	@RequestMapping(value = "/sendPrint",method = RequestMethod.POST)
	boolean sendPrint(List<String> list);
	
}
