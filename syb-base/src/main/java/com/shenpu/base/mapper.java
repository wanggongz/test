package com.shenpu.base;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType
@XmlRootElement(name="mapper")
public class mapper {
	@XmlElement(name="resultMap")
	public String resultMap;
	
}
