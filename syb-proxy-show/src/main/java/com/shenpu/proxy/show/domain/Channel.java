package com.shenpu.proxy.show.domain;

import java.io.Serializable;

public class Channel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8686211287106127946L;

	private String channelId;

    private String channeName;

    private Boolean channelType;

    private String channelDesc;

    private String channelCode;

    private String channelLinker;

    private String mobile;

    private String email;

    private Boolean status;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getChanneName() {
        return channeName;
    }

    public void setChanneName(String channeName) {
        this.channeName = channeName == null ? null : channeName.trim();
    }

    public Boolean getChannelType() {
        return channelType;
    }

    public void setChannelType(Boolean channelType) {
        this.channelType = channelType;
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc == null ? null : channelDesc.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getChannelLinker() {
        return channelLinker;
    }

    public void setChannelLinker(String channelLinker) {
        this.channelLinker = channelLinker == null ? null : channelLinker.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}