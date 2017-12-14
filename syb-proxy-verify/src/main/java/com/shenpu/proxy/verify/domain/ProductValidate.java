package com.shenpu.proxy.verify.domain;

import java.io.Serializable;

public class ProductValidate implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String productId;

    private String productCode;

    private Boolean status;

    private String validateXml;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getValidateXml() {
        return validateXml;
    }

    public void setValidateXml(String validateXml) {
        this.validateXml = validateXml == null ? null : validateXml.trim();
    }

	@Override
	public String toString() {
		return "ProductValidate [productId=" + productId + ", productCode=" + productCode + ", status=" + status
				+ ", validateXml=" + validateXml + "]";
	}
    
    
}