package com.shenpu.proxy.show.domain;

import java.io.Serializable;

public class CompanyChannel implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 5667421209184807759L;

	private String id;

    private String status;
    
    private Channel channel;
    
    private String companyId;
    
    public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}