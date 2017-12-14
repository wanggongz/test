package com.shenpu.proxy.pay.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Levi.Wang Web工具类
 */
public class WebUtils {

	/**
	 * @Title: getParams
	 * @Description: 获得HttpServletRequest请求的Parameters
	 * @param request
	 * @return String
	 * @throws
	 */
	public static String getParamsAsJson(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		return JsonUtils.obj2Json(params);
	}

	/**
	 * @Title: getHeaders
	 * @Description: 获得HttpServletRequest请求的Headers
	 * @param request
	 * @return String
	 * @throws
	 */
	public static String getHeadersAsJson(HttpServletRequest request) {
		Map<String, List<String>> headers = Maps.newHashMap();
		Enumeration<String> namesEnumeration = request.getHeaderNames();
		while (namesEnumeration.hasMoreElements()) {
			String name = namesEnumeration.nextElement();
			Enumeration<String> valueEnumeration = request.getHeaders(name);
			List<String> values = Lists.newArrayList();
			while (valueEnumeration.hasMoreElements()) {
				values.add(valueEnumeration.nextElement());
			}
			headers.put(name, values);
		}
		return JsonUtils.obj2Json(headers);
	}

	/**
	 * @Title: getPostBytes
	 * @Description: 读取HttpServletRequest Body为字节数组
	 * @param request
	 * @return byte[]
	 * @throws
	 */
	public static byte[] getPostAsBytes(HttpServletRequest request) {
		int contentLength = request.getContentLength();
		if (contentLength < 0)
			return null;

		byte[] content;
		try {
			content = IOUtils.toByteArray(request.getInputStream());
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}

		return content;
	}

	/**
	 * @Title: getPostString
	 * @Description: 读取HttpServletRequest Body为字符串
	 * @param request
	 * @param encoding
	 * @return String
	 * @throws
	 */
	public static String getPostAsString(HttpServletRequest request, String encoding) {
		byte[] contentByte = getPostAsBytes(request);

		String content;
		try {
			content = new String(contentByte, encoding);
		} catch (UnsupportedEncodingException e) {
			throw Throwables.propagate(e);
		}

		return content;
	}

	/**
	 * @Title: getPostString
	 * @Description: 读取HttpServletRequest Body为字符串（默认为UTF-8）
	 * @param request
	 * @return String
	 * @throws
	 */
	public static String getPostAsString(HttpServletRequest request) {
		return getPostAsString(request, "UTF-8");
	}

	
	/** 
	* 获得浏览器版本
	*
	* @param agent
	* @return String 
	*/
	public static String getBrowserName(String agent) {
		if (agent.indexOf("msie 7") > 0) {
			return "ie7";
		} else if (agent.indexOf("msie 8") > 0) {
			return "ie8";
		} else if (agent.indexOf("msie 9") > 0) {
			return "ie9";
		} else if (agent.indexOf("msie 10") > 0) {
			return "ie10";
		} else if (agent.indexOf("msie") > 0) {
			return "ie";
		} else if (agent.indexOf("opera") > 0) {
			return "opera";
		} else if (agent.indexOf("opera") > 0) {
			return "opera";
		} else if (agent.indexOf("firefox") > 0) {
			return "firefox";
		} else if (agent.indexOf("webkit") > 0) {
			return "webkit";
		} else if (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0) {
			return "ie11";
		} else {
			return "Others";
		}
	}
	
	public static String urlEncode(String url){
		String urlE="";
		try {
			urlE = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Throwables.propagate(e);
		}
		
		return urlE;
	}

}
