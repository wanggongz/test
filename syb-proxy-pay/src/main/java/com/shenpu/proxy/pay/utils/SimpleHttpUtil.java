package com.shenpu.proxy.pay.utils;

import java.util.Map;

/**
 * @author Levi.Wang Http简单工具类
 */
public class SimpleHttpUtil {
	private final static String DEFAULT_CHARSET = "UTF-8";

	public static String post(String url, String postcontent) {
		return post(url, postcontent, DEFAULT_CHARSET);
	}

	public static String post(String url, String postcontent, int timeout) {
		return post(url, postcontent, DEFAULT_CHARSET, timeout);
	}

	public static String post(String url, String postcontent, String charset) {
		return post(url, postcontent, charset, null);
	}

	public static String post(String url, String postcontent, String charset, int timeout) {
		return post(url, postcontent, charset, null, timeout);
	}

	public static String post(String url, String postcontent, String charset, Map<String, String> headerMap) {
		return post(url, postcontent, charset, headerMap, 0);
	}

	public static String post(String url, String postcontent, String charset, Map<String, String> headerMap,int timeout) {
		HttpUtilResponse httpResponse = HttpUtil.post(url, postcontent, charset, headerMap, timeout);
		if (httpResponse.getStatuscode() != 200) {
			StringBuilder strbuilder = new StringBuilder();
			strbuilder.append("HttpPost异常  url:");
			strbuilder.append(url);
			strbuilder.append("status:");
			strbuilder.append(httpResponse.getStatuscode());
			strbuilder.append(httpResponse.getContent());
			throw new RuntimeException(strbuilder.toString());
		}

		return httpResponse.getContent(charset);
	}

	public static String get(String url) {
		return get(url, DEFAULT_CHARSET);
	}

	public static String get(String url, String responseCharset) {
		return get(url, responseCharset, null);
	}

	public static String get(String url, String responseCharset, Map<String, String> headerMap) {
		return get(url, responseCharset, headerMap, 0);
	}

	public static String get(String url, String responseCharset, Map<String, String> headerMap, int timeout) {
		HttpUtilResponse httpResponse = HttpUtil.get(url, headerMap, timeout);
		if (httpResponse.getStatuscode() != 200) {
			StringBuilder strbuilder = new StringBuilder();
			strbuilder.append("HttpGet异常  url:");
			strbuilder.append(url);
			strbuilder.append("status:");
			strbuilder.append(httpResponse.getStatuscode());
			throw new RuntimeException(strbuilder.toString());
		}

		return httpResponse.getContent(responseCharset);
	}

	public static byte[] getAsBytes(String url, Map<String, String> headerMap) {
		return getAsBytes(url, headerMap, 0);
	}

	public static byte[] getAsBytes(String url, Map<String, String> headerMap, int timeout) {
		HttpUtilResponse httpResponse = HttpUtil.get(url, headerMap, timeout);
		if (httpResponse.getStatuscode() != 200) {
			StringBuilder strbuilder = new StringBuilder();
			strbuilder.append("HttpGet异常  url:");
			strbuilder.append(url);
			strbuilder.append("status:");
			strbuilder.append(httpResponse.getStatuscode());
			throw new RuntimeException(strbuilder.toString());
		}

		return httpResponse.getData();
	}
}
