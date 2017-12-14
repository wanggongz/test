package com.shenpu.proxy.pay.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 
 * @desc 系统配置读取类
 * 2016年10月18日
 */
@RefreshScope
@Component
public class Settings {
	
	@Value("${sitePath}")
	public String sitePath;
	
	@Value("${env}")
	public String ENV;
	
	/**
	 * 微信
	 */
	//app服务号id
	@Value("${WX_APP_ID}")
	public String wxAppId;
	//app服务号的秘钥
	@Value("${WX_APP_SECRET}")
	public String wxAppSecret;
	//商户号
	@Value("${WX_MCH_ID}")
	public String wxMchId;
	//商户密钥
	@Value("${partnerkey}")
	public String partnerkey;
	//货币类型
	@Value("${WX_FEE_TYPE}")
	public String wxFeeType;
	//支付通知地址
	@Value("${WX_NOTIFY_URL}")
	public String wxNotifyUrl;
	//交易类型	
	@Value("${WX_TRADE_TYPE}")
	public String wxTradeType;
	//app签名的秘钥
	@Value("${WX_API_KEY}")
	public String wxApiKey;
	//微信公众账户appid
	@Value("${WX_WX_APPID}")
	public String wxWxAppId;
	//商户名称
	@Value("${WX_SEND_NAME}")
	public String wxSendName;
	//微信重定向地址
	@Value("${WX_REDIRECT_URI}")
	public String wxRedirectUrl;
	
	/**
	 * 支付宝
	 */
	//开发者应用私钥，详见配置密钥
	@Value("${ALI_APP_PRIVATE_KEY}")
	public String aliAppPrivateKey;
	//请求和签名使用的字符编码格式，支持GBK和UTF-8
	@Value("${ALI_CHARSET}")
	public String aliCharset;
	//支付宝公钥，详见配置密钥
	@Value("${ALI_PAY_private_KEY}")
	public String aliPayPrivateKey;
	//支付宝分配给开发者的应用ID
	@Value("${ALI_APP_ID}")
	public String aliAppId;
	//支付宝分配给开发者的应用ID
	@Value("${ALI_RETURN_URL}")
	public String aliReturnUrl;
	//支付宝分配给开发者的应用ID
	@Value("${ALI_NOTIFY_URL}")
	public String aliNotifyUrl;
	
	
	public String getWxFeeType() {
		return wxFeeType;
	}
	public void setWxFeeType(String wxFeeType) {
		this.wxFeeType = wxFeeType;
	}
	public String getSitePath() {
		return sitePath;
	}
	public void setSitePath(String sitePath) {
		this.sitePath = sitePath;
	}
	public String getENV() {
		return ENV;
	}
	public void setENV(String eNV) {
		ENV = eNV;
	}
	public String getWxAppId() {
		return wxAppId;
	}
	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}
	public String getWxAppSecret() {
		return wxAppSecret;
	}
	public void setWxAppSecret(String wxAppSecret) {
		this.wxAppSecret = wxAppSecret;
	}
	public String getWxMchId() {
		return wxMchId;
	}
	public void setWxMchId(String wxMchId) {
		this.wxMchId = wxMchId;
	}
	public String getPartnerkey() {
		return partnerkey;
	}
	public void setPartnerkey(String partnerkey) {
		this.partnerkey = partnerkey;
	}
	public String getWxNotifyUrl() {
		return sitePath+wxNotifyUrl;
	}
	public void setWxNotifyUrl(String wxNotifyUrl) {
		this.wxNotifyUrl = wxNotifyUrl;
	}
	public String getWxTradeType() {
		return wxTradeType;
	}
	public void setWxTradeType(String wxTradeType) {
		this.wxTradeType = wxTradeType;
	}
	public String getWxApiKey() {
		return wxApiKey;
	}
	public void setWxApiKey(String wxApiKey) {
		this.wxApiKey = wxApiKey;
	}
	public String getWxWxAppId() {
		return wxWxAppId;
	}
	public void setWxWxAppId(String wxWxAppId) {
		this.wxWxAppId = wxWxAppId;
	}
	public String getWxSendName() {
		return wxSendName;
	}
	public void setWxSendName(String wxSendName) {
		this.wxSendName = wxSendName;
	}
	public String getWxRedirectUrl() {
		return sitePath+wxRedirectUrl;
	}
	public void setWxRedirectUrl(String wxRedirectUrl) {
		this.wxRedirectUrl = wxRedirectUrl;
	}
	public String getAliAppPrivateKey() {
		return aliAppPrivateKey;
	}
	public void setAliAppPrivateKey(String aliAppPrivateKey) {
		this.aliAppPrivateKey = aliAppPrivateKey;
	}
	public String getAliCharset() {
		return aliCharset;
	}
	public void setAliCharset(String aliCharset) {
		this.aliCharset = aliCharset;
	}
	public String getAliPayPrivateKey() {
		return aliPayPrivateKey;
	}
	public void setAliPayPrivateKey(String aliPayPrivateKey) {
		this.aliPayPrivateKey = aliPayPrivateKey;
	}
	public String getAliAppId() {
		return aliAppId;
	}
	public void setAliAppId(String aliAppId) {
		this.aliAppId = aliAppId;
	}
	public String getAliReturnUrl() {
		return aliReturnUrl;
	}
	public void setAliReturnUrl(String aliReturnUrl) {
		this.aliReturnUrl = aliReturnUrl;
	}
	public String getAliNotifyUrl() {
		return aliNotifyUrl;
	}
	public void setAliNotifyUrl(String aliNotifyUrl) {
		this.aliNotifyUrl = aliNotifyUrl;
	}

	
}
