package com.shenpu.proxy.pay.utils;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @desc 请求网关工具 
 *
 * @author hcy
 *
 * 2016年10月18日
 */
public class HttpClientUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	public String postStringEntity(String url, String entityString) {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse rsp = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(url);
			StringEntity stringEntity = new StringEntity(entityString, "UTF-8");
			/**
			 *如果进行模拟成浏览器的头，要进行如此修改，比如微信跳转 
			httppost.setHeader("Content-Type","text/xml");
			httppost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;"); 
			httppost.setHeader("Accept-Language", "zh-cn"); 
			httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3"); 
			httppost.setHeader("Accept-Charset", "utf-8"); 
			httppost.setHeader("Keep-Alive", "300"); 
			httppost.setHeader("Connection", "Keep-Alive"); 
			httppost.setHeader("Cache-Control", "no-cache"); 
			 */
			httppost.setEntity(stringEntity);
			rsp = httpclient.execute(httppost);
		    HttpEntity resEntity = rsp.getEntity();
		    String bakMsg = EntityUtils.toString(resEntity,Charset.forName("UTF-8"));
		    logger.info("post请求网关返回报文>>>>>>>>>>"+ bakMsg);
		    return bakMsg;
		} catch (Exception e) {
			logger.error("post请求网关失败:", e);
			return null;
		} finally {
			if (rsp != null) {
				try {
					rsp.close();
				} catch (IOException e) {
					logger.error("关闭httpclient的rsp失败:", e);
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					logger.error("关闭httpclient失败:", e);
				}
			}
		}
	}
	
	public String getStringEntity(String url) {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse rsp = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			rsp = httpclient.execute(httpGet);
		    HttpEntity resEntity = rsp.getEntity();
		    String bakMsg = EntityUtils.toString(resEntity,Charset.forName("UTF-8"));
		    logger.info("get请求网关返回报文>>>>>>>>>"+ bakMsg);
		    return bakMsg;
		} catch (Exception e) {
			logger.error("get请求网关失败:", e);
			return null;
		} finally {
			if (rsp != null) {
				try {
					rsp.close();
				} catch (IOException e) {
					logger.error("关闭httpclient的rsp失败:", e);
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					logger.error("关闭httpclient失败:", e);
				}
			}
		}
	}
}
