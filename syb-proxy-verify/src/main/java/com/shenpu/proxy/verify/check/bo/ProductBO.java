package com.shenpu.proxy.verify.check.bo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 产品信息对像
 * 
 * @author jetty
 */
@XmlRootElement(name = "product")  
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductBO {

	@XmlElementWrapper(name = "instrs")  
	@XmlElement(name = "instr") 
	private List<InstrBO> instrs;
	
	@XmlElementWrapper(name = "rules")  
	@XmlElement(name = "rule") 
	private List<RuleBO> rules;

	public List<InstrBO> getInstrs() {
		return instrs;
	}

	public void setInstrs(List<InstrBO> instrs) {
		this.instrs = instrs;
	}

	public List<RuleBO> getRules() {
		return rules;
	}

	public void setRules(List<RuleBO> rules) {
		this.rules = rules;
	}
}
