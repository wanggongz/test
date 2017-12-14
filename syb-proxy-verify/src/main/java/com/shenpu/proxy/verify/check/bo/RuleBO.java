package com.shenpu.proxy.verify.check.bo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * 规则节点
 * 
 * @author jetty
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RuleBO {
	
	@XmlAttribute(name="id")
	private String id;

	@XmlAttribute(name="name")
	private String name;

	@XmlElements({ @XmlElement(name = "if", type = IfBO.class, nillable = true) })
	private List<IfBO> ifs;

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

	public List<IfBO> getIfs() {
		return ifs;
	}

	public void setIfs(List<IfBO> ifs) {
		this.ifs = ifs;
	}
	
}
