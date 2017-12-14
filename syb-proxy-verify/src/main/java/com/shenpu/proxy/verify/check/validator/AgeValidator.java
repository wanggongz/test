package com.shenpu.proxy.verify.check.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.shenpu.base.utils.DateUtil;
import com.shenpu.base.utils.DyMethodUtil;
import com.shenpu.base.utils.IdcardUtil;
import com.shenpu.proxy.verify.check.msg.Message;
import com.shenpu.proxy.verify.dto.InsuredDTO;
import com.shenpu.proxy.verify.dto.PacketDTO;


/**
 * 险种年龄范围校验
 * 
 * @author jetty
 *
 */
public class AgeValidator {

	/**
	 * 获取年龄(天数)
	 * @return
	 */
	public static int getAgeByDay(String birthday) {
		return DateUtil.getAgeByDay(DateUtil.parse(birthday), new Date(System.currentTimeMillis()));
	}
	
	/**
	 * 获取年龄(年)
	 * @return
	 */
	public static int getAge(String birthday) {
		return DateUtil.getAge(DateUtil.parse(birthday), new Date(System.currentTimeMillis()));
	}

	/**
	 * 动态执行校验脚本
	 * @param PacketDTO
	 * @param jexlString
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public static List<Message> execute(String classString, PacketDTO packetDTO, String jexlString, String msg) throws ClassNotFoundException {
		List<Message> messages = new ArrayList<>();
		List<InsuredDTO> insuredList = packetDTO.getBodyDTO().getInsuredList();
		for(InsuredDTO insuredDTO : insuredList) {
			Map<String, Object> map = new HashMap<>();
			map.put("AgeValidator", AgeValidator.class);
			String birthday = "";
			if(StringUtils.isBlank(insuredDTO.getBirthday())) {
				birthday = IdcardUtil.getBirthByIdCard(insuredDTO.getIdNo());
			}else {
				birthday = insuredDTO.getBirthday();
			}
			map.put("birthday", birthday);
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
