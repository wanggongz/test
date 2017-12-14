package com.shenpu.proxy.verify.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Packet")  
public class PacketDTO {
	
	@XmlElement(name = "Head") 
	private HeadDTO HeadDTO;
	
	@XmlElement(name = "Body")
	private BodyDTO bodyDTO;

	public HeadDTO getHeadDTO() {
		return HeadDTO;
	}

	public void setHeadDTO(HeadDTO headDTO) {
		HeadDTO = headDTO;
	}

	public BodyDTO getBodyDTO() {
		return bodyDTO;
	}

	public void setBodyDTO(BodyDTO bodyDTO) {
		this.bodyDTO = bodyDTO;
	}

	@Override
	public String toString() {
		return "PacketDTO [HeadDTO=" + HeadDTO + ", bodyDTO=" + bodyDTO + "]";
	}
	
	
}
