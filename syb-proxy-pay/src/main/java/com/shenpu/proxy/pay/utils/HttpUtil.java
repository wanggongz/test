package com.shenpu.proxy.pay.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Throwables;

/**
 * @author Levi.Wang Http工具类
 */
public class HttpUtil {
	private final static String DEFAULT_CHARSET = "UTF-8";

	private final static CloseableHttpClient httpclient;
	private final static RequestConfig config;
	static {
		// 启用连接线程池，最大150个连接
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(150);
		// 默认超时 ConnectionRequestTimeout连接池超时 ConnectTimeout建立连接超时 SocketTimeout读取数据超时
		config = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000).setCookieSpec(null).build();
		httpclient = HttpClients.custom().setDefaultRequestConfig(config).setConnectionManager(cm).build();
	}

	public static HttpUtilResponse post(String url, String content, String charset, Map<String, String> headerMap, int timeout) {

		String localCharset;

		if (StringUtils.isEmpty(charset))
			localCharset = DEFAULT_CHARSET;
		else
			localCharset = charset;

		byte[] data;
		try {
			data = content.getBytes(localCharset);
		} catch (UnsupportedEncodingException e) {
			throw Throwables.propagate(e);
		}

		return post(url, data, headerMap, timeout);

	}

	public static HttpUtilResponse post(String url, byte[] data, Map<String, String> headerMap, int timeout) {

		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			if (data != null) {
				ByteArrayEntity byteEntity = new ByteArrayEntity(data);
				httpPost.setEntity(byteEntity);
			}
			if (headerMap != null) {
				for (Map.Entry<String, String> entry : headerMap.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}

			if (timeout != 0) {
				RequestConfig localConfig = RequestConfig.custom().setSocketTimeout(timeout).build();
				httpPost.setConfig(localConfig);
			}

			response = httpclient.execute(httpPost);

			HttpUtilResponse httpUtilResponse = new HttpUtilResponse();
			httpUtilResponse.setStatuscode(response.getStatusLine().getStatusCode());
			httpUtilResponse.setHeaders(response.getAllHeaders());
			httpUtilResponse.setData(EntityUtils.toByteArray(response.getEntity()));

			return httpUtilResponse;
		} catch (Exception e) {
			throw Throwables.propagate(e);
		} finally {
			try {
				if (response != null)
					response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static HttpUtilResponse get(String url, Map<String, String> headerMap, int timeout) {

		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			if (headerMap != null) {
				for (Map.Entry<String, String> entry : headerMap.entrySet()) {
					httpGet.addHeader(entry.getKey(), entry.getValue());
				}
			}
			if (timeout != 0) {
				RequestConfig localConfig = RequestConfig.custom().setSocketTimeout(timeout).build();
				httpGet.setConfig(localConfig);
			}
			response = httpclient.execute(httpGet);

			HttpUtilResponse httpUtilResponse = new HttpUtilResponse();
			httpUtilResponse.setStatuscode(response.getStatusLine().getStatusCode());
			httpUtilResponse.setHeaders(response.getAllHeaders());
			httpUtilResponse.setData(EntityUtils.toByteArray(response.getEntity()));

			return httpUtilResponse;
		} catch (Exception e) {
			throw Throwables.propagate(e);
		} finally {
			try {
				if (response != null)
					response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
