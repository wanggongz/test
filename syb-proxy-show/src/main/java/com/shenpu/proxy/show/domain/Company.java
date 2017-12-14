package com.shenpu.proxy.show.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Company implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2892589052171984677L;

	private String companyId;

    private String companyName;

    private String companyCode;

    private String address;

    private String zipCode;

    private String email;

    private String qq;

    private String mobile;

    private Boolean companyType;

    private String companyLinker;

    private Boolean status;

    private Date insertTime;

    private String insertManager;

    private Date updateTime;

    private String updateManager;

    private List<CompanyChannel> list;
    
    public List<CompanyChannel> getList() {
		return list;
	}

	public void setList(List<CompanyChannel> list) {
		this.list = list;
	}

	public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Boolean getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Boolean companyType) {
        this.companyType = companyType;
    }

    public String getCompanyLinker() {
        return companyLinker;
    }

    public void setCompanyLinker(String companyLinker) {
        this.companyLinker = companyLinker == null ? null : companyLinker.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getInsertManager() {
        return insertManager;
    }

    public void setInsertManager(String insertManager) {
        this.insertManager = insertManager == null ? null : insertManager.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateManager() {
        return updateManager;
    }

    public void setUpdateManager(String updateManager) {
        this.updateManager = updateManager == null ? null : updateManager.trim();
    }
}