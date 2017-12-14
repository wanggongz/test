package com.shenpu.proxy.verify.check.bo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * 险种检查列表属性结点
 * 
 * @author jetty
 */
public class AttributeBO implements Serializable {
	private static final long serialVersionUID = 649091583697200392L;

	@XmlAttribute(name="id")
	private String id;

	@XmlAttribute(name="name")
	private String name;

	@XmlElement(name="itemList")
	private List<ItemBO> itemList;

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

	public List<ItemBO> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemBO> itemList) {
		this.itemList = itemList;
	}
}
