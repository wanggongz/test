package com.shenpu.proxy.verify.check.bo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class InstrBO {

	@XmlAttribute(name = "id")
	private String id;
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "kind")
	private String kind;
	
	@XmlAttribute(name = "showMultInclude")
	private String showMultInclude;
	
	@XmlAttribute(name = "hasProductSpec")
	private String hasProductSpec;
	
	@XmlAttribute(name = "beginDate")
	private String beginDate;
	
	@XmlElementWrapper(name="rules")
	@XmlElement(name="rule")
	private List<RuleBO> ruleBOs;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getShowMultInclude() {
		return showMultInclude;
	}

	public void setShowMultInclude(String showMultInclude) {
		this.showMultInclude = showMultInclude;
	}

	public String getHasProductSpec() {
		return hasProductSpec;
	}

	public void setHasProductSpec(String hasProductSpec) {
		this.hasProductSpec = hasProductSpec;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public List<RuleBO> getRuleBOs() {
		return ruleBOs;
	}

	public void setRuleBOs(List<RuleBO> ruleBOs) {
		this.ruleBOs = ruleBOs;
	}
}
