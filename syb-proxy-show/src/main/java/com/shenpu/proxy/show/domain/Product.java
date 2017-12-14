package com.shenpu.proxy.show.domain;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String productId;

    private String productName;

    private String productCode;

    private Boolean productType;

    private String mainPic;

    private String attachPic;

    private String merits;

    private String clauseUrl;

    private String instructionsUrl;

    private Date insertTime;

    private String insertManager;

    private Date updateTime;

    private Date updateManager;

    private Boolean status;

    private String productDesc;

    private String healthNotice;

    private String insureInform;

    private Channel channel;
    
    private Company company;
    
    public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public Boolean getProductType() {
        return productType;
    }

    public void setProductType(Boolean productType) {
        this.productType = productType;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic == null ? null : mainPic.trim();
    }

    public String getAttachPic() {
        return attachPic;
    }

    public void setAttachPic(String attachPic) {
        this.attachPic = attachPic == null ? null : attachPic.trim();
    }

    public String getMerits() {
        return merits;
    }

    public void setMerits(String merits) {
        this.merits = merits == null ? null : merits.trim();
    }

    public String getClauseUrl() {
        return clauseUrl;
    }

    public void setClauseUrl(String clauseUrl) {
        this.clauseUrl = clauseUrl == null ? null : clauseUrl.trim();
    }

    public String getInstructionsUrl() {
        return instructionsUrl;
    }

    public void setInstructionsUrl(String instructionsUrl) {
        this.instructionsUrl = instructionsUrl == null ? null : instructionsUrl.trim();
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

    public Date getUpdateManager() {
        return updateManager;
    }

    public void setUpdateManager(Date updateManager) {
        this.updateManager = updateManager;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }

    public String getHealthNotice() {
        return healthNotice;
    }

    public void setHealthNotice(String healthNotice) {
        this.healthNotice = healthNotice == null ? null : healthNotice.trim();
    }

    public String getInsureInform() {
        return insureInform;
    }

    public void setInsureInform(String insureInform) {
        this.insureInform = insureInform == null ? null : insureInform.trim();
    }
}