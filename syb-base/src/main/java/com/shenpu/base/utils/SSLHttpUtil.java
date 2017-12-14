package com.shenpu.base.utils;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SSLHttpUtil {

	private final static Logger logger = LogManager.getLogger(SSLHttpUtil.class);
	
	private static PoolingHttpClientConnectionManager cm = null;
	
	//@Value("${sslKeyPath: D:\\lemon_workspace\\tomcat.keystore}")
	@SuppressWarnings("unused")
	private String sslKeyPath;
	//@Value("${sslPassword: lemon123")
	@SuppressWarnings("unused")
	private String sslPassword;

	static { 
		SSLConnectionSocketFactory  sslsf = null;
		try {
			//sslKeyPath: "D:\\lemon_workspace\\tomcat.keystore"
			//sslPassword: "lemon123"
			SSLContext sslcontext = SSLContexts.custom()
					.loadTrustMaterial(new File("D:\\lemon_workspace\\tomcat.keystore"), "lemon123".toCharArray(), new TrustSelfSignedStrategy())
					.build();
			sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
			
		} catch (NoSuchAlgorithmException e) {
			logger.error("Not exist encryption algorithm, Fail to create SSLConnectionSocketFactory", e);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			logger.error("Cannot find keystore file, Fail to create SSLConnectionSocketFactory", e);
		} catch (CertificateException e) {
			logger.error("Certification is illegal, Fail to create SSLConnectionSocketFactory", e);
		} catch (IOException e) {
			logger.error("IO exception, Fail to create SSLConnectionSocketFactory", e);
		}

		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();
		cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(20);
	}

	private static CloseableHttpClient getSSLHttpClient() {
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
		return httpClient;
	}
	
	/**
	 * get请求 SSL/TSL
	 * 
	 * @param url
	 * @return
	 */
	public static String stringEntitySSLGet(String url, String keyPath, String password) {
		CloseableHttpResponse rsp = null;
		try {
			CloseableHttpClient httpClient = getSSLHttpClient();
			HttpGet httpGet = new HttpGet(url);
			rsp = httpClient.execute(httpGet);
			HttpEntity resEntity = rsp.getEntity();
			String bakMsg = EntityUtils.toString(resEntity);
			logger.info("SSL/TSL网关返回报文" + bakMsg);
			return bakMsg;
		} catch (Exception e) {
			logger.error("请求SSL/TSL网关失败:", e);
			return null;
		} finally {
			close(rsp);
		}
	}

	/**
	 * post请求 SSL/TSL(字符串)
	 * 
	 * @param url
	 * @return
	 */
	public static String stringEntitySSLPost(String url, String content) {
		CloseableHttpResponse rsp = null;
		try {
			CloseableHttpClient httpClient = getSSLHttpClient();
			StringEntity stringEntity = new StringEntity(content, "UTF-8");
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(stringEntity);
			rsp = httpClient.execute(httppost);
			HttpEntity resEntity = rsp.getEntity();
			String bakMsg = EntityUtils.toString(resEntity);
			logger.info("post请求SSL/TSL网关返回报文" + bakMsg);
			return bakMsg;
		} catch (Exception e) {
			logger.error("post请求SSL/TSL网关失败:", e);
			return null;
		} finally {
			close(rsp);
		}
	}
	
	/**
	 * SSL post请求一般表单
	 * 
	 * @param url
	 * @param paras
	 * @return
	 */
	public static String parametersSSLPost(String url, Map<String, String> paras) {
		CloseableHttpResponse rsp = null;
		try {
			CloseableHttpClient httpClient = getSSLHttpClient();
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
	 * SSL post文件
	 * 
	 * @param url
	 * @param path
	 * @return
	 */
	public static String fileEntitySSLPost(String url, String keyPath, String password, String path) {
		CloseableHttpResponse rsp = null;
		try {
			CloseableHttpClient httpClient = getSSLHttpClient();
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
	 * SSL post文件
	 * 
	 * @param url
	 * @param file
	 * @return
	 */
	public static String fileEntitySSLPost(String url, String keyPath, String password, File file) {
		CloseableHttpResponse rsp = null;
		try {
			CloseableHttpClient httpClient = getSSLHttpClient();
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
