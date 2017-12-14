package com.shenpu.proxy.verify.domain;

import java.io.Serializable;
import java.util.Date;

public class InsureLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String insureLogId;

    private String applyCode;

    private Date reqTime;

    private Date rspTime;

    private String description;

    private String url;

    private String ip;

    private String userId;

    private String userName;

    private String logType;

    private Long elapsedTime;

    private String reqStr;

    private String rspStr;

    public String getInsureLogId() {
        return insureLogId;
    }

    public void setInsureLogId(String insureLogId) {
        this.insureLogId = insureLogId == null ? null : insureLogId.trim();
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode == null ? null : applyCode.trim();
    }

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    public Date getRspTime() {
        return rspTime;
    }

    public void setRspTime(Date rspTime) {
        this.rspTime = rspTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    public Long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getReqStr() {
        return reqStr;
    }

    public void setReqStr(String reqStr) {
        this.reqStr = reqStr == null ? null : reqStr.trim();
    }

    public String getRspStr() {
        return rspStr;
    }

    public void setRspStr(String rspStr) {
        this.rspStr = rspStr == null ? null : rspStr.trim();
    }

	@Override
	public String toString() {
		return "InsureLog [insureLogId=" + insureLogId + ", applyCode=" + applyCode + ", reqTime=" + reqTime
				+ ", rspTime=" + rspTime + ", description=" + description + ", url=" + url + ", ip=" + ip + ", userId="
				+ userId + ", userName=" + userName + ", logType=" + logType + ", elapsedTime=" + elapsedTime
				+ ", reqStr=" + reqStr + ", rspStr=" + rspStr + "]";
	}
    
    
}