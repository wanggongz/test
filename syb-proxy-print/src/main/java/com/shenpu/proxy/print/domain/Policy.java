package com.shenpu.proxy.print.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Policy implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String policyId;

    private String orderNo;

    private String accessDate;

    private String applyNo;

    private String policyNo;

    private String effectiveDate;

    private String termDate;

    private String orderState;

    private String isNotice;

    private String agencyCode;

    private String outeragentCode;

    private String agencyHand;

    private String agentCode;

    private String organId;

    private String productCode;

    private BigDecimal initialPremium;

    private BigDecimal totalPremium;

    private BigDecimal firstPaymentPeriod;

    private String overdueType;

    private String deliveryMode;

    private String autoRenewal;

    private String phoneVisit;

    private String isUnderwrite;

    private String uwCause;

    private String uwMemo;

    private String manualCause;

    private Integer mult;

    private String insuWay;

    private String policyTerm;

    private String premiumTerm;

    private String payintv;

    private String insertTime;

    private String updateTime;

    private String productName;

    private String policyTermUtil;

    private String faceamount;

    private String issueDate;

    private String proposalNo;

    private String isSuccess;

    private String issuedInfo;

    private String policyUrl;

    private String signDate;

    private Integer isVerify;

    private Integer isAccess;

    private Integer isPrint;

    private String payintvName;

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId == null ? null : policyId.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(String accessDate) {
        this.accessDate = accessDate == null ? null : accessDate.trim();
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo == null ? null : policyNo.trim();
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate == null ? null : effectiveDate.trim();
    }

    public String getTermDate() {
        return termDate;
    }

    public void setTermDate(String termDate) {
        this.termDate = termDate == null ? null : termDate.trim();
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState == null ? null : orderState.trim();
    }

    public String getIsNotice() {
        return isNotice;
    }

    public void setIsNotice(String isNotice) {
        this.isNotice = isNotice == null ? null : isNotice.trim();
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode == null ? null : agencyCode.trim();
    }

    public String getOuteragentCode() {
        return outeragentCode;
    }

    public void setOuteragentCode(String outeragentCode) {
        this.outeragentCode = outeragentCode == null ? null : outeragentCode.trim();
    }

    public String getAgencyHand() {
        return agencyHand;
    }

    public void setAgencyHand(String agencyHand) {
        this.agencyHand = agencyHand == null ? null : agencyHand.trim();
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode == null ? null : agentCode.trim();
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId == null ? null : organId.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public BigDecimal getInitialPremium() {
        return initialPremium;
    }

    public void setInitialPremium(BigDecimal initialPremium) {
        this.initialPremium = initialPremium;
    }

    public BigDecimal getTotalPremium() {
        return totalPremium;
    }

    public void setTotalPremium(BigDecimal totalPremium) {
        this.totalPremium = totalPremium;
    }

    public BigDecimal getFirstPaymentPeriod() {
        return firstPaymentPeriod;
    }

    public void setFirstPaymentPeriod(BigDecimal firstPaymentPeriod) {
        this.firstPaymentPeriod = firstPaymentPeriod;
    }

    public String getOverdueType() {
        return overdueType;
    }

    public void setOverdueType(String overdueType) {
        this.overdueType = overdueType == null ? null : overdueType.trim();
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode == null ? null : deliveryMode.trim();
    }

    public String getAutoRenewal() {
        return autoRenewal;
    }

    public void setAutoRenewal(String autoRenewal) {
        this.autoRenewal = autoRenewal == null ? null : autoRenewal.trim();
    }

    public String getPhoneVisit() {
        return phoneVisit;
    }

    public void setPhoneVisit(String phoneVisit) {
        this.phoneVisit = phoneVisit == null ? null : phoneVisit.trim();
    }

    public String getIsUnderwrite() {
        return isUnderwrite;
    }

    public void setIsUnderwrite(String isUnderwrite) {
        this.isUnderwrite = isUnderwrite == null ? null : isUnderwrite.trim();
    }

    public String getUwCause() {
        return uwCause;
    }

    public void setUwCause(String uwCause) {
        this.uwCause = uwCause == null ? null : uwCause.trim();
    }

    public String getUwMemo() {
        return uwMemo;
    }

    public void setUwMemo(String uwMemo) {
        this.uwMemo = uwMemo == null ? null : uwMemo.trim();
    }

    public String getManualCause() {
        return manualCause;
    }

    public void setManualCause(String manualCause) {
        this.manualCause = manualCause == null ? null : manualCause.trim();
    }

    public Integer getMult() {
        return mult;
    }

    public void setMult(Integer mult) {
        this.mult = mult;
    }

    public String getInsuWay() {
        return insuWay;
    }

    public void setInsuWay(String insuWay) {
        this.insuWay = insuWay == null ? null : insuWay.trim();
    }

    public String getPolicyTerm() {
        return policyTerm;
    }

    public void setPolicyTerm(String policyTerm) {
        this.policyTerm = policyTerm == null ? null : policyTerm.trim();
    }

    public String getPremiumTerm() {
        return premiumTerm;
    }

    public void setPremiumTerm(String premiumTerm) {
        this.premiumTerm = premiumTerm == null ? null : premiumTerm.trim();
    }

    public String getPayintv() {
        return payintv;
    }

    public void setPayintv(String payintv) {
        this.payintv = payintv == null ? null : payintv.trim();
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime == null ? null : insertTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getPolicyTermUtil() {
        return policyTermUtil;
    }

    public void setPolicyTermUtil(String policyTermUtil) {
        this.policyTermUtil = policyTermUtil == null ? null : policyTermUtil.trim();
    }

    public String getFaceamount() {
        return faceamount;
    }

    public void setFaceamount(String faceamount) {
        this.faceamount = faceamount == null ? null : faceamount.trim();
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate == null ? null : issueDate.trim();
    }

    public String getProposalNo() {
        return proposalNo;
    }

    public void setProposalNo(String proposalNo) {
        this.proposalNo = proposalNo == null ? null : proposalNo.trim();
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess == null ? null : isSuccess.trim();
    }

    public String getIssuedInfo() {
        return issuedInfo;
    }

    public void setIssuedInfo(String issuedInfo) {
        this.issuedInfo = issuedInfo == null ? null : issuedInfo.trim();
    }

    public String getPolicyUrl() {
        return policyUrl;
    }

    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl == null ? null : policyUrl.trim();
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate == null ? null : signDate.trim();
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    public Integer getIsAccess() {
        return isAccess;
    }

    public void setIsAccess(Integer isAccess) {
        this.isAccess = isAccess;
    }

    public Integer getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(Integer isPrint) {
        this.isPrint = isPrint;
    }

    public String getPayintvName() {
        return payintvName;
    }

    public void setPayintvName(String payintvName) {
        this.payintvName = payintvName == null ? null : payintvName.trim();
    }

	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", orderNo=" + orderNo + ", accessDate=" + accessDate + ", applyNo="
				+ applyNo + ", policyNo=" + policyNo + ", effectiveDate=" + effectiveDate + ", termDate=" + termDate
				+ ", orderState=" + orderState + ", isNotice=" + isNotice + ", agencyCode=" + agencyCode
				+ ", outeragentCode=" + outeragentCode + ", agencyHand=" + agencyHand + ", agentCode=" + agentCode
				+ ", organId=" + organId + ", productCode=" + productCode + ", initialPremium=" + initialPremium
				+ ", totalPremium=" + totalPremium + ", firstPaymentPeriod=" + firstPaymentPeriod + ", overdueType="
				+ overdueType + ", deliveryMode=" + deliveryMode + ", autoRenewal=" + autoRenewal + ", phoneVisit="
				+ phoneVisit + ", isUnderwrite=" + isUnderwrite + ", uwCause=" + uwCause + ", uwMemo=" + uwMemo
				+ ", manualCause=" + manualCause + ", mult=" + mult + ", insuWay=" + insuWay + ", policyTerm="
				+ policyTerm + ", premiumTerm=" + premiumTerm + ", payintv=" + payintv + ", insertTime=" + insertTime
				+ ", updateTime=" + updateTime + ", productName=" + productName + ", policyTermUtil=" + policyTermUtil
				+ ", faceamount=" + faceamount + ", issueDate=" + issueDate + ", proposalNo=" + proposalNo
				+ ", isSuccess=" + isSuccess + ", issuedInfo=" + issuedInfo + ", policyUrl=" + policyUrl + ", signDate="
				+ signDate + ", isVerify=" + isVerify + ", isAccess=" + isAccess + ", isPrint=" + isPrint
				+ ", payintvName=" + payintvName + "]";
	}
    
    
}