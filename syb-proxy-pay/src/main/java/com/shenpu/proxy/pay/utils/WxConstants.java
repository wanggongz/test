package com.shenpu.proxy.pay.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 
 * @desc 建立微信基本的请求参数
 *
 * @author hcy
 *
 * 2016年10月18日
 */
@Repository
public class WxConstants {
	private final static Logger logger = LoggerFactory.getLogger(WxConstants.class);
	private String accessToken = "";
	private Long accessTokenTimestamp = 1L;
	private String ticket;
	private Long ticketTimestamp = 1L;
	private String openid;

	@Autowired
	private Settings settings;
	
	public String getTicket() {
		setTicket();
		return ticket;
	}

	public String getAccessToken() {
		setAccessToken();
		return accessToken;
	}

	public Long getAccessTokenTimestamp() {
		return accessTokenTimestamp;
	}

	public void setAccessTokenTimestamp(Long accessTokenTimestamp) {
		this.accessTokenTimestamp = accessTokenTimestamp;
	}

	public Long getTicketTimestamp() {
		return ticketTimestamp;
	}

	public void setTicketTimestamp(Long ticketTimestamp) {
		this.ticketTimestamp = ticketTimestamp;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	/**
	 * 获取access token
	 */
	public void setAccessToken() {
		Long currentTimestamp = System.currentTimeMillis();
		if ("".equals(accessToken) || currentTimestamp - accessTokenTimestamp >= 7200000) {
			try {
				String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+
						settings.getWxAppId()+"&secret=" + settings.getWxAppSecret();
				String bakMsg = new HttpClientUtil().getStringEntity(url);
				if (bakMsg != null) {
					Gson gson = new Gson();
					JsonObject jsonobj = gson.fromJson(bakMsg, JsonObject.class);
					logger.info("获取access_token的返回字符串：" + jsonobj.get("access_token").getAsString());
					setAccessToken(jsonobj.get("access_token").getAsString());
					setAccessTokenTimestamp(currentTimestamp);
				}
			} catch (Exception e) {
				logger.error("请求微信网关access_token失败：", e);
			}
		}
	}

	/**
	 * 获取ticket
	 */
	public void setTicket() {
		setAccessToken();
		Long currentTimestamp = System.currentTimeMillis();
		if ("".equals(ticket) || currentTimestamp - ticketTimestamp >= 7200000) {
			try {
				String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken
						+ "&type=jsapi";
				String bakMsg = new HttpClientUtil().getStringEntity(url);
				if (bakMsg != null) {
					Gson gson = new Gson();
					Object obj = gson.fromJson(bakMsg, JsonObject.class);
					JsonObject jsonobj = (JsonObject) obj;
					logger.info("获取ticket的返回字符串：" + jsonobj.get("ticket").getAsString());
					setTicket(jsonobj.get("ticket").getAsString());
					setTicketTimestamp(currentTimestamp);
				}
			} catch (Exception e) {
				logger.error("请求微信网关ticket失败：", e);
			}
		}
	}
	
	public String getOpenid(String code) {
		setOpenid(code);
		return openid;
	}
	
	/**
	 * 获取openid
	 */
	public void setOpenid(String code) {
		try {
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+settings.getWxAppId()
					+"&secret="+settings.getWxAppSecret()+"&code="+code+"&grant_type=authorization_code";
			String bakMsg = new HttpClientUtil().getStringEntity(url);
			if (bakMsg != null) {
				Gson gson = new Gson();
				JsonObject jsonobj = gson.fromJson(bakMsg, JsonObject.class);
				logger.info("===="+jsonobj.toString());
				logger.info("获取openid的返回字符串：" + jsonobj.get("openid").getAsString());
				this.openid = jsonobj.get("openid").getAsString();
			}
		} catch (Exception e) {
			logger.error("请求微信网关openid失败：", e);
		}
	}
}
