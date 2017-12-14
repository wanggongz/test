package com.shenpu.proxy.verify.check.bo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import org.eclipse.persistence.oxm.annotations.XmlCDATA;

/**
 * 规则下的IF节点
 * 
 * @author jetty
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class IfBO {
	
	@XmlAttribute(name="id")
	private String id;

	@XmlAttribute(name="msg")
	private String msg;
	
	@XmlCDATA
	@XmlElement(name="test")
	private String test;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
}