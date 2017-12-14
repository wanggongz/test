package com.shenpu.proxy.verify.check.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.shenpu.base.utils.DyMethodUtil;
import com.shenpu.base.utils.IdcardUtil;
import com.shenpu.proxy.verify.check.msg.Message;
import com.shenpu.proxy.verify.dto.InsuredDTO;
import com.shenpu.proxy.verify.dto.PacketDTO;

/**
 * 险种性别校验
 * 
 * @author jetty
 *
 */
public class SexValidator {
	
	public static List<Message> execute(String classString, PacketDTO packetDTO, String jexlString, String msg) throws ClassNotFoundException {
		List<Message> messages = new ArrayList<>();
		List<InsuredDTO> insuredList = packetDTO.getBodyDTO().getInsuredList();
		for(InsuredDTO insuredDTO : insuredList) {
			Map<String, Object> map = new HashMap<>();
			map.put("SexValidator", SexValidator.class);
			String sex = "";
			if(StringUtils.isBlank(insuredDTO.getBirthday())) {
				sex = IdcardUtil.getGenderByIdCard(insuredDTO.getIdNo());
			}else {
				sex = insuredDTO.getSex();
			}
			map.put("sex", sex);
			Boolean isTrue = (Boolean) DyMethodUtil.invokeMethod(jexlString, map);
			if(!isTrue){
				Message message = new Message();
				message.setContinued(false);
				message.setMsg(msg);
				message.setReturnValue("001");
				message.setValid(true);
				messages.add(message);
			}
		}
		return messages;
	}
	
}
