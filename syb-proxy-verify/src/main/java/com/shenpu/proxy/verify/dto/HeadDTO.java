package com.shenpu.proxy.verify.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class HeadDTO {
	
	@XmlElement(name="RequestID")
	private String requestId;
	
	@XmlElement(name = "RequestType") 
	private String requestType;
	
	//渠道用户名
	@XmlElement(name = "User") 
	private String user;
	
	//渠道密码
	@XmlElement(name = "Password") 
	private String password;
	
	//返回错误代码(只是说明请求情况)
	@XmlElement(name = "ErrorCode")
	private String ErrorCode;
	
	//返回错误信息(只是说明请求情况)
	@XmlElement(name = "ErrorMessage") 
	private String errorMessage;
	
	@XmlElement(name = "SendTime")
	private String sendTime;
	
	//返回的请求业务代码
	@XmlElement(name = "ResponseCode") 
	private String responseCode;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	@Override
	public String toString() {
		return "HeadDTO [requestId=" + requestId + ", requestType=" + requestType + ", user=" + user + ", password="
				+ password + ", ErrorCode=" + ErrorCode + ", errorMessage=" + errorMessage + ", sendTime=" + sendTime
				+ ", responseCode=" + responseCode + "]";
	}
	
	
}
