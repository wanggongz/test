package com.shenpu.proxy.verify.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shenpu.proxy.verify.domain.Blacklist;
import com.shenpu.proxy.verify.domain.Dictionary;

@FeignClient("blacklist-server")
public interface BlacklistClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "dictionary/queryByCodeTypeAndCode")
	Dictionary queryByCodeTypeAndCode(@RequestParam(value = "codeType") String codeType, @RequestParam(value = "code") String code);
	
	/**
	 * 根据code查询地址对象
	 * @param codeType
	 * @param code
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "address/queryByPlaceCode")
	Boolean queryAddressByCode(@RequestParam(value = "placeCode") String placeCode);
	
	
	/**
	 * 根据code和upplacecode查询地址对象
	 * @param codeType
	 * @param code
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "address/queryByPlaceCodeAndUpPlaceCode")
	Boolean queryAddressByCodeAndUp(@RequestParam(value = "placeCode") String placeCode,@RequestParam(value="upPlaceCode")String upPlaceCode);
	
	
	/**
	 * 根据身份证查询黑名单
	 */
	@RequestMapping(method = RequestMethod.POST, value = "blacklist/queryByIdNo")
	Blacklist queryByIdNo(@RequestParam(value = "idNo") String idNo);
	
	
	
}
