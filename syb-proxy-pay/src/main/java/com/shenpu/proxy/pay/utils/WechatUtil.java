package com.shenpu.proxy.pay.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 微信工具类
 * 
 * @author zp
 */
public class WechatUtil {

	/**
	 * 把 XML 字符串 转成 Map
	 * 
	 * @param xmlString
	 * @return
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static Map<String, Object> getMapFromXML(String xmlString) {
		// 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream is = getStringStream(xmlString);
			document = builder.parse(is);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		// 获取到document里面的全部结点
		NodeList allNodes = document.getFirstChild().getChildNodes();
		Node node;
		Map<String, Object> map = new HashMap<String, Object>();
		int i = 0;
		while (i < allNodes.getLength()) {
			node = allNodes.item(i);
			if (node instanceof Element) {
				map.put(node.getNodeName(), node.getNodeValue());
			}
			i++;
		}
		return map;
	}

	public static InputStream getStringStream(String sInputString)
			throws UnsupportedEncodingException {
		ByteArrayInputStream tInputStringStream = null;
		if (sInputString != null && !sInputString.trim().equals("")) {
			tInputStringStream = new ByteArrayInputStream(
					sInputString.getBytes("UTF-8"));
		}
		return tInputStringStream;
	}

	/**
	 * 生成xml
	 * 
	 * @param mapData
	 * @param sign
	 * @return
	 */
	public static String mapToXml(Map<String, Object> mapData) {
		StringBuilder str = new StringBuilder("<xml>");
		Set<String> keySet = mapData.keySet();
		List<String> keyList = new ArrayList<String>(keySet);
		Collections.sort(keyList);
		int listSize = keyList.size();
		for (int i = 0; i < listSize; i++) {
			String key = keyList.get(i);
			str.append("<" + key + ">");
			str.append(mapData.get(key));
			str.append("</" + key + ">");
		}
		str.append("</xml>");
		return str.toString();
	}

	/**
	 * map 排序 生成签名字符串
	 * 
	 * @param mapData
	 * @return
	 */
	public static String sortMapToStr(Map<String, Object> mapData) {
		String str = "";
		Set<String> keySet = mapData.keySet();
		List<String> keyList = new ArrayList<String>(keySet);
		Collections.sort(keyList);
		int listSize = keyList.size();
		for (int i = 0; i < listSize; i++) {
			String key = keyList.get(i);
			str += key + "=" + mapData.get(key) + "&";
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	
	public static String sortMapToStr2(Map<String, String> mapData) {
		String str = "";
		Set<String> keySet = mapData.keySet();
		List<String> keyList = new ArrayList<String>(keySet);
		Collections.sort(keyList);
		int listSize = keyList.size();
		for (int i = 0; i < listSize; i++) {
			String key = keyList.get(i);
			str += key + "=" + mapData.get(key) + "&";
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}
	
	
	/**
	 * 把javaBean 转为 Map<String,Object> 实现类是 TreeMap 所以是 按 a-z的顺序排列的
	 * 
	 * @param thisObj
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object thisObj) {
		// 这里 用TreeMap 获得 的 是 排序好的map
		Map<String, Object> map = new TreeMap<String, Object>();
		@SuppressWarnings("rawtypes")
		Class c;
		try {
			c = Class.forName(thisObj.getClass().getName());
			Method[] m = c.getMethods();
			for (int i = 0; i < m.length; i++) {
				String method = m[i].getName();
				if (method.startsWith("get")) {
					try {
						Object value = m[i].invoke(thisObj);
						if (value != null) {
							String key = method.substring(3);
							key = key.substring(0, 1).toLowerCase()
									+ key.substring(1);
							if (!key.equalsIgnoreCase("class")) {
								map.put(key, value);
							}
						}
					} catch (Exception e) {
						System.out.println("error:" + method);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	public static String sortMapStrToStr(Map<String, String> mapData) {
		String str = "";
		Set<String> keySet= mapData.keySet();
		List<String> keyList = new ArrayList<String>(keySet);
		Collections.sort(keyList);
		int listSize = keyList.size();
		for (int i = 0; i < listSize; i++) {
			String key = keyList.get(i);
			str += key+"="+mapData.get(key)+"&";
		}
		str = str.substring(0,str.length()-1);
		return str;
	}
}
