package com.shenpu.proxy.pay.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 微信支付工具类
 * 
 * @author zp
 */
@Component
public class WechatPayUtil {

	private final static Logger log = LoggerFactory.getLogger(WechatPayUtil.class);

	@Autowired
	private Settings settings;
	
//	private static Map<String, String> CONFIG = ResourceUtils.getResource("config/wxconfig").getMap();
	
	/**
	 * 请求方的签名key
	 */
//	private static final String key = CONFIG.get("key");

	/**
	 * notify 求 sign
	 * 
	 * @return
	 */
//	public static String getSignByNotify(Map<String, String> reqMap) {
//		log.info(">>>>>>>>>>>>>>>  getSignByNotify  START ");
//		// 排序
//		String sortMap = WechatUtil.sortMapStrToStr(reqMap);
//		sortMap += "&key=" + partnerkey;
//		log.info("---------- sort String sign  " + sortMap);
//		String sign = MD5.MD5Encode(sortMap).toUpperCase();
//		log.info("---------- sign  " + sign);
//		log.info("<<<<<<<<<<<<<<<  getSignByNotify  END ");
//		return sign;
//	}

//	/**
//	 * 统一下单 求 sign
//	 * 
//	 * @return
//	 */
//	public static String getSignByUnifiedOrder(UnifiedOrderEntity order) {
//		log.info(">>>>>>>>>>>>>>>  getSignByUnifiedOrder  START ");
//		// 根据对象获得 map 数据
//		Map<String, Object> map = WechatUtil.beanToMap(order);
//		// 排序
//		String sortMap = WechatUtil.sortMapToStr(map);
//		sortMap += "&key=" + partnerkey;
//		log.info("---------- sort String sign  " + sortMap);
//		String sign = MD5.MD5Encode(sortMap).toUpperCase();
//		log.info("---------- sign  " + sign);
//		log.info("<<<<<<<<<<<<<<<  getSignByUnifiedOrder  END ");
//		return sign;
//	}

	/**
	 * 统一下单 生成 http请求数据 XML
	 * 
	 * @param order
	 * @param sign
	 * @return
	 */
//	public static String getReqtXmlByUnifiedOrder(UnifiedOrderEntity order, String sign) {
//		log.info(">>>>>>>>>>>>>>>  getPostXmlByUnifiedOrder  START ");
//		// 根据对象获得 map 数据
//		Map<String, Object> map = WechatUtil.beanToMap(order);
//		map.put("sign", sign);
//		String xml = WechatUtil.mapToXml(map);
//		log.info("---------- xml   " + xml);
//		log.info("<<<<<<<<<<<<<<<  getPostXmlByUnifiedOrder  END ");
//		return xml;
//	}

	// /**
	// * 查询订单 生成 http请求数据 XML
	// * @param order
	// * @param sign
	// * @return
	// */
	// public static String getReqtXmlByCheckWeixinOrder(CheckWeixinOrder
	// order,String sign) {
	// log.info(">>>>>>>>>>>>>>> getPostXmlByUnifiedOrder START ");
	// //根据对象获得 map 数据
	// Map<String,Object> map =WechatUtil.beanToMap(order);
	// map.put("sign", sign);
	// String xml = WechatUtil.mapToXml(map);
	// log.info("---------- xml "+xml);
	// log.info("<<<<<<<<<<<<<<< getPostXmlByUnifiedOrder END ");
	// return xml;
	// }
	//
	/**
	 * 统一下单 正确返回 求 sign
	 * 
	 * @param resMap
	 * @return
	 */
	public String getSignByUnifiedOrder(Map<String, Object> map) {
		log.info("---------------------------->>>>>>>>>>>>>>>  getSignByUnifiedOrder  START ");
		// 剔除返回的 sign
		map.remove("sign");
		// 排序
		String sortMap = WechatUtil.sortMapToStr(map);
		sortMap += "&key=" + settings.getPartnerkey();
		log.info("---------- sort String sign  " + sortMap);
		String sign = MD5.MD5Encode(sortMap).toUpperCase();
		log.info("---------- sign  " + sign);
		log.info("----------------------------<<<<<<<<<<<<<<<  getSignByUnifiedOrder  END ");
		return sign;
	}

	
	public String getSignByUnifiedOrder2(Map<String, String> map) {
		log.info("---------------------------->>>>>>>>>>>>>>>  getSignByUnifiedOrder  START ");
		// 剔除返回的 sign
		map.remove("sign");
		// 排序
		String sortMap = WechatUtil.sortMapToStr2(map);
		sortMap += "&key=" + settings.getPartnerkey();
		log.info("---------- sort String sign  " + sortMap);
		String sign = MD5.MD5Encode(sortMap).toUpperCase();
		log.info("---------- sign  " + sign);
		log.info("----------------------------<<<<<<<<<<<<<<<  getSignByUnifiedOrder  END ");
		return sign;
	}
	
	
	/**
	 * H5 支付的 paySign
	 * 
	 * @param map
	 * @return
	 */
	public String getSignByH5Pay(Map<String, Object> map) {
		log.info("---------------------------->>>>>>>>>>>>>>>  getSignByH5Pay  START ");
		// 排序
		String sortMap = WechatUtil.sortMapToStr(map);
		sortMap += "&key=" + settings.getPartnerkey();
		log.info("---------- sort str paySign  " + sortMap);
		// 获得sign 并填充
		String sign = MD5.MD5Encode(sortMap).toUpperCase();
		log.info("---------- paySign  " + sign);
		log.info("----------------------------<<<<<<<<<<<<<<<  getSignByH5Pay  END ");
		return sign;
	}

	
	public String getSignByH5Pay2(Map<String, String> map) {
		log.info("---------------------------->>>>>>>>>>>>>>>  getSignByH5Pay  START ");
		// 排序
		String sortMap = WechatUtil.sortMapToStr2(map);
		sortMap += "&key=" + settings.getPartnerkey();
		log.info("---------- sort str paySign  " + sortMap);
		// 获得sign 并填充
		String sign = MD5.MD5Encode(sortMap).toUpperCase();
		log.info("---------- paySign  " + sign);
		log.info("----------------------------<<<<<<<<<<<<<<<  getSignByH5Pay  END ");
		return sign;
	}
	
}
