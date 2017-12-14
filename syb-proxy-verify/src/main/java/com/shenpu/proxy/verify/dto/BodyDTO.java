package com.shenpu.proxy.verify.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class BodyDTO {

	@XmlElement(name = "Appnt")
	private AppntDTO appnt;
	
	@XmlElementWrapper (name = "InsurdList" )
	@XmlElement(name = "Insurd")
	private List<InsuredDTO> insuredList;
	
	@XmlElementWrapper (name = "BeneList" )
	@XmlElement(name = "Bene")
	private List<BeneDTO> beneList;
	
	@XmlElement(name = "Policy")
	private PolicyDTO policyDTO;
	
	//返回的保费说明
	@XmlElementWrapper (name = "InsuredPremiumList" )
	@XmlElement(name = "InsuredPremium")
	private List<InsuredPremiumDTO> insuredPremiumList;
	
	//交易流水号
	@XmlElement(name = "ApplyCode")
	private String applyCode;

	//试算结果
	@XmlElement(name = "TrailResult")
	private String trailResult;
	
	
	public String getTrailResult() {
		return trailResult;
	}

	public void setTrailResult(String trailResult) {
		this.trailResult = trailResult;
	}

	public AppntDTO getAppnt() {
		return appnt;
	}

	public void setAppnt(AppntDTO appnt) {
		this.appnt = appnt;
	}

	public List<InsuredDTO> getInsuredList() {
		return insuredList;
	}

	public void setInsuredList(List<InsuredDTO> insuredList) {
		this.insuredList = insuredList;
	}

	public List<BeneDTO> getBeneList() {
		return beneList;
	}

	public void setBeneList(List<BeneDTO> beneList) {
		this.beneList = beneList;
	}

	public PolicyDTO getPolicyDTO() {
		return policyDTO;
	}

	public void setPolicyDTO(PolicyDTO policyDTO) {
		this.policyDTO = policyDTO;
	}

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public List<InsuredPremiumDTO> getInsuredPremiumList() {
		return insuredPremiumList;
	}

	public void setInsuredPremiumList(List<InsuredPremiumDTO> insuredPremiumList) {
		this.insuredPremiumList = insuredPremiumList;
	}

	@Override
	public String toString() {
		return "BodyDTO [appnt=" + appnt + ", insuredList=" + insuredList + ", beneList=" + beneList + ", policyDTO="
				+ policyDTO + ", insuredPremiumList=" + insuredPremiumList + ", applyCode=" + applyCode + "]";
	}
	
	
}
