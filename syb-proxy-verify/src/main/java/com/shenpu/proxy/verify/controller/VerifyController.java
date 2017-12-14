package com.shenpu.proxy.verify.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shenpu.base.utils.DateUtil;
import com.shenpu.base.utils.JaxbUtil;
import com.shenpu.proxy.verify.check.validator.CommonValidator;
import com.shenpu.proxy.verify.client.BlacklistClient;
import com.shenpu.proxy.verify.config.FileConfig;
import com.shenpu.proxy.verify.domain.Blacklist;
import com.shenpu.proxy.verify.domain.Fee;
import com.shenpu.proxy.verify.dto.BodyDTO;
import com.shenpu.proxy.verify.dto.HeadDTO;
import com.shenpu.proxy.verify.dto.InsuredDTO;
import com.shenpu.proxy.verify.dto.InsuredPremiumDTO;
import com.shenpu.proxy.verify.dto.PacketDTO;
import com.shenpu.proxy.verify.dto.PolicyDTO;
import com.shenpu.proxy.verify.service.FeeService;
import com.shenpu.proxy.verify.service.PolicyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
public class VerifyController {
	private static final Logger logger = Logger.getLogger(VerifyController.class);
	
	@Autowired
	private CommonValidator commonValidator;
	
	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private FeeService feeService;
	
	@Autowired
	private BlacklistClient blacklistClient;

	@Autowired
	private FileConfig fileConfig;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	
	/**
	 * 获取注册信息
	 */
	@RequestMapping("/eureka")
	public void testEureka(){
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("CONFIG-SERVER", false);
		System.out.println(instanceInfo.getHomePageUrl());
		
		//获取所有实例
		List<InstanceInfo> instances = eurekaClient.getApplication("CONFIG-SERVER").getInstances();
		
		//获取所有应用
		Applications applications = eurekaClient.getApplications();
		System.out.println(applications.size());
	}
	
	
	/**
	 * 核保
	 * @param xmlStr
	 * @return
     */
	@ApiOperation(value="核保",notes="核查报文数据中投保人、被保人、受益人信息是否有误")
	@ApiImplicitParam(name="xmlStr",value = "请求报文",required=true,dataType="String")
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public String verify(@RequestBody String xmlStr) {
		System.out.println(fileConfig.getFontRealPath());
		logger.info("核保开始>>>>>>>>>>>");
		JaxbUtil jaxbUtil = new JaxbUtil(PacketDTO.class);
		//创建返回对象
		PacketDTO rspPacketDTO = new PacketDTO();
		HeadDTO rspHeadDTO = new HeadDTO();
		BodyDTO rspBodyDTO = new BodyDTO();
		//设置返回信息、持久化数据、保存日志
		PacketDTO reqPacketDTO = null;
		BodyDTO bodyDTO;
		PolicyDTO policyDTO = null;
		try {
			reqPacketDTO = jaxbUtil.fromXml(xmlStr, true);
			bodyDTO = reqPacketDTO.getBodyDTO();
			policyDTO = bodyDTO.getPolicyDTO();
			//查看黑名单
			Blacklist black = blacklistClient.queryByIdNo(bodyDTO.getAppnt().getIdNo());
			if(black==null){
				//校验
				Map<String, Object> reustlMap = commonValidator.validate(reqPacketDTO);
				Boolean result = (Boolean)reustlMap.get("result");
				//判断校验是否通过
				if(!result){
					String errorMessage = (String)reustlMap.get("msg");
					rspHeadDTO.setErrorCode("001");
					rspHeadDTO.setErrorMessage(errorMessage);
					rspHeadDTO.setRequestId(reqPacketDTO.getHeadDTO().getRequestId());
					policyDTO.setIsSuccess("0");
					logger.debug("核保失败:"+errorMessage);
				}else{
					//保存数据库
					policyDTO.setIsSuccess("1");
					bodyDTO.setPolicyDTO(policyDTO);
					reqPacketDTO.setBodyDTO(bodyDTO);
					policyService.addPolicy(reqPacketDTO);
					
					//填充返回信息
					rspHeadDTO.setErrorCode("000");
					rspHeadDTO.setErrorMessage("成功");
					rspHeadDTO.setRequestId(reqPacketDTO.getHeadDTO().getRequestId());
					policyDTO.setIsSuccess("1");
					//填充被保人
					List<InsuredPremiumDTO>  insuredPremiumDTOList = new ArrayList<>();
					for(InsuredDTO insuredDTO : bodyDTO.getInsuredList()) {
						InsuredPremiumDTO insuredPremiumDTO = new InsuredPremiumDTO();
						insuredPremiumDTO.setInsuredGrade(insuredDTO.getInsuredGrade());
						insuredPremiumDTO.setInsuredName(insuredDTO.getInsuredName());
						insuredPremiumDTO.setPremium(insuredDTO.getPremium());
						insuredPremiumDTOList.add(insuredPremiumDTO);
					}
					rspBodyDTO.setInsuredPremiumList(insuredPremiumDTOList);
					rspPacketDTO.setBodyDTO(rspBodyDTO);
				}
			}else{
				rspHeadDTO.setErrorCode("001");
				rspHeadDTO.setErrorMessage("黑名单命中："+black.getReason());
				rspHeadDTO.setRequestId(reqPacketDTO.getHeadDTO().getRequestId());
				policyDTO.setIsSuccess("0");
				logger.debug("核保失败：{}黑名单命中");
			}
			
			//填充返回信息
			rspPacketDTO.setHeadDTO(rspHeadDTO);
			String rspXml = jaxbUtil.toXml(rspPacketDTO, "utf-8");
			return rspXml;
		} catch (Exception e) {
			logger.error("核保异常：{}", e);
			//返回
			rspHeadDTO.setErrorCode("001");
			rspHeadDTO.setErrorMessage("服务器繁忙");
			if (reqPacketDTO != null) {
				rspHeadDTO.setRequestId(reqPacketDTO.getHeadDTO().getRequestId());
			}else{
				rspHeadDTO.setErrorMessage("请求数据不合法");
			}
			if (policyDTO != null) {
				policyDTO.setIsSuccess("0");
			}
			rspPacketDTO.setHeadDTO(rspHeadDTO);
			String rspXml = jaxbUtil.toXml(rspPacketDTO, "utf-8");
			return rspXml;
		}
	}

	/**
	 * 试算
	 * @param xmlStr
	 * @return
	 */
	@ApiOperation(value = "试算",notes="根据请求报文，进行校验数据，返回计算的到保费")
	@ApiImplicitParam(name="xmlStr",value="请求报文",required=true,dataType="String")
	@RequestMapping(value = "/trial", method = RequestMethod.POST)
	public String trial(@RequestBody String xmlStr) {
		logger.info("试算开始>>>>>>>>>>>");
		JaxbUtil jaxbUtil = new JaxbUtil(PacketDTO.class);
		try {
			PacketDTO reqPacketDTO = jaxbUtil.fromXml(xmlStr, true);
			//校验
			Map<String, Object> reustlMap = commonValidator.validate(reqPacketDTO);
			Boolean boo = (Boolean)reustlMap.get("result");
			BodyDTO bodyDTO = reqPacketDTO.getBodyDTO();
			//获取条件值
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			PolicyDTO policyDTO = bodyDTO.getPolicyDTO();
			InsuredDTO insured = bodyDTO.getInsuredList().get(0);
			if(boo){
				String productCode = policyDTO.getProductCode();
				String policyTerm = policyDTO.getPolicyTerm();//保险期间
				String premiumTerm = policyDTO.getPremiumTerm();//缴费期间
				String faceamount = policyDTO.getFaceamount();//保额
				String birthday = insured.getBirthday();
				int age = DateUtil.getAge(sdf.parse(birthday), new Date());
				String sex = insured.getSex();
				//设置查询条件
				Fee fee = new Fee();
				fee.setSex(sex);
				fee.setAge(age);
				fee.setProductCode(productCode);
				fee.setInusYear(policyTerm);
				fee.setPayYear(Integer.valueOf(premiumTerm));
				fee.setPolicyYear(Integer.valueOf(faceamount));
				Float f = feeService.getInsureFee(fee);
				logger.info("校验通过，试算结果为："+f);
				return String.valueOf(f);
			}else{
				logger.debug("保单号"+policyDTO.getPolicyNo()+"：试算失败：===》校验未通过!");
			}
		} catch (Exception e) {
			logger.error("试算异常:{}", e);
		}
		return null;
	}
}
