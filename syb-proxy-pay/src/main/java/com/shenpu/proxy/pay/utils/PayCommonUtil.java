package com.shenpu.proxy.pay.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

/**
 * 微信支付回调主要工具类
 * 
 * @author hcy
 * 
 * 2016年10月18日
 */
@Component
public class PayCommonUtil {
	
	@Autowired
	private Settings settings;
	
	/**
	 * 判断微信支付签名是否正确
	 * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * @return boolean
	 */
	public boolean judgeWxNotifySign(String characterEncoding, SortedMap<String, String> paramsMap) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> es = paramsMap.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while(it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if(!"sign".equals(k) && null != v && !"".equals(v)) {
				System.out.println("key="+entry.getKey()+",value="+entry.getValue());
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + settings.getWxAppSecret());
		System.out.println(sb.toString());
		String mysign = DigestUtils.md5Hex(sb.toString()).toLowerCase();
		String tenpaySign = ((String)paramsMap.get("sign")).toLowerCase();
		System.out.println("mysign="+mysign);
		return tenpaySign.equals(mysign);
	}

	/**
	 * 微信支付创建签名
	 * @param characterEncoding
	 * @param paramsMap
	 * @param API_KEY
	 * @return
	 */
	public String createWxSign(String characterEncoding, SortedMap<Object, Object> paramsMap) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<Object, Object>> es = paramsMap.entrySet();
		Iterator<Entry<Object, Object>> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>)it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + settings.getPartnerkey());
		System.out.println(sb.toString());
		String sign = DigestUtils.md5Hex(sb.toString()).toUpperCase();
		System.out.println("sign="+sign);
		return sign;
	}

	/**
	 * 将请求参数转换为xml格式的string
	 * @param paramsMap
	 * @return
	 */
	public static String dealWxParameters2XmlStr(SortedMap<Object, Object> paramsMap) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set<Entry<Object, Object>> es = paramsMap.entrySet();
		Iterator<Entry<Object, Object>> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>)it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
	
	/**
	 * 生成一个十位的随机数
	 * @return
	 */
	public static String generateTenBitRamdam() {
		StringBuilder stringBuilder = new StringBuilder();
		Integer a[] = new Integer[10];
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (10 * (Math.random()));
			stringBuilder.append(a[i].toString());
		}
		return stringBuilder.toString();
	}
	
	/**
	 * 将微信返回的数据流转换成map
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static SortedMap<String, String> dealWxRequest2Map(HttpServletRequest request) throws IOException {
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		SortedMap<String, String> map = new TreeMap<String, String>();
		try {
			inputStream = request.getInputStream();
			if (inputStream == null) {
				return null;
			}else {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				StringBuffer stringBuffer = new StringBuffer();
				String tempOneLine = new String("");
				while ((tempOneLine = bufferedReader.readLine()) != null) {
					stringBuffer.append(tempOneLine);
				}
				String requestDomString = stringBuffer.toString();
				SAXReader reader = new SAXReader();
				Document document = reader.read(new ByteArrayInputStream(requestDomString.getBytes()));
				Element rootElement = document.getRootElement();
				@SuppressWarnings("unchecked")
				List<Element> elementList = rootElement.elements();  
		        for (Element e : elementList) {
		        	map.put(e.getName(), e.getText());
		        }
		        return map ;
			}
		}catch (Exception e) {
			return null;
		}finally {
			if(inputStream != null){
				inputStream.close();
			}
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
	}
	
	/**
	 * 将微信返回的数据流转换成json
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException 
	 */
	public static String dealXmlStr2JsonStr(String xmlString) throws IOException, DocumentException {
		Gson gson = new Gson();
		SAXReader reader = new SAXReader();
		Document document = reader.read(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
		Map<String, String> map = new HashMap<String, String>();
		Element rootElement = document.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> elementList = rootElement.elements();  
        for (Element e : elementList)  
            map.put(e.getName(), e.getText());
        return gson.toJson(map);
	}
	
	
	/**
	 * 将微信返回的数据流转换成map
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException 
	 */
	public static Map<String, String> dealXmlStr2Map(String xmlString) throws IOException, DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
		Map<String, String> map = new HashMap<String, String>();
		Element rootElement = document.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> elementList = rootElement.elements();  
        for (Element e : elementList)  
            map.put(e.getName(), e.getText());
        return map;
	}
	
	
	public static Map<String, Object> dealXmlStr2Map2(String xmlString) throws IOException, DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
		Map<String, Object> map = new HashMap<String, Object>();
		Element rootElement = document.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> elementList = rootElement.elements();  
        for (Element e : elementList)  
            map.put(e.getName(), e.getText());
        return map;
	}
	
	
	/**
	 * 支付宝将参数拼接成“参数=参数值”的格式
	 * @param parametersMap
	 * @return
	 */
	public static String dealAliParameters2LinkStr(SortedMap<Object, Object> parametersMap) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<Object, Object>> es = parametersMap.entrySet();
		Iterator<Entry<Object, Object>> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>)it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append( k + "=" + v);
		}
		return sb.toString();
	}
	
	/**
	 * 将支付的回调请求参数生成map
	 * @param request
	 * @return
	 */
	public static SortedMap<String, String> dealAliNotify2Map(HttpServletRequest request) {
		SortedMap<String,String> params = new TreeMap<String,String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		if(requestParams!=null){
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
		}
		return params;
	}
	
	/**
	 * 支付宝将参数拼接成“参数1=参数值1&参数2=参数值2”的格式
	 * @param paramsMap
	 * @return
	 */
	public static String dealAliParameters2UrlStr(SortedMap<String, String> paramsMap) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> es = paramsMap.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (it.hasNext()) {
				sb.append( k + "=" + v + "&");
			}else {
				sb.append( k + "=" + v);
			}
		}
		return sb.toString();
	}
}
