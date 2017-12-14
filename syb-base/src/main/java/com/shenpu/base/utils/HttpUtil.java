package com.shenpu.base.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * http工具类
 * 
 * @author jetty
 *
 */
public class HttpUtil {

	private final static Logger logger = LogManager.getLogger(HttpUtil.class);

	private static PoolingHttpClientConnectionManager cm = null;

	static {
		cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(20);
	}

	private static CloseableHttpClient getHttpClient() {
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
		return httpClient;
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String stringEntityGet(String url) {
		CloseableHttpResponse rsp = null;
		try {
			CloseableHttpClient httpClient = getHttpClient();
			HttpGet httpGet = new HttpGet(url);
			rsp = httpClient.execute(httpGet);
			HttpEntity resEntity = rsp.getEntity();
			String bakMsg = EntityUtils.toString(resEntity);
			logger.info("网关返回报文" + bakMsg);
			return bakMsg;
		} catch (Exception e) {
			logger.error("请求网关失败:", e);
			return null;
		} finally {
			close(rsp);
		}
	}

	/**
	 * post字符串
	 * @param url
	 * @param string
	 * @return
	 */
	public static String stringEntityPost(String url, String content, String character) {
		CloseableHttpResponse rsp = null;
		try {
			CloseableHttpClient httpClient = getHttpClient();
			HttpPost httpPost = new HttpPost(url);
			StringEntity stringEntity = new StringEntity(content, character);
			httpPost.setEntity(stringEntity);
			rsp = httpClient.execute(httpPost);
			HttpEntity resEntity = rsp.getEntity();
			String bakMsg = EntityUtils.toString(resEntity);
			logger.info("网关返回报文" + bakMsg);
			return bakMsg;
		} catch (Exception e) {
			logger.error("请求网关失败:", e);
			return null;
		} finally {
			close(rsp);
		}
	}
	/**
	 * post文件
	 * 
	 * @param url
	 * @param path
	 * @return
	 */
	public static String fileEntityPost(String url, String path) {
		CloseableHttpResponse rsp = null;
		try {
			CloseableHttpClient httpClient = getHttpClient();
			HttpPost httpPost = new HttpPost(url);
			FileEntity fileEntity = new FileEntity(new File(path));
			httpPost.setEntity(fileEntity);
			rsp = httpClient.execute(httpPost);
			HttpEntity resEntity = rsp.getEntity();
			String bakMsg = EntityUtils.toString(resEntity);
			logger.info("网关返回报文" + bakMsg);
			return bakMsg;
		} catch (Exception e) {
			logger.error("请求网关失败:", e);
			return null;
		} finally {
			close(rsp);
		}
	}

	/**
	 * post文件
	 * 
	 * @param url
	 * @param file
	 * @return
	 */
	public static String fileEntityPost(String url, File file) {
		CloseableHttpResponse rsp = null;
		try {
			CloseableHttpClient httpClient = getHttpClient();
			HttpPost httpPost = new HttpPost(url);
			FileEntity fileEntity = new FileEntity(file);
			httpPost.setEntity(fileEntity);
			rsp = httpClient.execute(httpPost);
			HttpEntity resEntity = rsp.getEntity();
			String bakMsg = EntityUtils.toString(resEntity);
			logger.info("网关返回报文" + bakMsg);
			return bakMsg;
		} catch (Exception e) {
			logger.error("请求网关失败:", e);
			return null;
		} finally {
			close(rsp);
		}
	}

	/**
	 * post请求一般表单
	 * 
	 * @param url
	 * @param paras
	 * @return
	 */
	public static String parametersPost(String url, Map<String, String> paras) {
		CloseableHttpResponse rsp = null;
		try {
			CloseableHttpClient httpClient = getHttpClient();
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (paras != null && !paras.isEmpty()) {
				for (Entry<String, String> entry : paras.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			rsp = httpClient.execute(httpPost);
			HttpEntity resEntity = rsp.getEntity();
			String bakMsg = EntityUtils.toString(resEntity);
			logger.info("网关返回报文" + bakMsg);
			return bakMsg;
		} catch (Exception e) {
			logger.error("请求网关失败:", e);
			return null;
		} finally {
			close(rsp);
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @param rsp
	 * @param httpclient
	 */
	private static void close(CloseableHttpResponse rsp) {
		try {
			if (rsp != null) {
				rsp.close();
			}
		} catch (IOException e) {
			logger.error("关闭网关请求连接失败:", e);
		}
	}
}
