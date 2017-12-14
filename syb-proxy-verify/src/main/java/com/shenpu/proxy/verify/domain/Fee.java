package com.shenpu.proxy.verify.domain;

import java.io.Serializable;

public class Fee implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String inusYear;

    private String inusYearUnit;

    private Integer payYear;

    private String parYearUnit;

    private String sex;

    private Integer age;

    private Integer policyYear;

    private String cashValue;

    private String productCode;

    private String feeId;

    private String insurId;
    
    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId == null ? null : feeId.trim();
    }

    public String getInsurId() {
        return insurId;
    }

    public void setInsurId(String insurId) {
        this.insurId = insurId == null ? null : insurId.trim();
    }

    public String getInusYear() {
        return inusYear;
    }

    public void setInusYear(String inusYear) {
        this.inusYear = inusYear == null ? null : inusYear.trim();
    }

    public String getInusYearUnit() {
        return inusYearUnit;
    }

    public void setInusYearUnit(String inusYearUnit) {
        this.inusYearUnit = inusYearUnit == null ? null : inusYearUnit.trim();
    }

    public Integer getPayYear() {
        return payYear;
    }

    public void setPayYear(Integer payYear) {
        this.payYear = payYear;
    }

    public String getParYearUnit() {
        return parYearUnit;
    }

    public void setParYearUnit(String parYearUnit) {
        this.parYearUnit = parYearUnit == null ? null : parYearUnit.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPolicyYear() {
        return policyYear;
    }

    public void setPolicyYear(Integer policyYear) {
        this.policyYear = policyYear;
    }

    public String getCashValue() {
        return cashValue;
    }

    public void setCashValue(String cashValue) {
        this.cashValue = cashValue == null ? null : cashValue.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

	@Override
	public String toString() {
		return "Fee [inusYear=" + inusYear + ", inusYearUnit=" + inusYearUnit + ", payYear=" + payYear
				+ ", parYearUnit=" + parYearUnit + ", sex=" + sex + ", age=" + age + ", policyYear=" + policyYear
				+ ", cashValue=" + cashValue + ", productCode=" + productCode + ", feeId=" + feeId + ", insurId="
				+ insurId + "]";
	}
    
    
}