//package com.shenpu.proxy.pay.controller;
//
//import java.io.IOException;
//import java.util.SortedMap;
//import java.util.TreeMap;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.internal.util.AlipaySignature;
//import com.alipay.api.request.AlipayTradeWapPayRequest;
//import com.alipay.api.response.AlipayTradeWapPayResponse;
//import com.google.gson.Gson;
//import com.shenpu.base.utils.IpUtil;
//import com.shenpu.base.utils.RadomUtil;
//import com.shenpu.proxy.pay.domain.Order;
//import com.shenpu.proxy.pay.mq.AccessRabbitMqSender;
//import com.shenpu.proxy.pay.service.impl.OrderService;
//import com.shenpu.proxy.pay.utils.PayUtil;
//
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import springfox.documentation.annotations.ApiIgnore;
//
//@RestController  
//@RequestMapping(value="/antpay")  
//public class AntPayController {
//
//	private static final Logger logger = Logger.getLogger(AntPayController.class);
//
//	@Autowired
//	private OrderService orderService;
//
//	@Autowired
//	private AccessRabbitMqSender accessRabbitMqSender;
//	
//	
//	/**
//	 * 统一下单
//	 * @return
//	 * @throws IOException
//	 */
//	@ApiImplicitParams(value={
//			@ApiImplicitParam(name="total_amount",value="总保费",dataType="decimal(10,2)",required=true),
//			@ApiImplicitParam(name="apply_no",value="流水号",dataType="String",required=true)
//	})
//	@ApiOperation(value="统一下单",notes="提交支付，核查保费，返回执行结果")
//	@RequestMapping(value="/unifiedorder", method = RequestMethod.POST)
//	public String unifiedorder(
//			@RequestParam(value = "total_amount", required = true) String total_amount,
//			@RequestParam(value = "apply_no", required = true) String apply_no,
//			HttpServletRequest request, HttpServletResponse response) throws IOException {
//		try {
//			String spbill_create_ip = IpUtil.getIPLocation(request);
//			SortedMap<String, String> parametersMap = new TreeMap<>();
//			parametersMap.put("appid", "Settings.WX_APP_ID");
//			parametersMap.put("nonce_str", RadomUtil.get32BitUUID());
//			parametersMap.put("mch_id", "Settings.WX_MCH_ID");
//			parametersMap.put("notify_url", "Settings.WX_NOTIFY_URL");
//			parametersMap.put("trade_type", "Settings.WX_TRADE_TYPE");
//			parametersMap.put("total_amount", total_amount);
//			parametersMap.put("spbill_create_ip", spbill_create_ip);
//			//一、校验购买物品是否与应支付的金额一致,暂时不实现这层判断
//			//二、进行向支付宝网关进提交
//			String url = "https://openapi.alipay.com/gateway.do";
//			String sign = AlipaySignature.rsaSign(parametersMap, "Settings.ALI_APP_PRIVATE_KEY", "Settings.ALI_CHARSET") ;
//			parametersMap.put("sign", sign);
//			AlipayClient alipayClient = new DefaultAlipayClient(url,"Settings.ALI_APP_ID",
//					"Settings.ALI_APP_PRIVATE_KEY","json","Settings.ALI_CHARSET","Settings.ALI_PAY_PUBLIC_KEY");
//			//创建API对应的request
//		    AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
//		    //支付成功后的返回页面地址
//		    alipayRequest.setReturnUrl("Settings.ALI_RETURN_URL");
//		    //在公共参数中设置回跳和通知地址
//		    alipayRequest.setNotifyUrl("Settings.ALI_NOTIFY_URL");
//		    //填充业务参数
//		    Gson gson = new Gson();
//		    String reqMsg = gson.toJson(parametersMap);
//		    alipayRequest.setBizContent(reqMsg);
//		    //调用SDK生成表单
//		    AlipayTradeWapPayResponse rspMsg = alipayClient.pageExecute(alipayRequest);
//		    
//		    String form = rspMsg.getBody(); 
//		    response.setContentType("text/html;charset=" + "Settings.ALI_CHARSET");
//		    //判断下单是否成功
//		    String code = rspMsg.getCode();
//		    @SuppressWarnings("unused")
//			String status="0";
//		    if(code.equals("10000")){
//		    	System.out.println("接口调用成功");
//		    	status="1";
//		    }else if(code.equals("20000")){
//		    	System.out.println("服务不可用");
//		    }else if(code.equals("20001")){
//		    	System.out.println("授权权限不足");
//		    }else if(code.equals("40001")){
//		    	System.out.println("缺少必选参数");
//		    }else if(code.equals("40002")){
//		    	System.out.println("非法的参数");
//		    }else if(code.equals("40004")){
//		    	System.out.println("业务处理失败");
//		    }else if(code.equals("40002")){
//		    	System.out.println("40006");
//		    }
//		    //持久化数据
//		    Order order = new Order();
////			order.setOrderId(RadomUtil.get64BitUUID());
////			order.setApplyNo(apply_no);
////			order.setPayMoney(total_amount);
////			order.setPayState(status);
////			order.setPlatformCode("2");
////			order.setReqMsg(reqMsg);
////			order.setRspMsg(gson.toJson(rspMsg));
//			orderService.saveOrder(order);
//			logger.info("流水号："+apply_no+"，支付成功。");
//			//发送承包消息
//			accessRabbitMqSender.send(apply_no);
//			//直接将完整的表单html输出到页面
//		    return form;
//		} catch (Exception e) {
//			logger.error("统一下单失败:{}", e);
//			return null;
//		}
//	}
//	
//	/**
//	 * 获取支付宝回调网关数据
//	 * @return
//	 * @throws IOException
//	 */
//	@ApiIgnore
//	@RequestMapping(value="/notify", method = RequestMethod.POST)
//	public String notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String backXmlString = null;
//		try {
//			SortedMap<String, String> paramsMap = PayUtil.dealAntNotify2Map(request);
//			if (paramsMap == null || paramsMap.isEmpty()) {
//				backXmlString = "failure";
//			}else {
//				Boolean isBelived =  AlipaySignature.rsaCheckV1(paramsMap, "Settings.ALI_PAY_PUBLIC_KEY", "Settings.ALI_CHARSET"); 
//				if (!isBelived) {
//					backXmlString = "failure";
//				}else if("TRADE_SUCCESS".equals(paramsMap.get("trade_status"))){
////					SalesOrder salesOrder = salesOrderService.selectSalesOrderByOrderNo(paramsMap.get("out_order_no"));
////					//0、判读数据库是否有该订单
////					if (salesOrder == null) {
////						backXmlString = "failure";
////					//1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，	
////					}else if (!salesOrder.getOrderNo().equals(paramsMap.get("out_trade_no"))) {
////						backXmlString = "failure";
////					//2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
////					}else if (!salesOrder.getOrderMoney().equals(paramsMap.get("total_amount"))) {
////						backXmlString = "failure";
////					//3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
////					}else if (!salesOrder.getCnlUserCode().equals(paramsMap.get("seller_id"))) {
////						backXmlString = "failure";
////					//4、验证app_id是否为该商户本身
////					}else if (!Settings.ALI_APP_ID.equals(paramsMap.get("app_id"))){
////						backXmlString = "failure";
////					}else {
////						backXmlString = "success";
////					}
//				}else {
//					backXmlString = "failure";
//				}
//			}
//			return backXmlString;
//		} catch (Exception e) {
//			logger.error("处理支付宝支付回调数据失败:{}", e);
//			backXmlString = "failure";
//			return backXmlString;
//		} 
//	}
//}
