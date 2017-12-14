package com.shenpu.proxy.print.domain;

import java.io.Serializable;
import java.util.Date;

public class Dictionary implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String codeType;

    private String code;

    private String codeLabel;

    private Integer showOrder;

    private String parentCode;

    private Date createdDate;

    private String createdUser;

    private Date modifiedDate;

    private String modifiedUser;

    private Integer status;

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType == null ? null : codeType.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCodeLabel() {
        return codeLabel;
    }

    public void setCodeLabel(String codeLabel) {
        this.codeLabel = codeLabel == null ? null : codeLabel.trim();
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser == null ? null : createdUser.trim();
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser == null ? null : modifiedUser.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "Dictionary [id=" + id + ", codeType=" + codeType + ", code=" + code + ", codeLabel=" + codeLabel
				+ ", showOrder=" + showOrder + ", parentCode=" + parentCode + ", createdDate=" + createdDate
				+ ", createdUser=" + createdUser + ", modifiedDate=" + modifiedDate + ", modifiedUser=" + modifiedUser
				+ ", status=" + status + "]";
	}
	
	
}