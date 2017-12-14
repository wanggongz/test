package com.shenpu.proxy.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.shenpu.proxy.pay.domain.Order;
import com.shenpu.proxy.pay.domain.Pay;
import com.shenpu.proxy.pay.domain.WxPay;
import com.shenpu.proxy.pay.service.impl.OrderService;
import com.shenpu.proxy.pay.service.impl.PayService;
import com.shenpu.proxy.pay.service.impl.WxPayService;
import com.shenpu.proxy.pay.utils.CustomerStringUtils;
import com.shenpu.proxy.pay.utils.DateTimeUtils;
import com.shenpu.proxy.pay.utils.DateUtils;
import com.shenpu.proxy.pay.utils.HttpClientUtil;
import com.shenpu.proxy.pay.utils.IpUtils;
import com.shenpu.proxy.pay.utils.JsonUtils;
import com.shenpu.proxy.pay.utils.PayCommonUtil;
import com.shenpu.proxy.pay.utils.Settings;
import com.shenpu.proxy.pay.utils.SimpleHttpUtil;
import com.shenpu.proxy.pay.utils.WechatPayUtil;
import com.shenpu.proxy.pay.utils.WechatUtil;

/**
 * 
 * @desc 微信支付控制器
 *
 * @author hcy
 *
 * 2016年10月18日
 */
@Controller
@RequestMapping("/wxpay")
public class WxPayController {

	private final static Logger logger = LoggerFactory.getLogger(WxPayController.class);

	@Autowired
	private Settings settings;	
	
	@Autowired
	private PayCommonUtil PayCommonUtil;
	
	@Autowired
	private WechatPayUtil WechatPayUtil;
	
	@Resource WxPayService wxPayService;
	
	@Resource OrderService orderService;
	
	@Resource PayService payService;
	
	@RequestMapping(value="/test")
	public ModelAndView getOpenid(HttpServletResponse response) throws IOException{
		System.out.println("-----------");
//		response.sendRedirect("test1");
		ModelAndView view = new ModelAndView("test1");
		return  view;
	}
	
	
	
	/**
	 * 支付入口
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/entrance")
	public String entrance(Order order,HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter printWriter = null;
		try {
			//保存订单信息
			orderService.saveOrder(order);
			//获取code后再获取openid
			String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+settings.getWxAppId()
					+"&redirect_uri="+URLEncoder.encode(settings.getWxRedirectUrl()+"?orderNo="+order.getOrderno(), "UTF-8")
					+"&response_type=code&scope=snsapi_base&state=1"+"#wechat_redirect";
			System.out.println(codeUrl);
			response.sendRedirect(codeUrl);
		} catch (Exception e) {
			logger.error("统一下单失败:", e);
			return null;
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
		return null;
	}
	
	/**
	 * 统一下单
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/unifiedorder")
	@ResponseBody
	@SuppressWarnings("static-access")
	public Map<String, Object> unifiedorder(
			@RequestParam(value = "orderno", required = true) String orderno, 
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		try {
			System.out.println("unifiedorder=========start");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Order Order = orderService.queryOrderByOrderno(orderno);
			System.out.println("Order:"+Order.toString());
			response.setContentType("text/json;charset=utf-8"); 
			String spbill_create_ip = IpUtils.getIPLocation(request);
			String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			SortedMap<Object, Object> paramsMap = new TreeMap<Object, Object>();
			paramsMap.put("appid", settings.getWxAppId());
			paramsMap.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", ""));
			paramsMap.put("mch_id", settings.getWxMchId());
			paramsMap.put("out_trade_no", Order.getOrderno());
			paramsMap.put("notify_url", settings.getWxNotifyUrl());
			paramsMap.put("trade_type", settings.getWxTradeType());
			paramsMap.put("body", Order.getBody());
			/**
			 * { "goods_detail":[ { "goods_id":"iphone6s_16G", "wxpay_goods_id":"1001", 
			 * "goods_name":"iPhone6s 16G", "quantity":1, "price":528800, "goods_category":"123456", "body":"苹果手机" }, 
			 * { "goods_id":"iphone6s_32G", "wxpay_goods_id":"1002", "goods_name":"iPhone6s 32G", "quantity":1, 
			 * "price":608800, "goods_category":"123789", "body":"苹果手机" } ] }
			 */
			
			String totalfee="1";
			String payTest = settings.getENV();
			if (!"test".equals(payTest)) {
				totalfee = String.valueOf(Double.valueOf(Order.getTotalfee())*100);
				totalfee = totalfee.substring(0, totalfee.indexOf("."));
			}
			System.out.println("tt========="+totalfee);
			paramsMap.put("total_fee", totalfee);
			paramsMap.put("spbill_create_ip", spbill_create_ip);
			String openid = request.getSession().getAttribute("openid").toString();
			if (StringUtils.isBlank(openid) ) {
				//转至支付入口
				response.sendRedirect("entrance.hsml");
				return null;
			}else {
				paramsMap.put("openid", openid);
			}
			String sign = PayCommonUtil.createWxSign("UTF-8", paramsMap);
			paramsMap.put("sign", sign);
			String content = PayCommonUtil.dealWxParameters2XmlStr(paramsMap);
			String bakMsg = new HttpClientUtil().postStringEntity(url, content);
			
			Map<String, Object> resMap = PayCommonUtil.dealXmlStr2Map2(bakMsg);
			System.out.println("resMap========="+resMap);
			
			String returnCode = resMap.get("return_code").toString();
			String returnMsg = resMap.get("return_msg").toString();
			if ("SUCCESS".equals(returnCode)) {
				String resultCode = resMap.get("result_code").toString();
				String resultSign = resMap.get("sign").toString();
				sign = WechatPayUtil.getSignByUnifiedOrder(resMap);
				if (!resultSign.equals(sign)) {
					resultMap.put("code", "10000");
					resultMap.put("message", "签名错误");
				} else {
					if ("SUCCESS".equals(resultCode)) {
						resultMap.put("code", "00000");
						resultMap.put("message", "");
						Map<String, Object> map = new HashMap<String, Object>();
						// 封装支付 数据 并求 sign
						map.put("appId", settings.getWxAppId());
						map.put("timeStamp", new Date().getTime() / 1000);
						map.put("nonceStr", CustomerStringUtils.generateString(16));
						map.put("package", "prepay_id=" + resMap.get("prepay_id"));
						map.put("signType", "MD5");
						// 获得sign 并填充
						String paySign = WechatPayUtil.getSignByH5Pay(map);
						map.put("paySign", paySign);
						String data = JsonUtils.ObjectToJson(map);
						resultMap.put("message", data);
						
						// 插入交易记录
						Pay pay = new Pay();
						// 保存支付信息
						pay.setOrderNo(orderno);
						pay.setPayId(UUID.randomUUID().toString().replace("-", ""));
						pay.setInsertTime(new Date());
						pay.setPayMoney(new BigDecimal(Order.getTotalfee()));
						pay.setPayChannel("WX");
						pay.setPayState("0");
						//保存预支付信息。
						payService.saveWxPayInfo(pay);
					} else {
						resultMap.put("code", "50000");
						resultMap.put("message", resMap.get("err_code_des"));
					}
				}
			} else {
				resultMap.put("code", "50000");
				resultMap.put("message", returnMsg);
			}
			return resultMap;
		} catch (Exception e) {
			logger.error("统一下单失败:", e);
			return null;
		} 
	}

	/**
	 * 获取微信支付回调网关数据
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/notify")
	public String notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter printWriter = null;
		String backXmlString = null;
		try {
			response.setContentType("text/plain;charset=utf-8");   
			printWriter = response.getWriter();
			
			@SuppressWarnings("static-access")
			SortedMap<String, String> reqMap = PayCommonUtil.dealWxRequest2Map(request);
			
			logger.debug(">>>>>>>>>>>>>>接收到微信支付回调 请求reqMap:{}", JsonUtils.obj2Json(reqMap));
			// 处理数据 并生成返回数据
			Map<String, Object> resMap = new HashMap<String, Object>();
			// 微信状态标识
			String returnCode = reqMap.get("return_code");
			// 微信支付商户订单号
			String orderNo = reqMap.get("out_trade_no");
			// 此处需要程序员自己完成，我没有写此判断
			if (returnCode.equals("SUCCESS")) {
				// TODO 增加处理同步锁,根据订单号同步处理
				// 支付状态标识
				String resultCode = reqMap.get("result_code");
				if ("SUCCESS".equals(resultCode)) {
					// 支付成功
					// 校验签名，防止非法回调链接
					String reqSign = reqMap.get("sign");
					reqMap.remove("sign");
					String sign = WechatPayUtil.getSignByUnifiedOrder2(reqMap);
					logger.debug("订单号{}验证微信请求签名是否正确,微信请求签名为:{} 根据微信发送信息生成签名为:{}", orderNo, reqSign, sign);
					if (reqSign.equals(sign)) {
						// 微信订单号
						String transactionId = reqMap.get("transaction_id");
						logger.debug("订单号{}微信返回的微信订单号:{}", orderNo, transactionId);
						// 根据订单号查询订单信息
						Order order = orderService.queryOrderByOrderno(orderNo);
						logger.debug("根据订单号{}查询到的订单信息为{}", orderNo,
								ToStringBuilder.reflectionToString(order));
						// 因为微信会多次回调，此处要判断是否已处理过此订单再决定是否调用回调逻辑里的业务逻辑.查询当前微信订单号是否已通知成功
						//根据微信订单号transactionId查询支付数据
						WxPay payInfo = wxPayService.queryPayInfoById(transactionId);
						// 检查订单是否已进行过支付结果通知
						if (payInfo == null) {
							// 微信返回的订单总金额
							String totalFee = reqMap.get("total_fee") == null ? "0" : reqMap.get("total_fee");
							String payTest = settings.getENV();
							// 增加验证金额是否为订单金额
							if (new BigDecimal(order.getTotalfee())
									.compareTo(BigDecimal.valueOf(Double.parseDouble(totalFee) / 100)) == 0
									|| "test".equals(payTest)) {
								resMap.put("return_code", "SUCCESS");
								resMap.put("return_msg", "OK");
								PrintWriter pw = response.getWriter();
								String postXml = WechatUtil.mapToXml(resMap);
								pw.print(postXml);
								pw.flush();
								pw.close();
								logger.debug("订单号{}支付回调成功,返回微信内容为{}", orderNo, JsonUtils.obj2Json(postXml));
								// 增加本地更新交易记录表逻辑
								WxPay wxPay2 = new WxPay();
								wxPay2.setOutTradeNo(orderNo);
								wxPay2.setTradeState("1");
								wxPay2.setTimeEnd(DateTimeUtils.dateToString(new Date(), "yyyyMMddHHmmss"));
								wxPay2.setResultCode(resultCode);
								wxPay2.setTransactionId(reqMap.get("transaction_id"));
								boolean flag = wxPayService.updateWxPayByOrderNo(wxPay2);
								if (!flag) {
									logger.error("订单号{}微信交易记录更新异常", orderNo);
								} else {
									// 增加更新交易总表中记录的状态和完成时间
									Pay pay = new Pay();
									pay.setOrderNo(orderNo);
									pay.setPayState("1");
									pay.setUpdateTime(new Date());
									pay.setPayTime(DateUtils.parseDate(reqMap.get("time_end"), "yyyyMMddHHmmss"));
									boolean flag1 = payService.updatePayByOrderNo(pay);
									if (!flag1) {
										logger.error("订单号{}总表交易记录更新异常", orderNo);
									} else {
										// 支付成功，通知前端服务器
										Order orderInfo = orderService.queryOrderByOrderno(orderNo);
//										Order orderInfo = OrderInfoUtil.getUserOrderInfo(orderNo);
										try {
											SimpleHttpUtil.post(orderInfo.getNotify_url(), new Gson().toJson(reqMap));
										} catch (Exception e) {
											logger.error("request forward server exception", orderNo);
										}

									}
								}
							} else {
								logger.error("微信返回订单金额与数据库中订单金额不一致,微信返回金额换算后:{},数据库中订单金额:{}",
										BigDecimal.valueOf(Double.parseDouble(totalFee) / 100),
										order.getTotalfee());
								resMap.put("return_code", "SUCCESS");
								resMap.put("return_msg", "OK");
								// 增加金额错误数据记录
								WxPay wxPay1 = new WxPay();
								wxPay1.setOutTradeNo(orderNo);
								wxPay1.setTradeState("1");
								wxPay1.setTimeEnd(DateTimeUtils.dateToString(new Date(), "yyyyMMddHHmmss"));
								wxPay1.setResultCode(resultCode);
								wxPay1.setTransactionId(reqMap.get("transaction_id"));
								wxPay1.setErrCode(BigDecimal.valueOf(Double.parseDouble(totalFee) / 100).toString());
								wxPay1.setErrCodeDes(totalFee);
//								 boolean flag =
//								 wxPayService.(wxPay);
//								 if (!flag) {
//									 logger.error("订单{} 金额错误记录插入失败", orderNo);
//								 }
//								 PrintWriter pw = response.getWriter();
//								 String postXml = WechatUtil.mapToXml(resMap);
//								 pw.print(postXml);
//								 pw.flush();
//								 pw.close();
//								 logger.debug("订单号{}支付回调成功,但金额不符,返回微信内容为{}",
//								 orderNo,
//								 JsonUtils.obj2Json(postXml));
							}
						} else {
							logger.debug(">>>>>>>>>>>>>>该笔订单,商户订单号{},微信订单号{} 已成功支付", orderNo, transactionId);
							resMap.put("return_code", "SUCCESS");
							resMap.put("return_msg", "OK");
							PrintWriter pw = response.getWriter();
							String postXml = WechatUtil.mapToXml(resMap);
							pw.print(postXml);
							pw.flush();
							pw.close();
							logger.debug("订单号{}支付回调成功,该订单已支付,返回微信内容为{}", orderNo, JsonUtils.obj2Json(postXml));
						}
					} else {
						logger.error("订单号{}签名错误,返回微信失败", orderNo);
						resMap.put("return_code", "FAIL");
						resMap.put("return_msg", "签名失败");
						PrintWriter pw = response.getWriter();
						String postXml = WechatUtil.mapToXml(resMap);
						pw.print(postXml);
						pw.flush();
						pw.close();
						logger.debug("订单号{}支付回调成功,签名错误,返回微信内容为{}", orderNo, JsonUtils.obj2Json(postXml));
					}
				} else {
					logger.warn("订单号{}微信返回的支付状态标识为非SUCCESS", orderNo);
					resMap.put("return_code", "SUCCESS");
					resMap.put("return_msg", "OK");
					// 增加本地更新交易记录表逻辑
					WxPay wxPay = new WxPay();
					wxPay.setOutTradeNo(orderNo);
					wxPay.setTradeState("2");
					wxPay.setTimeEnd(DateTimeUtils.dateToString(new Date(), "yyyyMMddHHmmss"));
					wxPay.setResultCode(resultCode);
					wxPay.setErrCode(reqMap.get("err_code"));
					wxPay.setErrCodeDes(reqMap.get("err_code_des"));
					
					wxPayService.updateWxPayByOrderNo(wxPay);
					 
					PrintWriter pw = response.getWriter();
					String postXml = WechatUtil.mapToXml(resMap);
					pw.print(postXml);
					pw.flush();
					pw.close();
					logger.debug("订单号{}支付回调成功,支付状态标识为非SUCCESS,返回微信内容为{}", orderNo, JsonUtils.obj2Json(postXml));
				}
			} else {
				logger.debug("订单号{}微信回调发送请求信息中return_code非SUCCESS", orderNo);
				resMap.put("return_code", "FAIL");
				resMap.put("return_msg", "支付失败");
				PrintWriter pw = response.getWriter();
				String postXml = WechatUtil.mapToXml(resMap);
				pw.print(postXml);
				pw.flush();
				pw.close();
				logger.debug("订单号{}支付回调成功,return_code非SUCCESS,返回微信内容为{}", orderNo, JsonUtils.obj2Json(postXml));
			}
		} catch (Exception e) {
			logger.error("处理微信支付回调数据失败:", e);
			backXmlString = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[处理失败]]></return_msg></xml>";
			printWriter.print(backXmlString);
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
		return null;
	}
	
	
	/**
	 * 获取微信支付回调网关数据
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/success")
	public ModelAndView success(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView("/success");
		String orderNo = request.getParameter("orderNo");
		Order order = orderService.queryOrderByOrderno(orderNo);
		view.addObject("order", order);
		return view;
	}
	
}
