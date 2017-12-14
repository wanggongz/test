package com.shenpu.proxy.verify.dto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PolicyDTO {
	
	//机构关联号（暂时用不到吧，预留字段）
	@XmlElement(name = "AgencyPolicyRef")
	private String agencyPolicyRef;
	
	//产品编码
	@XmlElement(name = "ProductCode")
	private String productCode;
	
	//产品名字
	@XmlElement(name = "ProductName") 
	private String productName;
	
	//保险期间单位(日、月、年)
	@XmlElement(name = "PolicyTermUnit")
	private String policyTermUnit;
	
	//保险期间
	@XmlElement(name = "PolicyTerm")
	private String policyTerm;
	
	//缴费期间
	@XmlElement(name = "PremiumTerm")
	private String premiumTerm;
	
	//缴费方式
	@XmlElement(name = "Payintv")
	private String payintv;
	
	//保额（单位：分）
	@XmlElement(name = "Faceamount") 
	private String faceamount;
	
	//投保日期
	@XmlElement(name = "IssueDate") 
	private String issueDate;
	
	//保单生效日期(承保才返回)
	@XmlElement(name = "EffectiveDate") 
	private String effectiveDate;
	
	//保单号（承保才返回）
	@XmlElement(name = "PolicyNo") 
	private String policyNo;
	
	//投保单号
	@XmlElement(name = "ProposalNo")
	private String proposalNo;
	
	//总保费（返回并没有此字段）
	@XmlElement(name = "TotalPremium") 
	private BigDecimal totalPremium;
	
	//是否成功
	@XmlElement(name = "IsSuccess") 
	private String isSuccess;
	
	//返回
	@XmlElement(name = "IssuedInfo") 
	private String issuedInfo;
	
	//保单承保后返回的pdf下载地址
	@XmlElement(name = "PolicyUrl") 
	private String policyUrl;
	
	//签名日期算法
	@XmlElement(name = "SignDate") 
	private String signDate;

	
	
	
	@XmlElement(name = "PayintvName") 
	private String payintvName;
	
	public String getAgencyPolicyRef() {
		return agencyPolicyRef;
	}

	public void setAgencyPolicyRef(String agencyPolicyRef) {
		this.agencyPolicyRef = agencyPolicyRef;
	}

	public String getPayintvName() {
		return payintvName;
	}

	public void setPayintvName(String payintvName) {
		this.payintvName = payintvName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPolicyTermUnit() {
		return policyTermUnit;
	}

	public void setPolicyTermUnit(String policyTermUnit) {
		this.policyTermUnit = policyTermUnit;
	}

	public String getPolicyTerm() {
		return policyTerm;
	}

	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}

	public String getPremiumTerm() {
		return premiumTerm;
	}

	public void setPremiumTerm(String premiumTerm) {
		this.premiumTerm = premiumTerm;
	}

	public String getPayintv() {
		return payintv;
	}

	public void setPayintv(String payintv) {
		this.payintv = payintv;
	}

	public String getFaceamount() {
		return faceamount;
	}

	public void setFaceamount(String faceamount) {
		this.faceamount = faceamount;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public BigDecimal getTotalPremium() {
		return totalPremium;
	}

	public void setTotalPremium(BigDecimal totalPremium) {
		this.totalPremium = totalPremium;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getIssuedInfo() {
		return issuedInfo;
	}

	public void setIssuedInfo(String issuedInfo) {
		this.issuedInfo = issuedInfo;
	}

	public String getPolicyUrl() {
		return policyUrl;
	}

	public void setPolicyUrl(String policyUrl) {
		this.policyUrl = policyUrl;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	@Override
	public String toString() {
		return "PolicyDTO [agencyPolicyRef=" + agencyPolicyRef + ", productCode=" + productCode + ", productName="
				+ productName + ", policyTermUnit=" + policyTermUnit + ", policyTerm=" + policyTerm + ", premiumTerm="
				+ premiumTerm + ", payintv=" + payintv + ", faceamount=" + faceamount + ", issueDate=" + issueDate
				+ ", effectiveDate=" + effectiveDate + ", policyNo=" + policyNo + ", proposalNo=" + proposalNo
				+ ", totalPremium=" + totalPremium + ", isSuccess=" + isSuccess + ", issuedInfo=" + issuedInfo
				+ ", policyUrl=" + policyUrl + ", signDate=" + signDate + ", payintvName=" + payintvName + "]";
	}

	
}
