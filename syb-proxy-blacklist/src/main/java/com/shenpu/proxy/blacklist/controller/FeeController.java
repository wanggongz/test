//package com.shenpu.proxy.blacklist.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import com.shenpu.proxy.blacklist.dto.FeeExample;
//import com.shenpu.proxy.verify.service.FeeService;
//
//@Controller
//@RequestMapping("/fee")
//public class FeeController {
//	@Autowired
//	private FeeService feeService;
//	
//	private final Logger logger = LoggerFactory.getLogger(FeeController.class);
//	
//	@RequestMapping(value = "/import.htm",method = RequestMethod.GET)
//	@ResponseBody
//	public String ImportExcel(String fileName){
//		
//		Integer i = feeService.importExcel(fileName);
//		return "成功导入"+i+"条数据";
//	}
//	
//	
//	
//	/**
//	 * 根据条件查询保费
//	 */
//	@RequestMapping(value = "/getInsureFee",method = RequestMethod.GET)
//	@ResponseBody
//	public Float getInsureFee(FeeExample feeExample){
//		logger.info("查询保费开始==============");
//		Float f = 0f;
//		try {
//			f = feeService.getInsureFee(feeExample);
//		} catch (Exception e) {
//			logger.error("查询保费开始异常！"+e);
//			e.printStackTrace();
//		}
//		return f;
//	}
//
//}
