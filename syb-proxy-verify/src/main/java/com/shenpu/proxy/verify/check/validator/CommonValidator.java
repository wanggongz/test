package com.shenpu.proxy.verify.check.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shenpu.base.utils.JaxbUtil;
import com.shenpu.proxy.verify.check.bo.IfBO;
import com.shenpu.proxy.verify.check.bo.InstrBO;
import com.shenpu.proxy.verify.check.bo.ProductBO;
import com.shenpu.proxy.verify.check.bo.RuleBO;
import com.shenpu.proxy.verify.check.msg.Message;
import com.shenpu.proxy.verify.check.msg.MessageWrapper;
import com.shenpu.proxy.verify.client.BlacklistClient;
import com.shenpu.proxy.verify.domain.Dictionary;
import com.shenpu.proxy.verify.dto.AppntDTO;
import com.shenpu.proxy.verify.dto.BeneDTO;
import com.shenpu.proxy.verify.dto.BodyDTO;
import com.shenpu.proxy.verify.dto.InsuredDTO;
import com.shenpu.proxy.verify.dto.PacketDTO;
import com.shenpu.proxy.verify.dto.PolicyDTO;
import com.shenpu.proxy.verify.service.ProductValidateService;

/**
 * 请求保单内容与字典表的一致性校验
 * 
 * @author jetty
 */
@Component
public class CommonValidator {

	private static final Logger logger = Logger.getLogger(CommonValidator.class);
	
	private static final String ID_TYPE = "id_type";

	private static final String MARRIAGE = "marriage";

	private static final String NATIVEPLACE = "nativeplace";
	
	private static final String DEGREE = "degree";
	
	private static final String BENEFIT_RELATION = "relation";
	
	private static final String INSURED_RELATION = "relation";
	
	private static final String PAYINTV = "payintv";
	
	@Autowired
	private BlacklistClient blacklistClient;
	
	@Autowired
	private ProductValidateService productValidateService;

	/**
	 * 完整校验 
	 * 1,数据规则校验 
	 * 2,数据一致性校验 
	 * 3,保险规则校验
	 * 4,保费校验
	 * 
	 * @param packetDTO
	 * @return
	 */
	public Map<String, Object> validate(PacketDTO packetDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		// 1,数据规则校验
		Map<String,	Object> valMap = regularValidate(packetDTO);
		if(((Boolean)valMap.get("result") == false)) {
			//校验不通过，直接退出
			valMap.put("result", false);
			return valMap;
		}else {
			// 2,数据一致性校验 
			MessageWrapper valMsg = accordanceValidate(packetDTO,blacklistClient);
			if(valMsg.isValid()) {
				//省市县校验
				MessageWrapper addressMsg = addressValidate(packetDTO, blacklistClient);
				if(addressMsg.isValid()){
					// 3,保险规则校验
					try {
						MessageWrapper messageWrapper = insurRuleValidate(packetDTO);
						if(messageWrapper.isValid()) {
							resultMap.put("result", true);
							resultMap.put("msg", "保险规则校验通过");
							return resultMap;
						}else {
							resultMap.put("result", false);
							resultMap.put("msg", messageWrapper.getInvalidMessages().get(0));
							return resultMap;
						}
					} catch (Exception e) {
						logger.error("保险规则校验异常：{}", e);
						resultMap.put("result", false);
						resultMap.put("msg", "保险规则校验异常");
						return resultMap;
					}
				}else{
					resultMap.put("result", false);
					resultMap.put("msg", addressMsg.getInvalidMessages().get(0));
					return resultMap;
				}
			}else {
				resultMap.put("result", false);
				resultMap.put("msg", valMsg.getInvalidMessages().get(0));
				return resultMap;
			}
		}
		
	}

	/**
	 * 数据规则校验
	 * 
	 * @param packetDTO
	 * @return
	 */
	public Map<String, Object> regularValidate(PacketDTO packetDTO) {
		MessageWrapper messageWrapper = new MessageWrapper();
		List<Message> messages = new ArrayList<>();
		Map<String, Object> resultMap = new HashMap<>();
		//投保人
		messages.add(packetDTO.getBodyDTO().getAppnt().validatBirthDay(true));
		messages.add(packetDTO.getBodyDTO().getAppnt().validatIDCard(false));
		messages.add(packetDTO.getBodyDTO().getAppnt().validatMobile(false));
		messages.addAll(packetDTO.getBodyDTO().getAppnt().accordanceValidate());
		messageWrapper.setMsgList(messages);
		if(!messageWrapper.isValid()){//投保人校验不通过
			//返回退出校验
			resultMap.put("result", false);
			resultMap.put("msg", messageWrapper.getInvalidMessages().get(0));
			return resultMap;
		}else {
			//进行被保人校验
			if("0".equals(packetDTO.getBodyDTO().getAppnt().getIsself())) {
				List<InsuredDTO> insuredDTOs = packetDTO.getBodyDTO().getInsuredList();
				for(InsuredDTO insuredDTO : insuredDTOs) {
					messages.add(insuredDTO.validatBirthDay(true));
					messages.add(insuredDTO.validatIDCard(false));
					messages.add(insuredDTO.validatMobile(false));
					messages.addAll(insuredDTO.accordanceValidate());
					messageWrapper.setMsgList(messages);
				}
				if(!messageWrapper.isValid()){//投保人校验不通过
					//返回退出校验
					resultMap.put("result", false);
					resultMap.put("msg", messageWrapper.getInvalidMessages().get(0));
					return resultMap;
				}else {
					//进行受益人校验
					if("0".equals(packetDTO.getBodyDTO().getAppnt().getIsStatutory())) {
						List<BeneDTO> beneDTOs = packetDTO.getBodyDTO().getBeneList();
						for(BeneDTO beneDTO : beneDTOs) {
							messages.add(beneDTO.validatBirthDay(true));
							messages.add(beneDTO.validatIDCard(false));
							messages.add(beneDTO.validatMobile(false));
							messages.addAll(beneDTO.accordanceValidate());
							messageWrapper.setMsgList(messages);
						}
						if(!messageWrapper.isValid()){//投保人校验不通过
							//返回退出校验
							resultMap.put("result", false);
							resultMap.put("msg", messageWrapper.getInvalidMessages().get(0));
							return resultMap;
						}else {
							//返回退出校验
							resultMap.put("result", true);
							resultMap.put("msg", "数据规矩校验通过");
							return resultMap;
						}
					}else {
						//返回退出校验
						resultMap.put("result", true);
						resultMap.put("msg", "数据规矩校验通过");
						return resultMap;
					}
				}
			}else {
				//进行受益人校验
				if("0".equals(packetDTO.getBodyDTO().getAppnt().getIsStatutory())) {
					List<BeneDTO> beneDTOs = packetDTO.getBodyDTO().getBeneList();
					for(BeneDTO beneDTO : beneDTOs) {
						messages.add(beneDTO.validatBirthDay(true));
						messages.add(beneDTO.validatIDCard(false));
						messages.add(beneDTO.validatMobile(false));
						messages.addAll(beneDTO.accordanceValidate());
						messageWrapper.setMsgList(messages);
					}
					if(!messageWrapper.isValid()){//投保人校验不通过
						//返回退出校验
						resultMap.put("result", false);
						resultMap.put("msg", messageWrapper.getInvalidMessages().get(0));
						return resultMap;
					}else {
						//返回退出校验
						resultMap.put("result", true);
						resultMap.put("msg", "数据规矩校验通过");
						return resultMap;
					}
				}else {
					//返回退出校验
					resultMap.put("result", true);
					resultMap.put("msg", "数据规矩校验通过");
					return resultMap;
				}
			}
		}
	}

	/**
	 * 数据与字典一致性校验
	 * @param packetDTO
	 * @return
	 */
	public MessageWrapper accordanceValidate(PacketDTO packetDTO,BlacklistClient blacklistClient) {
		MessageWrapper messageWrapper = new MessageWrapper();
		List<Message> messages = new ArrayList<>();
		// 投保人
		BodyDTO bodyDTO = packetDTO.getBodyDTO();
		AppntDTO appnt = bodyDTO.getAppnt();
		// 1.证件
		if (StringUtils.isNotBlank(appnt.getIdType())) {
			Dictionary dictionary =  blacklistClient.queryByCodeTypeAndCode(ID_TYPE, appnt.getIdType());
			if (dictionary==null) {
				Message message = new Message();
				message.setContinued(false);
				message.setMsg("投保人身份证件类型错误");
				message.setReturnValue("001");
				message.setValid(true);
				messages.add(message);
				messageWrapper.setMsgList(messages);
				return messageWrapper;
			}else{
				appnt.setIdTypeName(dictionary.getCodeLabel());
			}
		}
		// 2，婚否
		String marriage = appnt.getMarriage();
		if (StringUtils.isNotBlank(marriage)) {
			Dictionary dictionary =  blacklistClient.queryByCodeTypeAndCode(MARRIAGE,
					marriage);
			if (dictionary==null) {
				Message message = new Message();
				message.setContinued(false);
				message.setMsg("投保人婚否数据类型错误");
				message.setReturnValue("001");
				message.setValid(true);
				messages.add(message);
				messageWrapper.setMsgList(messages);
				return messageWrapper;
			}else{
				appnt.setMarriageName(dictionary.getCodeLabel());
			}
		}
		// 3，国籍
		if (StringUtils.isNotBlank(appnt.getNativeplace())) {
			Dictionary dictionary =  blacklistClient.queryByCodeTypeAndCode(NATIVEPLACE,
					appnt.getNativeplace());
			if (dictionary==null) {
				Message message = new Message();
				message.setContinued(false);
				message.setMsg("投保人国籍类型错误");
				message.setReturnValue("001");
				message.setValid(true);
				messages.add(message);
				messageWrapper.setMsgList(messages);
				return messageWrapper;
			}else{
				appnt.setNativeplaceName(dictionary.getCodeLabel());
			}
		}
		
		// 4，学历
		if (StringUtils.isNotBlank(appnt.getDegree())) {
			Dictionary dictionary =  blacklistClient.queryByCodeTypeAndCode(DEGREE,
					appnt.getDegree());
			if (dictionary==null) {
				Message message = new Message();
				message.setContinued(false);
				message.setMsg("学历类型错误");
				message.setReturnValue("001");
				message.setValid(true);
				messages.add(message);
				messageWrapper.setMsgList(messages);
				return messageWrapper;
			}else{
				appnt.setDegreeName(dictionary.getCodeLabel());
			}
		}
		
		bodyDTO.setAppnt(appnt);
		
		// 被保人
		if("0".equals(appnt.getIsself())) {
			List<InsuredDTO> insuredDTOs = bodyDTO.getInsuredList();
			for(InsuredDTO insuredDTO : insuredDTOs) {
				// 1.证件
				if (StringUtils.isNotBlank(insuredDTO.getIdType())) {
					Dictionary dictionary =  blacklistClient.queryByCodeTypeAndCode(ID_TYPE, insuredDTO.getIdType());
					if (dictionary==null) {
						Message message = new Message();
						message.setContinued(false);
						message.setMsg("被保人身份证件类型错误");
						message.setReturnValue("001");
						message.setValid(true);
						messages.add(message);
						messageWrapper.setMsgList(messages);
						return messageWrapper;
					}else{
						insuredDTO.setIdTypeName(dictionary.getCodeLabel());
					}
				}
				// 2，婚否
				if (StringUtils.isNotBlank(insuredDTO.getMarriage())) {
					Dictionary dictionary =  blacklistClient.queryByCodeTypeAndCode(MARRIAGE, insuredDTO.getMarriage());
					if (dictionary==null) {
						Message message = new Message();
						message.setContinued(false);
						message.setMsg("被保人婚否数据类型错误");
						message.setReturnValue("001");
						message.setValid(true);
						messages.add(message);
						messageWrapper.setMsgList(messages);
						return messageWrapper;
					}else{
						insuredDTO.setMarriageName(dictionary.getCodeLabel());
					}
				}
				// 3，国籍
				if (StringUtils.isNotBlank(insuredDTO.getNativeplace())) {
					Dictionary dictionary =  blacklistClient.queryByCodeTypeAndCode(NATIVEPLACE, insuredDTO.getNativeplace());
					if (dictionary==null) {
						Message message = new Message();
						message.setContinued(false);
						message.setMsg("被保人国籍类型错误");
						message.setReturnValue("001");
						message.setValid(true);
						messages.add(message);
						messageWrapper.setMsgList(messages);
						return messageWrapper;
					}else{
						insuredDTO.setNativeplaceName(dictionary.getCodeLabel());
					}
				}
				// 3，与投保人关系
				if (StringUtils.isNotBlank(insuredDTO.getInsuredRelation())) {
					Dictionary dictionary =  blacklistClient.queryByCodeTypeAndCode(INSURED_RELATION, insuredDTO.getInsuredRelation());
					if (dictionary==null) {
						Message message = new Message();
						message.setContinued(false);
						message.setMsg("与投保人关系类型错误");
						message.setReturnValue("001");
						message.setValid(true);
						messages.add(message);
						messageWrapper.setMsgList(messages);
						return messageWrapper;
					}else{
						insuredDTO.setInsuredRelationName(dictionary.getCodeLabel());
					}
				}
			}
			bodyDTO.setInsuredList(insuredDTOs);
		}
		
		// 受益人
		if("0".equals(appnt.getIsStatutory())) {
			List<BeneDTO> beneDTOs = bodyDTO.getBeneList();
			for(BeneDTO beneDTO : beneDTOs) {
				// 1.证件
				if (StringUtils.isNotBlank(beneDTO.getIdType())) {
					Dictionary dictionary =  blacklistClient.queryByCodeTypeAndCode(ID_TYPE, beneDTO.getIdType());
					if (dictionary==null) {
						Message message = new Message();
						message.setContinued(false);
						message.setMsg("被保人身份证件类型错误");
						message.setReturnValue("001");
						message.setValid(true);
						messages.add(message);
						messageWrapper.setMsgList(messages);
						return messageWrapper;
					}else{
						beneDTO.setIdTypeName(dictionary.getCodeLabel());
					}
				}
				// 2，婚否
				if (StringUtils.isNotBlank(beneDTO.getMarriage())) {
					Dictionary dictionary =  blacklistClient.queryByCodeTypeAndCode(MARRIAGE, beneDTO.getMarriage());
					if (dictionary==null) {
						Message message = new Message();
						message.setContinued(false);
						message.setMsg("被保人婚否数据类型错误");
						message.setReturnValue("001");
						message.setValid(true);
						messages.add(message);
						messageWrapper.setMsgList(messages);
						return messageWrapper;
					}else{
						beneDTO.setMarriageName(dictionary.getCodeLabel());
					}
				}
				// 3，国籍
				if (StringUtils.isNotBlank(beneDTO.getNativeplace())) {
					Dictionary dictionary =  blacklistClient.queryByCodeTypeAndCode(NATIVEPLACE, beneDTO.getNativeplace());
					if (dictionary==null) {
						Message message = new Message();
						message.setContinued(false);
						message.setMsg("被保人国籍类型错误");
						message.setReturnValue("001");
						message.setValid(true);
						messages.add(message);
						messageWrapper.setMsgList(messages);
						return messageWrapper;
					}else{
						beneDTO.setNativeplaceName(dictionary.getCodeLabel());
					}
				}
				// 3，与被保人关系
				if (StringUtils.isNotBlank(beneDTO.getBenefitRelation())) {
					Dictionary dictionary =  blacklistClient.queryByCodeTypeAndCode(BENEFIT_RELATION, beneDTO.getBenefitRelation());
					if (dictionary==null) {
						Message message = new Message();
						message.setContinued(false);
						message.setMsg("与被保人关系类型错误");
						message.setReturnValue("001");
						message.setValid(true);
						messages.add(message);
						messageWrapper.setMsgList(messages);
						return messageWrapper;
					}else{
						beneDTO.setBenefitRelationName(dictionary.getCodeLabel());
					}
				}
				bodyDTO.setBeneList(beneDTOs);
				//保单校验
				PolicyDTO policyDTO = bodyDTO.getPolicyDTO();
				if(StringUtils.isNotBlank(policyDTO.getPayintv())){
					Dictionary dictionary =  blacklistClient.queryByCodeTypeAndCode(PAYINTV, policyDTO.getPayintv());
					if (dictionary==null) {
						Message message = new Message();
						message.setContinued(false);
						message.setMsg("缴费类型错误");
						message.setReturnValue("001");
						message.setValid(true);
						messages.add(message);
						messageWrapper.setMsgList(messages);
						return messageWrapper;
					}else{
						policyDTO.setPayintvName(dictionary.getCodeLabel());
					}
				}
				bodyDTO.setPolicyDTO(policyDTO);
			}
		}
		packetDTO.setBodyDTO(bodyDTO);
		return messageWrapper;
	}
	
	/**
	 * 保险规则校验
	 * @param packetDTO
	 * @throws ClassNotFoundException 
	 */
	public MessageWrapper insurRuleValidate(PacketDTO packetDTO) throws ClassNotFoundException {
		MessageWrapper messageWrapper = new MessageWrapper();
		List<Message> messages = new ArrayList<>();
		String productCode = packetDTO.getBodyDTO().getPolicyDTO().getProductCode();
		JaxbUtil jaxbUtil = new JaxbUtil(ProductBO.class);
		String productXmlString = productValidateService.queryXmlByProductCode(productCode);
		ProductBO productBO = jaxbUtil.fromXml(productXmlString, true);
		//1，校验产品公共核保规则
		List<InstrBO> instrBOs = productBO.getInstrs();
		if(instrBOs!=null) {
			for(InstrBO instrBo : instrBOs) {
				List<RuleBO> ruleBOs = instrBo.getRuleBOs();
				if(ruleBOs!=null && !ruleBOs.isEmpty()) {
					for(RuleBO ruleBO : ruleBOs) {
						List<IfBO> ifBOs = ruleBO.getIfs();
						for(IfBO ifBO : ifBOs) {
							String testString = ifBO.getTest();
							String validatorString = testString.substring(0,testString.indexOf("."));
							String msg = ifBO.getMsg();
							//如果是年龄
							if("AgeValidator".equals(validatorString)) {
								List<Message> messages1 = AgeValidator.execute(validatorString, packetDTO, testString,msg);
								messages.addAll(messages1);
								messageWrapper.setMsgList(messages);
								return messageWrapper;
							//如果是性别
							}else if("SexValidator".equals(validatorString)) {
								List<Message> messages1 = SexValidator.execute(validatorString, packetDTO, testString,msg);
								messages.addAll(messages1);
								messageWrapper.setMsgList(messages);
								return messageWrapper;
							}else {
								return messageWrapper;
							}
						}
					}
				}else {
					return messageWrapper;
				}
			}
		}
		List<RuleBO> ruleBOs = productBO.getRules();
		for(RuleBO ruleBO : ruleBOs) {
			List<IfBO> ifBOs = ruleBO.getIfs();
			for(IfBO ifBO : ifBOs) {
				String testString = ifBO.getTest();
				String validatorString = testString.substring(0,testString.indexOf("."));
				String msg = ifBO.getMsg();
				//如果是年龄
				if("AgeValidator".equals(validatorString)) {
					List<Message> messages1 = AgeValidator.execute(validatorString, packetDTO, testString,msg);
					messages.addAll(messages1);
					messageWrapper.setMsgList(messages);
					return messageWrapper;
				//如果是性别
				}else if("SexValidator".equals(validatorString)) {
					List<Message> messages1 = SexValidator.execute(validatorString, packetDTO, testString,msg);
					messages.addAll(messages1);
					messageWrapper.setMsgList(messages);
					return messageWrapper;
				}else {
					return messageWrapper;
				}
			}
		}
		return messageWrapper;
	}
	
	/**
	 * 投保人、受益人、被保人省市区校验
	 * @param packetDTO
	 * @param blacklistClient
	 * @return
	 */
	public MessageWrapper addressValidate(PacketDTO packetDTO,BlacklistClient blacklistClient) {
		MessageWrapper wrapper = new MessageWrapper();
		BodyDTO bodyDTO = packetDTO.getBodyDTO();
		AppntDTO appnt = bodyDTO.getAppnt();//投保人
		List<BeneDTO> beneList = bodyDTO.getBeneList();//受益人
		List<InsuredDTO> insuredList = bodyDTO.getInsuredList();//被保人
		Message message = null;
		//校验投保人省市区
		message = volidatePCC(appnt.getProvId(),appnt.getCityId(),appnt.getCountyId(),"投保人");
		if(message!=null){
			List<Message> messages = new ArrayList<Message>();
			messages.add(message);
			wrapper.setMsgList(messages);
			return wrapper;
		}
		//校验受益人省市区
		for(BeneDTO bene: beneList){
			message = volidatePCC(bene.getProvId(),bene.getCityId(),bene.getCountyId(),"受益人");
			if(message!=null){
				List<Message> messages = new ArrayList<Message>();
				messages.add(message);
				wrapper.setMsgList(messages);
				return wrapper;
			}
		}
		//校验被保人省市区
		for(InsuredDTO insure: insuredList){
			message = volidatePCC(insure.getProvId(),insure.getCityId(),insure.getCountyId(),"被保人");
			if(message!=null){
				List<Message> messages = new ArrayList<Message>();
				messages.add(message);
				wrapper.setMsgList(messages);
				return wrapper;
			}
		}
		return wrapper;
	}
	
	/**
	 * 省市区校验
	 * @param provId
	 * @param cityId
	 * @param countyId
	 * @return
	 */
	public Message volidatePCC(String provId,String cityId,String countyId,String name){
		
		if(provId!=null&&!provId.equals("")){
			Boolean isLegal = blacklistClient.queryAddressByCode(provId);
			if(!isLegal){
				Message message = new Message();
				message.setReturnValue("001");
				message.setContinued(false);
				message.setMsg(name+"省份编号不存在");
				message.setValid(true);
				return message;
			}
		}
		
		if(cityId!=null&&!cityId.equals("")){
			Boolean isLegalCity = blacklistClient.queryAddressByCodeAndUp(cityId, provId);
			if(provId==null||provId.equals("")){
				Message message = new Message();
				message.setReturnValue("001");
				message.setContinued(false);
				message.setMsg(name+"省编号缺失");
				message.setValid(true);
				return message;
			}
			
			if(!isLegalCity){
				Message message = new Message();
				message.setReturnValue("001");
				message.setContinued(false);
				message.setMsg(name+"市区编号和省编号不匹配");
				message.setValid(true);
				return message;
			}
		}
		
		if(countyId!=null&&!countyId.equals("")){
			Boolean isLegalContry = blacklistClient.queryAddressByCodeAndUp(countyId, cityId);
			if(cityId==null||cityId.equals("")){
				Message message = new Message();
				message.setReturnValue("001");
				message.setContinued(false);
				message.setMsg(name+"市编号缺失");
				message.setValid(true);
				return message;
			}
			
			if(!isLegalContry){
				Message message = new Message();
				message.setReturnValue("001");
				message.setContinued(false);
				message.setMsg(name+"县编号和市编号不匹配");
				message.setValid(true);
				return message;
			}
		}
		
		return null;
	}
	
	
}
