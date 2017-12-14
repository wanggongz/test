package com.shenpu.proxy.verify.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class InsuredPremiumDTO {
	
	//被保人顺序
	@XmlElement(name = "InsuredGrade")
	private String insuredGrade;
	
	//被保人名字
	@XmlElement(name = "InsuredName")
	private String insuredName;
	
	//被保人顺序
	@XmlElement(name = "Premium")
	private String premium;

	public String getInsuredGrade() {
		return insuredGrade;
	}

	public void setInsuredGrade(String insuredGrade) {
		this.insuredGrade = insuredGrade;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	@Override
	public String toString() {
		return "InsuredPremiumDTO [insuredGrade=" + insuredGrade + ", insuredName=" + insuredName + ", premium="
				+ premium + "]";
	}
	
}
